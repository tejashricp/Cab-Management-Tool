package com.cab.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabSnapshot extends Entity {
    private String cityId;
    private Status status;
    private LocalDateTime statusUpdateTime;
}
