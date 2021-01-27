package com.cab.management.service;

import com.cab.management.manager.CacheManager;
import com.cab.management.model.CabSnapshot;
import com.cab.management.model.Status;
import com.cab.management.repository.CabSnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CabSnapshotService {

    @Autowired
    private CabSnapshotRepository cabSnapshotRepository;

    @Autowired
    private CacheManager cacheManager;

    public void add(List<CabSnapshot> cabSnapshots) {
        cabSnapshots.stream().forEach(cabSnapshot -> cabSnapshotRepository.save(cabSnapshot));
    }

    public void updateStatus(String id, Status status) {
        CabSnapshot cabSnapshot = cabSnapshotRepository.findOne(id);
        if(cabSnapshot != null) {
            cabSnapshot.setStatus(status);
            cabSnapshotRepository.save(cabSnapshot);
        }
    }

    public void updateCity(String cabId, String cityId) {
        CabSnapshot cabSnapshot = cabSnapshotRepository.findOne(cabId);
        if(cabSnapshot != null){
            cabSnapshot.setCityId(cityId);
            cabSnapshotRepository.save(cabSnapshot);
            cacheManager.invalidateSnapshotByLocationCache(cityId);
        }

    }

    public List<CabSnapshot> getCabs() {
        return cabSnapshotRepository.findAll();
    }
}
