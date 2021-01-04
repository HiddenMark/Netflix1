package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorServiceImpl.class);

    @Autowired
    private ActorRepository ActorRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<ActorRest> getActors() throws NetflixException {
        return ActorRepository.findAll().stream().map(Actor -> modelMapper.map(Actor, ActorRest.class))
                .collect(Collectors.toList());
    }

    public ActorRest createActor(ActorRest actorRest) throws NetflixException {
        Actor actor = new Actor();
        actor.setName(actorRest.getName());
        actor.setLastName(actorRest.getLastName());
        actor.setAge(actorRest.getAge());
        try {
            actor = ActorRepository.save(actor);
        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
        }
        return modelMapper.map(actor, ActorRest.class);
    }

    public ActorRest getActorById(Long id) throws NetflixException {
        try {
            return modelMapper.map(ActorRepository.getOne(id), ActorRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }    }

    public void deleteActorById(Long id) throws NetflixException {
        try {
            ActorRepository.deleteById(id);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    public ActorRest modifyActorById(Long id, String name, String lastName, int age) throws NetflixException {
        try {
            Actor actor = ActorRepository.getOne(id);
            actor.setName(name);
            actor.setLastName(lastName);
            actor.setAge(age);
            return modelMapper.map(ActorRepository.save(actor), ActorRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }
}
