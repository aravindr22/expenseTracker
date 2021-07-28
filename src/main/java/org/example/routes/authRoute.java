package org.example.routes;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.controllers.authControllers;
import org.example.helper.authHelper;
import org.example.model.auth.loginModel;
import org.example.model.message.messageIdModel;
import org.example.model.message.messageModel;
import org.example.model.auth.registerModel;


@Path("auth")
public class authRoute {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int test(@Context HttpHeaders httpHeaders){
        System.out.println(httpHeaders.getHeaderString("Authorization"));
        return 1;
    }

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(registerModel user){
        messageModel message = null;
        try {
            authControllers auth = new authControllers();
            String result = auth.registerUser(user);
            message = new messageModel(result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Response
                .status(200)
                .entity(message)
                .build();
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(loginModel data){
        authControllers auth = new authControllers();
        messageIdModel message = auth.loginUser(data);
        return Response.status(200)
                .entity(message)
                .build();
    }

    @Path("/logout")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoutUser(@Context HttpHeaders httpHeaders){
        try {
            authHelper auth = new authHelper();
            int account_id = auth.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
            authControllers authC = new authControllers();
            authC.logoutUser(account_id);
            return Response.status(200)
                    .entity(new messageModel("Logout Successful"))
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(200)
                    .entity(new messageModel("Logout Operation Failed"))
                    .build();
        }
    }
}
