package com.cab.management.controller;

import com.cab.management.model.CabSnapshot;
import com.cab.management.model.Status;
import com.cab.management.service.CabSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CabSnapshotController {

    @Autowired
    private CabSnapshotService cabSnapshotService;

    @PostMapping("/cabs/snapshots")
    public ResponseEntity registerCabSnapshot(@RequestBody List<CabSnapshot> cabSnapshots) {
        cabSnapshotService.add(cabSnapshots);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/snapshot/cab/{cabId}/state/{status}")
    public ResponseEntity updateCabState(@PathVariable("cabId") String cabId, @PathVariable("status") Status status) {
        cabSnapshotService.updateStatus(cabId, status);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/snapshot/cab/{cabId}/city/{cityId}")
    public ResponseEntity updateCabCity(@PathVariable("cabId") String cabId, @PathVariable("cityId") String cityId) {
        cabSnapshotService.updateCity(cabId, cityId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/snapshots/cabs")
    public ResponseEntity getAllCabSnapshots() {
        return new ResponseEntity(cabSnapshotService.getCabs(), HttpStatus.OK);
    }
}
