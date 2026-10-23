package com.beraldo.diary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "time_sleep", nullable = false)
    @NotNull
    @Positive
    private BigDecimal timeSleep;

    @Column(name = "sleep_quality", nullable = false)
    @NotBlank
    private String sleepQuality;

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
