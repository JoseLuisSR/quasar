package com.rebell.alliance.intelligence.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SatelliteWrapper {

    private List<Satellite> satellites;

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public double[] getDistances(){

        double [] distances = new double[satellites.size()];
        for(int i = 0; i < satellites.size(); i ++){
            distances[i] = satellites.get(i).getDistance();
        }
        return  distances;
    }

    public double[][] getPositions(){
        double [][] positions = new double[satellites.size()][];
        String[] points;
        for(int i = 0; i < satellites.size(); i ++){
            if(satellites.get(i).getPosition() != null) {
                points = satellites.get(i).getPosition().toString().split(",");
                positions[i] = Arrays.stream(points)
                        .map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
        }
        return positions;
    }

    public void setPositions(double[][] pointsList){
        Position position;
        for(int i = 0; i < pointsList.length; i++){
            position = new Position(pointsList[i]);
            satellites.get(i).setPosition(position);
        }
    }

    public List<List<String>> getMessages(){
        List<List<String>> messages = new ArrayList<List<String>>();
        for(Satellite s : satellites){
            messages.add(s.getMessage());
        }
        return  messages;
    }

}
