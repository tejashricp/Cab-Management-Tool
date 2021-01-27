package com.cab.management.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class Booking extends Entity {
    private String cabId;
    private String pickupLocationCityId;
    private String dropLocationCityId;
    private LocalDateTime bookingTime;
}
