package unisinos.apptrip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoutePlacesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnRoutePlacesOrderedBySequence() throws Exception {
        String email = "it-" + UUID.randomUUID() + "@apptrip.local";
        String password = "123456";

        mockMvc.perform(post("/api/v1/users-auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Integration User",
                                  "email": "%s",
                                  "password": "%s"
                                }
                                """.formatted(email, password)))
                .andExpect(status().isOk());

        String loginJson = mockMvc.perform(post("/api/v1/users-auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "%s",
                                  "password": "%s"
                                }
                                """.formatted(email, password)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = extractField(loginJson, "\"token\":\"", "\"");

        String routeJson = mockMvc.perform(post("/api/v1/routes")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "IT Route"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String routeId = extractField(routeJson, "\"id\":\"", "\"");

        mockMvc.perform(post("/api/v1/places")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Point Sequence 2",
                                  "routeId": "%s",
                                  "type": "Public",
                                  "latitude": -29.1673,
                                  "longitude": -51.1796,
                                  "sequence": 2,
                                  "capturedAt": "2026-04-07T21:59:00"
                                }
                                """.formatted(routeId)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/v1/places")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Point Sequence 1",
                                  "routeId": "%s",
                                  "type": "Public",
                                  "latitude": -29.1651,
                                  "longitude": -51.1820,
                                  "sequence": 1,
                                  "capturedAt": "2026-04-07T21:58:30"
                                }
                                """.formatted(routeId)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/routes/{routeId}/places", routeId)
                        .param("skip", "0")
                        .param("take", "20")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].sequence").value(1))
                .andExpect(jsonPath("$.data[1].sequence").value(2));
    }

    @Test
    void shouldPublishAndFinalizeRoute() throws Exception {
        String email = "it-" + UUID.randomUUID() + "@apptrip.local";
        String password = "123456";

        mockMvc.perform(post("/api/v1/users-auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Integration User",
                                  "email": "%s",
                                  "password": "%s"
                                }
                                """.formatted(email, password)))
                .andExpect(status().isOk());

        String loginJson = mockMvc.perform(post("/api/v1/users-auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "%s",
                                  "password": "%s"
                                }
                                """.formatted(email, password)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = extractField(loginJson, "\"token\":\"", "\"");

        String routeJson = mockMvc.perform(post("/api/v1/routes")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "IT Route Publish"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("DRAFT"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String routeId = extractField(routeJson, "\"id\":\"", "\"");

        mockMvc.perform(post("/api/v1/places")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Point A",
                                  "routeId": "%s",
                                  "type": "Public",
                                  "latitude": -29.1673,
                                  "longitude": -51.1796,
                                  "sequence": 1,
                                  "capturedAt": "2026-04-07T21:59:00"
                                }
                                """.formatted(routeId)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/v1/places")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Point B",
                                  "routeId": "%s",
                                  "type": "Public",
                                  "latitude": -29.1651,
                                  "longitude": -51.1820,
                                  "sequence": 2,
                                  "capturedAt": "2026-04-07T21:58:30"
                                }
                                """.formatted(routeId)))
                .andExpect(status().isOk());

        mockMvc.perform(patch("/api/v1/routes/{id}/publish", routeId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("PUBLISHED"));

        mockMvc.perform(get("/api/v1/routes/mine")
                        .param("skip", "0")
                        .param("take", "20")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].status").value("PUBLISHED"));

        mockMvc.perform(get("/api/v1/routes/published")
                        .param("skip", "0")
                        .param("take", "20")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].status").value("PUBLISHED"));

        mockMvc.perform(patch("/api/v1/routes/{id}/finalize", routeId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("FINISHED"));
    }

    private static String extractField(String source, String startToken, String endToken) {
        int start = source.indexOf(startToken);
        if (start < 0) {
            throw new IllegalStateException("Start token not found: " + startToken);
        }
        start += startToken.length();

        int end = source.indexOf(endToken, start);
        if (end < 0) {
            throw new IllegalStateException("End token not found: " + endToken);
        }

        return source.substring(start, end);
    }
}

