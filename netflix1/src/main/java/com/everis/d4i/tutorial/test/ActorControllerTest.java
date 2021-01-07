package com.everis.d4i.tutorial.test;

import com.everis.d4i.tutorial.controllers.ActorController;
import com.everis.d4i.tutorial.controllers.impl.ActorControllerImpl;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.services.impl.ActorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ActorController.class)
public class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorService actorService;

    //@MockBean
    //private ActorRepository actorRepository;

    @Test
    public void getActors() throws Exception {
        when(actorService.getActors()).thenReturn(
                Arrays.asList(new ActorRest(1l, "Marcos", "Gomez", 21),
                        new ActorRest(2l, "test", "3", 22))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/netflix/v1/netflix/v1/actors")
                .accept(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Marcos\",\"lastName\":\"Gomez\",\"age\":21},{\"id\":2,\"name\":\"test\",\"lastName\":\"3\",\"age\":22}]"))
                .andReturn();

    }

}
