package com.cab.management.service;

import com.cab.management.manager.BookingManager;
import com.cab.management.model.*;

import com.cab.management.selector.CabSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CabBookingService {

    @Autowired
    @Qualifier("maxIdleTimePreferredSelector")
    private CabSelector cabSelector;

    @Autowired
    private BookingManager bookingManager;

    @Autowired
    private CabSnapshotService cabSnapshotService;

    @Autowired
    private RideService rideService;

    public RideInformation book(BookingDetail bookingDetail) {
        List<CabSnapshot> selectedCabs = cabSelector.select(bookingDetail);
        CabSnapshot bookedCab = selectedCabs.get(0);
        Booking booking = bookingManager.add(bookedCab, bookingDetail);
        cabSnapshotService.updateStatus(bookedCab.getId(), Status.ON_TRIP);
        return rideService.startTrip(bookingDetail, bookedCab.getId(), booking.id);
    }

    public List<Booking> getBookings() {
        return bookingManager.getBookings();
    }
}
