package unisinos.apptrip.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisinos.apptrip.dto.*;
import unisinos.apptrip.map.UserMapper;
import unisinos.apptrip.model.shared.DtoResponse;
import unisinos.apptrip.service.UserLoginService;

@RestController
@RequestMapping("api/v1/users-auth")
@Tag(name = "Autenticação")
@RequiredArgsConstructor
public class UserLoginController {
    private final UserMapper userMapper;
    private final UserLoginService userLoginService;

    @PostMapping
    public ResponseEntity<DtoResponse<UserDto>> create(@Valid @RequestBody CreateUserDto create) {
        var user = userLoginService.createUser(create);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponse.success(userMapper.toDto(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<DtoResponse<UserLoginDto>> login(@Valid @RequestBody AuthenticationDto request) {
        var result = userLoginService.login(request);
        return ResponseEntity.ok(DtoResponse.success(userMapper.toDto(result.user(), result.token())));
    }
}
