package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

import java.util.List;
import java.util.Map;

public interface ActorController {

    NetflixResponse<List<ActorRest>> getActors() throws NetflixException;

    NetflixResponse<ActorRest> createActor(ActorRest ActorRest) throws NetflixException;

    NetflixResponse<ActorRest> getActorById(Long id) throws NetflixException;

    void deleteActorById(Long id) throws NetflixException;

    NetflixResponse<ActorRest> modifyActorById(Long id, String name, String lastName, int age) throws NetflixException;
}
