package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/api")
public class ApiService {
    @Inject
    private MyMessagingApplication messagingApplication;

    @POST
    @Path("/")
    public Response testMessaging(){
        messagingApplication.sendMsg(1);
        return Response.created(URI.create("/1")).build();
    }


}
