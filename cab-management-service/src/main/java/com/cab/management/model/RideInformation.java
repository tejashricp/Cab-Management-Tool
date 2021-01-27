package com.cab.management.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RideInformation extends Entity {
    private String cabId;
    private String bookingId;
    private String pickUpCity;
    private String dropCity;
    private LocalDateTime pickupTime;
    private LocalDateTime dropTime;
}
