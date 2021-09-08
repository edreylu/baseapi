package com.example.baseapi.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
        private String username;
        private List<String> roles;
        private String accessToken;
        private String name;
}
