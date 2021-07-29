package org.example.routes;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.controllers.userControllers;
import org.example.helper.authHelper;
import org.example.model.message.messageModel;
import org.example.model.user.allCategory;
import org.example.model.user.category;
import org.example.model.user.userDetail;

@Path("/user")
public class userRoute {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response userDetail(@Context HttpHeaders httpHeaders){
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        userControllers userC = new userControllers();
        userDetail result = userC.getAllUserData(account_id);
        return Response.status(200).entity(result).build();
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

    @Path("/incomeCategory")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addIncomeCategory(@Context HttpHeaders httpHeaders, category data){
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        userControllers userC = new userControllers();
        String result = userC.addIncomeCategory(account_id, data.getName());
        return Response.status(Response.Status.ACCEPTED).entity(new messageModel(result)).build();
    }

    @Path("/expenseCategory")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addExpenseCategory(@Context HttpHeaders httpHeaders, category data){
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        userControllers userC = new userControllers();
        String result = userC.addExpenseCategory(account_id, data.getName());
        return Response.status(Response.Status.ACCEPTED).entity(new messageModel(result)).build();
    }
}
