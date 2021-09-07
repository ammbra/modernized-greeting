package org.acme.experience;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
public class GreetingResource {

    public static final String URI = "uri";
    public static final String API_GREET = "api.greet";

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Timed(value = "greetings.creation", longTask = true, extraTags = {URI, API_GREET})
    public Message create(Message message) {
         Message.persist(message);
         return message;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(value = "http.get.requests", extraTags = {URI, API_GREET})
    public List<Message> findAll() {
        return Message.findAll().list();
    }

}