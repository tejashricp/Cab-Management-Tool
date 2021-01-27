package com.cab.management.manager;

import com.cab.management.selector.LocationSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheManager {

    @Autowired
    private LocationSelector locationSelector;

    public void invalidateSnapshotByLocationCache(String cityId){
        locationSelector.invalidateEntryFor(cityId);
    }

}
