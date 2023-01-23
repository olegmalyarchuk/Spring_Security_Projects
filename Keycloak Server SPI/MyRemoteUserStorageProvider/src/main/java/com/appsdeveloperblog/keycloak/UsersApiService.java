package com.appsdeveloperblog.keycloak;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public interface UsersApiService {

    @GET
    @Path("/{username}")
    User getUserDetails(@PathParam("username") String username);

}
