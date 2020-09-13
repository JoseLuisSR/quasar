package com.rebell.alliance.intelligence.services;

import com.rebell.alliance.intelligence.entities.Satellite;
import com.rebell.alliance.intelligence.entities.SatelliteWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageService {

    public String completeMessage(List<String> m1, List<String> m2, List<String> m3){

        if(m1.size()>0 && m2.size() > 0 && m3.size() > 0){

            if(!m1.get(0).equals("")) {
                return m1.get(0) + " " + completeMessage(m1.subList(1, m1.size()), m2.subList(1, m2.size()), m3.subList(1, m2.size()));
            }else if(!m2.get(0).equals("")) {
                return m2.get(0) + " " + completeMessage(m1.subList(1, m1.size()), m2.subList(1, m2.size()), m3.subList(1, m3.size()));
            }else if(!m3.get(0).equals("")) {
                return m3.get(0) + " " + completeMessage(m1.subList(1, m1.size()), m2.subList(1, m2.size()), m3.subList(1, m3.size()));
            }else {
                return completeMessage(m1.subList(1, m1.size()), m2.subList(1, m2.size()), m3.subList(1, m3.size()));
            }
        }else{
            return "";
        }
    }

    public String getMessage(SatelliteWrapper satelliteWrapper){

        int msgSize = getMessageSize(satelliteWrapper);
        List<String> m1 = satelliteWrapper.getSatellites().get(0).getMessage();
        List<String> m2 = satelliteWrapper.getSatellites().get(1).getMessage();
        List<String> m3 = satelliteWrapper.getSatellites().get(2).getMessage();

        int size1 = satelliteWrapper.getSatellites().get(0).getMessage().size();
        int size2 = satelliteWrapper.getSatellites().get(1).getMessage().size();
        int size3 = satelliteWrapper.getSatellites().get(2).getMessage().size();
        int to1 = size1 - msgSize;
        int to2 = size2 - msgSize;
        int to3 = size3 - msgSize;

        return completeMessage(m1.subList(to1,m1.size()),m2.subList(to2,m2.size()),m3.subList(to3,m3.size()));
    }

    public int getMessageSize(SatelliteWrapper satelliteWrapper){
        List<String> listWords = new ArrayList<String>();
        for( Satellite s : satelliteWrapper.getSatellites()){
            listWords = Stream.concat(listWords.stream(), s.getMessage().stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        listWords.remove("");
        return listWords.size();
    }

    public void removeFirstMsgPhrase(SatelliteWrapper satelliteWrapper){

        satelliteWrapper.getSatellites().stream().forEach( s -> s.getMessage().remove(0));
    }

}
