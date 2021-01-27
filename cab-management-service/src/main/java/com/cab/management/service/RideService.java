package com.cab.management.service;

import com.cab.management.exception.InvalidCabException;
import com.cab.management.exception.InvalidRideInfromartion;
import com.cab.management.model.BookingDetail;
import com.cab.management.model.CabSnapshot;
import com.cab.management.model.RideInformation;
import com.cab.management.model.Status;
import com.cab.management.repository.CabSnapshotRepository;
import com.cab.management.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RideService {
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CabSnapshotRepository cabSnapshotRepository;

    public void endTrip(String cabId, String rideId) throws InvalidRideInfromartion, InvalidCabException {
        RideInformation rideInformation = rideRepository.findOne(rideId);
        if(rideInformation != null) {
            rideInformation.setDropTime(LocalDateTime.now());
            rideRepository.save(rideInformation);
        }
        else {
            throw new InvalidRideInfromartion("This is ride is not available");
        }
        String cabIdFromRide = rideInformation.getCabId();
        if(cabId.equals(cabIdFromRide)) {

            CabSnapshot cabSnapshot = cabSnapshotRepository.findOne(cabId);
            cabSnapshot.setStatus(Status.IDLE);
            cabSnapshot.setStatusUpdateTime(LocalDateTime.now());
            cabSnapshot.setCityId(rideInformation.getDropCity());

            cabSnapshotRepository.save(cabSnapshot);
        }
        else {
            throw new InvalidCabException("This cab is not booked for this ride");
        }
    }

    public RideInformation startTrip(BookingDetail bookingDetail, String id, String bookingId) {
        RideInformation rideInformation = new RideInformation();
        rideInformation.setBookingId(bookingId);
        rideInformation.setCabId(id);
        rideInformation.setPickUpCity(bookingDetail.getPickUpCity());
        rideInformation.setPickupTime(LocalDateTime.now());
        rideInformation.setDropCity(bookingDetail.getDropCity());
        rideRepository.save(rideInformation);
        return rideInformation;
    }
}
