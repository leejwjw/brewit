package com.pt.brewit.dto;

import lombok.Data;

@Data
public class AuthDTO {
    private String email;
    private String auth; // enum Role -> private Role auth
}
