package com.rebell.alliance.intelligence.services;

import com.rebell.alliance.intelligence.controller.IntelligenceService;
import com.rebell.alliance.intelligence.entities.Carrier;
import com.rebell.alliance.intelligence.entities.Position;
import com.rebell.alliance.intelligence.entities.SatelliteWrapper;
import com.rebell.alliance.intelligence.entities.Spaceship;
import com.rebell.alliance.intelligence.exceptions.LocationException;
import com.rebell.alliance.intelligence.exceptions.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class IntelligenceServiceImpl implements IntelligenceService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessageService messageService;

    @Autowired
    private Environment environment;

    @Override
    public Spaceship getSpacheship(RequestEntity requestEntity) throws MessageException, LocationException {

        SatelliteWrapper satelliteWrapper = (SatelliteWrapper)requestEntity.getBody();
        if(satelliteWrapper.getMessages().size() < 2)
            throw new MessageException("Nùmero de mensajes insuficientes");
        String message = messageService.getMessage(satelliteWrapper.getMessages());

        uploadPositions(satelliteWrapper);
        if( (satelliteWrapper.getPositions().length < 2) || (satelliteWrapper.getDistances().length < 2) )
            throw new LocationException("Nùmero posicion o distancias insuficientes");
        double [] points = locationService.getLocation(satelliteWrapper.getPositions(), satelliteWrapper.getDistances());
        Position position = new Position(points);

        return new Carrier(position, message);
    }

    public void uploadPositions(SatelliteWrapper satelliteWrapper){

        if(satelliteWrapper.getPositions()[0] == null) {
            int numberSat = Integer.parseInt(environment.getProperty("satellites.numbers"));
            double[][] pointsList = new double[numberSat][];
            String[] satellitePos;
            for (int i = 0; i < satelliteWrapper.getSatellites().size(); i++) {
                satellitePos = environment.getProperty("satellites." + i + ".position").split(",");
                pointsList[i] = Arrays.stream(satellitePos)
                        .map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
            satelliteWrapper.setPositions(pointsList);
        }
    }

}
