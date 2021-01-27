package com.cab.management.manager;

import com.cab.management.model.Booking;
import com.cab.management.model.BookingDetail;
import com.cab.management.model.CabSnapshot;
import com.cab.management.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookingManager {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking add(CabSnapshot bookedCab, BookingDetail bookingDetail) {
        Booking booking = Booking.builder()
                .cabId(bookedCab.getId())
                .pickupLocationCityId(bookingDetail.getPickUpCity())
                .dropLocationCityId(bookingDetail.getDropCity())
                .bookingTime(LocalDateTime.now())
                .build();
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();

    }
}
