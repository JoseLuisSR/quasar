package com.rebell.alliance.intelligence.services;

import com.rebell.alliance.intelligence.entities.Carrier;
import com.rebell.alliance.intelligence.entities.Position;
import com.rebell.alliance.intelligence.entities.SatelliteWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntelligenceService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessageService messageService;

    public Carrier getCarrier(SatelliteWrapper satelliteWrapper){
        Position position = new Position(locationService.getLocation(satelliteWrapper));
        String message = messageService.getMessage(satelliteWrapper);
        return  new Carrier(position, message);
    }

}
