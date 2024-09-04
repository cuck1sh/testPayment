package com.example.testpayment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDto {

    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
