package com.cab.management.controller;


import com.cab.management.model.BookingDetail;
import com.cab.management.model.RideInformation;
import com.cab.management.service.CabBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabBookingController {
    @Autowired
    private CabBookingService cabBookingService;

    @PostMapping("/cab/booking")
    public RideInformation bookCab(@RequestBody BookingDetail bookingDetail) {
        return cabBookingService.book(bookingDetail);
    }

    @GetMapping("/cab/booking")
    public ResponseEntity getAllCabBookings() {
        return new ResponseEntity(cabBookingService.getBookings(), HttpStatus.OK);
    }
}
