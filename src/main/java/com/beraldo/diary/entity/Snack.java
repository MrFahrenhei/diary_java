package com.beraldo.diary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "snack")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Snack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "dt_insert", nullable = false, updatable = false)
    private LocalDateTime dtInsert;

    @PrePersist
    protected void onCreate(){
        if(this.dtInsert == null){
            this.dtInsert = LocalDateTime.now();
        }
    }
}
