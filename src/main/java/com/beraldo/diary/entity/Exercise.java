package com.beraldo.diary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "exercise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDateTime date;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "series", nullable = false)
    private Integer serie;

    @Column(name = "repetition", nullable = false)
    private Integer repetition;

    @Column(name = "carga", nullable = false)
    private Float carga;

    @Column(name = "dt_insert", nullable = false, updatable = false)
    private LocalDateTime dtInsert;

    @PrePersist
    protected void onCreate(){
        if(this.dtInsert == null){
            this.dtInsert = LocalDateTime.now();

        }
    }
}
