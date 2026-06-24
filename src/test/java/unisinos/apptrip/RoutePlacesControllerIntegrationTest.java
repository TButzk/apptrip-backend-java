package unisinos.apptrip;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;
import java.util.Locale;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoutePlacesControllerIntegrationTest extends PostgresIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void protectedEndpointReturnsJsonUnauthorizedWithoutBasicChallenge() throws Exception {
        mockMvc.perform(get("/api/v1/routes/mine").param("skip", "0").param("take", "20"))
                .andExpect(status().isUnauthorized())
                .andExpect(header().doesNotExist("WWW-Authenticate"))
                .andExpect(header().string("Content-Type", not(emptyOrNullString())))
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void recordsOrderedPointsAndIgnoresDuplicateOrNearCoordinates() throws Exception {
        String token = createUserAndLogin();
        String routeId = createRoute(token, "Captura automatica", 25);
        String pointId = UUID.randomUUID().toString();

        String firstResponse = addPoint(token, routeId, pointId, 1, -29.167300, -51.179600)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String firstPointId = objectMapper.readTree(firstResponse).path("data").path("id").asText();

        addPoint(token, routeId, pointId, 1, -29.167300, -51.179600)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(firstPointId));

        addPoint(token, routeId, UUID.randomUUID().toString(), 2, -29.167301, -51.179601)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(firstPointId));

        addPoint(token, routeId, UUID.randomUUID().toString(), 3, -29.165100, -51.182000)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(not(firstPointId)));

        mockMvc.perform(get("/api/v1/routes/{routeId}/places", routeId)
                        .param("skip", "0")
                        .param("take", "20")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].sequence").value(1))
                .andExpect(jsonPath("$.data[1].sequence").value(3));
    }

    @Test
    void finalizesRenamesAndPublishesRoute() throws Exception {
        String token = createUserAndLogin();
        String routeId = createRoute(token, "Rota em andamento", 25);

        addPoint(token, routeId, UUID.randomUUID().toString(), 1, -29.167300, -51.179600)
                .andExpect(status().isOk());
        addPoint(token, routeId, UUID.randomUUID().toString(), 2, -29.165100, -51.182000)
                .andExpect(status().isOk());

        mockMvc.perform(patch("/api/v1/routes/{id}/finalize", routeId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("FINISHED"));

        mockMvc.perform(patch("/api/v1/routes/{id}", routeId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Rota academica concluida"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Rota academica concluida"));

        mockMvc.perform(patch("/api/v1/routes/{id}/publish", routeId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("PUBLISHED"));

        mockMvc.perform(get("/api/v1/routes/published").param("skip", "0").param("take", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[?(@.id == '" + routeId + "')].status").value("PUBLISHED"));
    }

    private String createUserAndLogin() throws Exception {
        String email = "it-" + UUID.randomUUID() + "@apptrip.local";
        String password = "Senha123!";

        mockMvc.perform(post("/api/v1/users-auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Usuario Integracao","email":"%s","password":"%s"}
                                """.formatted(email, password)))
                .andExpect(status().isCreated());

        String response = mockMvc.perform(post("/api/v1/users-auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"%s","password":"%s"}
                                """.formatted(email, password)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonNode json = objectMapper.readTree(response);
        return json.path("data").path("token").asText();
    }

    private String createRoute(String token, String name, int minimumDistanceMeters) throws Exception {
        String response = mockMvc.perform(post("/api/v1/routes")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"%s","minimumDistanceMeters":%d}
                                """.formatted(name, minimumDistanceMeters)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).path("data").path("id").asText();
    }

    private ResultActions addPoint(
            String token,
            String routeId,
            String clientPointId,
            int sequence,
            double latitude,
            double longitude
    ) throws Exception {
        return mockMvc.perform(post("/api/v1/places")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(Locale.ROOT, """
                        {
                          "name":"Ponto %d",
                          "routeId":"%s",
                          "type":"Public",
                          "latitude":%f,
                          "longitude":%f,
                          "sequence":%d,
                          "capturedAt":"2026-06-06T12:00:00",
                          "clientPointId":"%s",
                          "accuracyMeters":5.0
                        }
                        """, sequence, routeId, latitude, longitude, sequence, clientPointId)));
    }
}
