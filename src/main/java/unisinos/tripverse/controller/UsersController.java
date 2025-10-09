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
@Tag(name = "Users", description = "Controller responsável pela administração de usuários")
public class UsersController {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Criar usuário", description = "Cria um usuário dentro do sistema")
    public User create(@RequestBody CreateUserDTO create){
        return new User();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna usuário", description = "Retorna um usuário")
    public List<User> get(@RequestParam int limit, @RequestParam int skip){
        return List.of(new User());
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna usuário", description = "Retorna um usuário pelo ID")
    public User getById(@PathVariable String id){
        return new User();
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário")
    public UserDTO update(@PathVariable String id, @RequestBody UpdateUserDTO update){
        return new UserDTO();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove um usuário", description = "Remove um usuário")
    public UserDTO delete(@PathVariable String id){
        return new UserDTO();
    }
}
