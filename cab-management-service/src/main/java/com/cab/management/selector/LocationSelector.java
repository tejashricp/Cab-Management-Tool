package com.cab.management.selector;

import com.cab.management.model.BookingDetail;
import com.cab.management.model.CabSnapshot;
import com.cab.management.repository.CabSnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.groupingBy;

@Component
public class LocationSelector implements CabSelector {

    private Map<String, List<CabSnapshot>> SNAPSHOT_BY_LOCATION;

    @Autowired
    private CabSnapshotRepository cabSnapshotRepository;

    @PostConstruct
    public void init() {
        build();
    }

    private Map<String, List<CabSnapshot>> build() {
        Map<String, List<CabSnapshot>> snapshotList = cabSnapshotRepository
                .findAll()
                .stream()
                .collect(groupingBy(CabSnapshot::getCityId));
        SNAPSHOT_BY_LOCATION = snapshotList;
        return snapshotList;
    }

    @Override
    public List<CabSnapshot> select(BookingDetail bookingDetail) {
        String pickUpCityId = bookingDetail.getPickUpCity();
        List<CabSnapshot> cabSnapshotList = null;
        if(SNAPSHOT_BY_LOCATION.get(pickUpCityId) == null) {
            cabSnapshotList = cabSnapshotRepository.findAll()
                    .stream()
                    .filter(snapshot -> pickUpCityId.equals(snapshot.getCityId()))
                    .collect(Collectors.toList());
            SNAPSHOT_BY_LOCATION.put(pickUpCityId, cabSnapshotList);
        }

        return SNAPSHOT_BY_LOCATION.get(bookingDetail.getPickUpCity());
    }

    public void invalidateEntryFor(String cityId){
        if(SNAPSHOT_BY_LOCATION.containsKey(cityId))
            SNAPSHOT_BY_LOCATION.put(cityId, null);
    }
}
