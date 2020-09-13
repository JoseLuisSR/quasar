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


    public String getMessage(SatelliteWrapper satelliteWrapper){

        int msgSize = getMsgSize(satelliteWrapper);
        removeGap(satelliteWrapper, msgSize);
        return completeMessage(satelliteWrapper);

    }

    public int getMsgSize(SatelliteWrapper satelliteWrapper){
        List<String> listWords = new ArrayList<String>();
        for( Satellite s : satelliteWrapper.getSatellites()){
            listWords = Stream.concat(listWords.stream(), s.getMessage().stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        listWords.remove("");
        return listWords.size();
    }

    public void removeGap(SatelliteWrapper satelliteWrapper, int msgSize){
        satelliteWrapper.getSatellites().stream()
                                        .forEach( s -> s.getMessage().subList(s.getMessage().size() - msgSize, s.getMessage().size()));
    }

    public String completeMessage(SatelliteWrapper satelliteWrapper){
        String phrase = "";
        for(Satellite s : satelliteWrapper.getSatellites()){

            if(s.getMessage().size()>0 && !s.getMessage().get(0).equals("") ){
                phrase = s.getMessage().get(0);
                removeFirstMsgPhrase(satelliteWrapper);
                return  phrase + " " + completeMessage(satelliteWrapper);

            }if (!phrase.isEmpty()) {
                break;
            }

            phrase = "";

        }
        return "";
    }

    public void removeFirstMsgPhrase(SatelliteWrapper satelliteWrapper){

        satelliteWrapper.getSatellites().stream().forEach( s -> s.getMessage().remove(0));
    }

}
