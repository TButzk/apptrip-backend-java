package unisinos.apptrip.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisinos.apptrip.map.UserMapper;
import unisinos.apptrip.model.shared.DtoResponse;
import unisinos.apptrip.model.shared.PageInfo;
import unisinos.apptrip.model.shared.PageResponse;
import unisinos.apptrip.dto.UpdateUserDto;
import unisinos.apptrip.dto.UserDto;
import unisinos.apptrip.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "Users", description = "Endpoints do CRUD dos Usuários")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista usuários")
    public ResponseEntity<PageResponse<UserDto>> get(@RequestParam int take, @RequestParam int skip){
        var users = userService.getUser(take, skip);
        var response = PageResponse.success(users.map(userMapper::toDto).toList(), PageInfo.fromPage(users));
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna usuário por id")
    public ResponseEntity<DtoResponse<UserDto>> getById(@PathVariable String id){

        var user = userService.getUser(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(userMapper.toDto(user)));
    }

    @PatchMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza os dados de um usuário")
    public ResponseEntity<DtoResponse<UserDto>> update(@RequestBody UpdateUserDto update){

        try{
            var user = userService.updateUser(update);
            return ResponseEntity.ok(DtoResponse.success(userMapper.toDto(user)));
        }
        catch (Exception ex){
            return ResponseEntity.ok(DtoResponse.error(ex.getMessage()));
        }
    }

    @DeleteMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove um usuário")
    public ResponseEntity<DtoResponse<UserDto>> delete(){
        var user = userService.removeUser();
        return ResponseEntity.ok(DtoResponse.success(userMapper.toDto(user)));
    }
}

