package com.rebell.alliance.intelligence.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.rebell.alliance.intelligence.entities.SatelliteWrapper;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class LocationService {

    @Autowired
    private Environment environment;

    public double[] getLocation(SatelliteWrapper satelliteList) {

        int satellitesSize = satelliteList.getSatellites().size();
        double[][] positions = new double[satellitesSize][2];
        double [] distances = new double[satellitesSize];
        String[] satellitePos;

        for(int i = 0; i < satellitesSize; i ++){
            satellitePos = environment.getProperty("satellite." + i + ".position").split(",");
            positions[i] = Arrays.stream(satellitePos)
                                .map(Double::valueOf)
                                .mapToDouble(Double::doubleValue)
                                .toArray();
            distances[i] = satelliteList.getSatellites().get(i).getDistance();
        }

        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        return  nSolver.solve().getPoint().toArray();
    }

}
