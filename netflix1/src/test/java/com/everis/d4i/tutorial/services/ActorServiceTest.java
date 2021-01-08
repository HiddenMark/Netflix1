package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.impl.ActorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActorServiceTest {

    @InjectMocks
    private ActorServiceImpl actorService;

    @Mock
    private ActorRepository actorRepository;

    private ModelMapper modelMapper;

    @Test
    public void getActorsTest() throws Exception{

        when(actorRepository.findAll()).thenReturn(
                Arrays.asList(new Actor(1l, "Marcos", "Gomez", 21),
                        new Actor(2l, "test", "3", 22))
        );

        List<ActorRest> actorRestList = actorService.getActors();

        assertEquals("Marcos", actorRestList.get(0).getName());
    }

    @Test
    public void getActorByIdTest() throws Exception {
        when(actorRepository.getOne(1l)).thenReturn(
                new Actor(1l, "Marcos", "Gomez", 21)
        );

        ActorRest actorRest = actorService.getActorById(1L);

        assertEquals("Marcos", actorRest.getName());
    }

    @Test
    public void createActorTest() throws Exception{
        when(actorRepository.save(new Actor(20l, "TESTUSER", "TEST",22)))
                .thenReturn(new Actor(1l,"Marcos", "Gomez", 21));

        ActorRest actorRest = actorService.getActorById(1l);

        assertEquals("Marcos", actorRest.getName());
    }

}
