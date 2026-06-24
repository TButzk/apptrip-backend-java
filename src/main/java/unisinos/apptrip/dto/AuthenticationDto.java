package unisinos.apptrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}

