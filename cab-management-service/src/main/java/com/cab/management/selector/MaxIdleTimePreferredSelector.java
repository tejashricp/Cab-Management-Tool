package com.cab.management.selector;

import com.cab.management.model.BookingDetail;
import com.cab.management.model.CabSnapshot;
import com.cab.management.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class MaxIdleTimePreferredSelector implements CabSelector {
    @Autowired
    private LocationSelector locationSelector;

    @Override
    public List<CabSnapshot> select(BookingDetail bookingDetail) {
           List<CabSnapshot> snapshots = locationSelector.select(bookingDetail);
           List<CabSnapshot> idleCabSnapshots = snapshots.stream()
                   .filter(snapshot -> Status.IDLE.equals(snapshot.getStatus()))
                   .collect(Collectors.toList());
        LocalDateTime oldestTime = LocalDateTime.now();
        List<CabSnapshot> eligibleCabForBooking = new ArrayList<>();
        for (CabSnapshot idleCab:idleCabSnapshots) {
            if(oldestTime.compareTo(idleCab.getStatusUpdateTime()) > 0){
                oldestTime = idleCab.getStatusUpdateTime();
                eligibleCabForBooking .add(idleCab);
            }
        }
        return eligibleCabForBooking;
    }
}
