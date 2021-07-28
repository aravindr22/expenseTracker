package org.example.routes;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.controllers.authControllers;
import org.example.helper.authHelper;
import org.example.model.loginModel;
import org.example.model.messageModel;
import org.example.model.registerModel;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Path("auth")
public class authRoute {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int test(){
        authHelper auth = new authHelper();
        int val = auth.getIDFromDB("test@gmail.com", "123456");
        return val;
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
        String code, pass, header;
        try {
            code = data.getEmail() + ":" + data.getPassword();
            pass = Base64.getEncoder().encodeToString(code.getBytes(StandardCharsets.UTF_8));
            header = "Basic " + pass;
            return Response.status(200).entity(header).build();
            //return header.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200)
                .entity("header")
                .build();
    }
}
