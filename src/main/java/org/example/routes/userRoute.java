package org.example.routes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.controllers.userControllers;
import org.example.helper.authHelper;
import org.example.model.user.allCategory;

@Path("/user")
public class userRoute {

    @GET
    public String test(){
        return "test";
    }

    @Path("/categories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories(@Context HttpHeaders httpHeaders){
        userControllers user = new userControllers();
        authHelper auth = new authHelper();
        int account_id = auth.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        allCategory result = user.getAllCategories(account_id);
        return Response.status(200).entity(result).build();
    }
}
