package com.example.midterm.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty(message = "username can not empty")
    @Email
    private String username;
    @NotEmpty(message = "password can not empty")
    @Email
    private String password;
    @NotEmpty
    private String address;
}
