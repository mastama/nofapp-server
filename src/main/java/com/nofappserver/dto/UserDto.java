package com.nofappserver.dto;

import com.nofappserver.model.Biodata;
import com.nofappserver.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Biodata biodata;
    private Set<Role> roles = new HashSet<>();
}
