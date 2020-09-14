package com.rebell.alliance.intelligence.controller;

import com.rebell.alliance.intelligence.entities.SatelliteWrapper;
import com.rebell.alliance.intelligence.exceptions.LocationException;
import com.rebell.alliance.intelligence.exceptions.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "${communication.context.path}")
public class CommunicationController {

    @Autowired
    private IntelligenceService intelligenceService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret(RequestEntity<SatelliteWrapper> requestEntity){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(intelligenceService.getSpacheship(requestEntity));
        }catch (MessageException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }catch (LocationException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
