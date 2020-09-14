package com.rebell.alliance.intelligence;

import com.rebell.alliance.intelligence.exceptions.MessageException;
import com.rebell.alliance.intelligence.services.MessageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void getMessageWith3Satellites() throws MessageException {
        List<List<String>> messages = new ArrayList<List<String>>();
        String [] m1 = {"este", "", "", "mensaje", ""};
        String [] m2 = {"", "es", "", "", "secreto"};
        String [] m3 = {"este", "", "un", "", ""};
        messages.add(Arrays.stream(m1).collect(Collectors.toList()));
        messages.add(Arrays.stream(m2).collect(Collectors.toList()));
        messages.add(Arrays.stream(m3).collect(Collectors.toList()));
        String message = messageService.getMessage(messages);
        String expectedMsg = "este es un mensaje secreto";
        assertEquals(message,expectedMsg);
    }

    @Test
    public void getMessageWith3SatellitesError(){
        List<List<String>> messages = new ArrayList<List<String>>();
        String [] m1 = {"este", "", "", "mensaje", ""};
        String [] m2 = {"", "es", "", "", "secreto"};
        String [] m3 = {"este", "", "un", "", "",""};
        messages.add(Arrays.stream(m1).collect(Collectors.toList()));
        messages.add(Arrays.stream(m2).collect(Collectors.toList()));
        messages.add(Arrays.stream(m3).collect(Collectors.toList()));
        try {
            String message = messageService.getMessage(messages);
        }catch (MessageException e){
            assertEquals("No se puede conocer el mensaje",e.getMessage());
        }
    }

    @Test
    public void getMessageWith4Satellites() throws MessageException {
        List<List<String>> messages = new ArrayList<List<String>>();
        String [] m1 = {"este", "", "", "mensaje", ""};
        String [] m2 = {"", "es", "", "", "secreto"};
        String [] m3 = {"este", "", "un", "", ""};
        String [] m4 = {"", "", "un", "", "secreto"};
        messages.add(Arrays.stream(m1).collect(Collectors.toList()));
        messages.add(Arrays.stream(m2).collect(Collectors.toList()));
        messages.add(Arrays.stream(m3).collect(Collectors.toList()));
        messages.add(Arrays.stream(m4).collect(Collectors.toList()));
        String message = messageService.getMessage(messages);
        String expectedMsg = "este es un mensaje secreto";
        assertEquals(message,expectedMsg);
    }
}
