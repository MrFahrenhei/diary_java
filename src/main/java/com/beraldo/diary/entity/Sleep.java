package com.beraldo.diary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sleep")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_sleep", nullable = false)
    private BigDecimal timeSleep;

    @Column(name = "sleep_quality", nullable = false)
    private String sleepQuality;

    @Column(name = "dt_insert", nullable = false, updatable = false)
    private LocalDateTime dtInsert;

    @PrePersist
    protected void onCreate(){
        if(this.dtInsert == null){
            this.dtInsert = LocalDateTime.now();
        }
    }
}
