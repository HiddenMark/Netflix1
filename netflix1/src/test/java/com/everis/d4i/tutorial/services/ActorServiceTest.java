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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        when(actorRepository.getOne(anyLong())).thenReturn(
                new Actor(1l, "Marcos", "Gomez", 21)
        );

        ActorRest actorRest = actorService.getActorById(1L);

        assertEquals("Marcos", actorRest.getName());
    }

    @Test
    public void createActorTest() throws Exception{
        when(actorRepository.save(any(Actor.class)))
                .thenReturn(new Actor(1l,"Marcos", "Gomez", 21));

        ActorRest actorRest = actorService.createActor(new ActorRest(1l, "Marcos", "Gomez", 21));

        assertEquals("Marcos", actorRest.getName());
    }

    @Test
    public void deleteActorByIdTest() throws Exception{
        doNothing().when(actorRepository).deleteById(anyLong());
        actorService.deleteActorById(anyLong());
        verify(actorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void modifyActorByIdTest() throws Exception{
        when(actorRepository.getOne(anyLong())).
                thenReturn(new Actor(20l, "Marcos", "Gomez", 21));

        when(actorRepository.save(any(Actor.class)))
                .thenReturn(new Actor(20l, "Marcos", "Gomez", 21));

        ActorRest actorRest = actorService.modifyActorById(1l, "-", "-", 25);

        assertEquals("Marcos", actorRest.getName());
    }

}
