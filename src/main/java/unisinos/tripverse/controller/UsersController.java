package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.user.User;
import unisinos.tripverse.model.user.CreateUserDTO;
import unisinos.tripverse.model.user.UpdateUserDTO;
import unisinos.tripverse.model.user.UserDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "Users", description = "Endpoints do CRUD dos Usuários")
public class UsersController {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria usuário")
    public UserDTO create(@RequestBody CreateUserDTO create){
        return new UserDTO();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista usuários")
    public List<UserDTO> get(@RequestParam int limit, @RequestParam int skip){
        return List.of(new UserDTO());
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna usuário por id")
    public UserDTO getById(@PathVariable String id){
        return new UserDTO();
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza os dados de um usuário")
    public UserDTO update(@PathVariable String id, @RequestBody UpdateUserDTO update){
        return new UserDTO();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove um usuário")
    public UserDTO delete(@PathVariable String id){
        return new UserDTO();
    }
}
