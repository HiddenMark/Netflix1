package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;

import java.util.List;

public interface ActorService {

    List<ActorRest> getActors() throws NetflixException;

    ActorRest createActor(ActorRest ActorRest) throws NetflixException;

    ActorRest getActorById(Long id) throws NetflixException;

    void deleteActorById(Long id) throws NetflixException;

    ActorRest modifyActorById(Long id, String name, String lastName, int age) throws NetflixException;
}
