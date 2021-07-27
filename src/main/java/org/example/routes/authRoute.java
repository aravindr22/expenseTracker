package org.example.routes;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.controllers.authControllers;
import org.example.model.registerModel;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("auth")
public class authRoute {

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String registerUser(registerModel user){
        try {
            authControllers auth = new authControllers();
            int result = auth.registerUser(user);
            System.out.println(result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "Done";
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(String email){
        String password = "123456";
        System.out.println(email);
        System.out.println(password);
        String code, pass, header;
        try {
            code = email + ":" + password;
            pass = Base64.getEncoder().encodeToString(code.getBytes(StandardCharsets.UTF_8));
            header = "Basic " + pass;
            return Response.status(200).entity(header).build();
            //return header.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity("header").build();
    }
}
