package com.rebell.alliance.intelligence.controller;

import com.rebell.alliance.intelligence.entities.SatelliteWrapper;
import com.rebell.alliance.intelligence.services.IntelligenceService;
import com.rebell.alliance.intelligence.services.LocationService;
import com.rebell.alliance.intelligence.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${communication.context.path}")
public class CommunicationController {

    @Autowired
    private IntelligenceService intelligenceService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity intelligence(@RequestBody SatelliteWrapper satellites){
        return ResponseEntity.status(HttpStatus.CREATED).body(intelligenceService.getCarrier(satellites));
    }
}
