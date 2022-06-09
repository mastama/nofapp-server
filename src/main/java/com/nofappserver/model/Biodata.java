package com.nofappserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "biodata")
public class Biodata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "umur")
    private Integer umur;

    @Column(name = "pekerjaan")
    private String pekerjaan;

    @Column(name = "kecanduan")
    private String kecanduan;
}
