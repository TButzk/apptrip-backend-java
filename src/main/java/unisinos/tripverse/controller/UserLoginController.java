package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unisinos.tripverse.exception.AlreadyExistsException;
import unisinos.tripverse.map.UserMapper;
import unisinos.tripverse.model.shared.DtoResponse;
import unisinos.tripverse.model.user.AuthenticationDto;
import unisinos.tripverse.model.user.CreateUserDto;
import unisinos.tripverse.model.user.UserDto;
import unisinos.tripverse.model.user.UserLoginDto;
import unisinos.tripverse.service.UserLoginService;

@Slf4j
@RestController
@RequestMapping("api/v1/users-login")
@Tag(name = "Users", description = "Endpoints responsáveis pela autenticação do usuário")
public class UserLoginController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria usuário")
    public ResponseEntity<DtoResponse<UserDto>> create(@RequestBody CreateUserDto create){

        try{
            var user = userLoginService.createUser(create);
            return ResponseEntity.ok().body(DtoResponse.success(userMapper.toDto(user)));
        }
        catch (AlreadyExistsException ex){
            return ResponseEntity.badRequest().body(DtoResponse.error("User already exists"));
        }
    }

    @PostMapping("/login")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Efetua login")
    public ResponseEntity<DtoResponse<UserLoginDto>> login(@org.springframework.web.bind.annotation.RequestBody AuthenticationDto authRequest) {
        var data = userLoginService.login(authRequest);
        return ResponseEntity.ok().body(DtoResponse.success(userMapper.toDto(data.user(), data.token())));
    }
}
