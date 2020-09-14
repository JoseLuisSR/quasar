package com.rebell.alliance.intelligence.controller;

import com.rebell.alliance.intelligence.entities.Spaceship;
import com.rebell.alliance.intelligence.exceptions.LocationException;
import com.rebell.alliance.intelligence.exceptions.MessageException;
import org.springframework.http.RequestEntity;

public interface IntelligenceService {

    public Spaceship getSpacheship(RequestEntity requestEntity) throws MessageException, LocationException;
}
