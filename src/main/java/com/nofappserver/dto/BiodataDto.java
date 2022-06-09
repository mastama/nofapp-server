package com.nofappserver.dto;

import com.nofappserver.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BiodataDto {
    private Integer id;
    private Integer umur;
    private String pekerjaan;
    private String kecanduan;
    private User user;
}
