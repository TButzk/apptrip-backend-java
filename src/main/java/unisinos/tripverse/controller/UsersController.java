package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "Users", description = "Controller responsável pela administração de usuários")
public class UsersController {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @Operation(summary = "Criar usuário", description = "Cria um usuário dentro do sistema")
    @Parameters({
            @Parameter(name = "user", description = "Modelo para criar um usuário")
    })
    public User create(@RequestBody User user){
        return new User();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @Operation(summary = "Retorna usuário", description = "Retorna um usuário")
    public User get(){
        return new User();
    }
}
