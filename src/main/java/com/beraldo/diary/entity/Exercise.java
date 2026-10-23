package com.beraldo.diary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "data", nullable = false)
    @NotNull
    private LocalDateTime date;

    @Column(name = "name", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(name = "series", nullable = false)
    @Positive
    @NotNull
    private Integer serie;

    @Column(name = "repetition", nullable = false)
    @NotNull
    @Positive
    private Integer repetition;

    @Column(name = "carga", nullable = false)
    @PositiveOrZero
    @NotNull
    private Float carga;

    @Column(name = "dt_insert", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dtInsert;

    @PrePersist
    protected void onCreate(){
        if(this.dtInsert == null){
            this.dtInsert = LocalDateTime.now();

        }
    }
}
