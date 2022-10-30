package com.miniproject.aeon.domain.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
}
