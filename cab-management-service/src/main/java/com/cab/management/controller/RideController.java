package com.cab.management.controller;

import com.cab.management.exception.InvalidCabException;
import com.cab.management.exception.InvalidRideInfromartion;
import com.cab.management.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RideController {

    @Autowired
    private RideService rideService;

    @PutMapping("/cab/{cab_id}/ride/{ride_id}/end")
    public ResponseEntity endTrip(@PathVariable("cab_id") String cabId,
                                  @PathVariable("ride_id") String rideId) throws InvalidRideInfromartion, InvalidCabException {
        rideService.endTrip(cabId, rideId);
        return null;
    }


}
