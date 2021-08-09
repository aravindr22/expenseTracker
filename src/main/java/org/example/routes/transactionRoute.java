package org.example.routes;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.controllers.transactionControllers;
import org.example.database.dbConnector;
import org.example.helper.authHelper;
import org.example.model.message.messageModel;
import org.example.model.transaction.transactionViewModel;

import java.sql.*;

@Path("/transaction")
public class transactionRoute {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(@Context HttpHeaders httpHeaders){
        Integer page = 1;
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        transactionControllers transactionC = new transactionControllers();
        return Response.status(200).entity(transactionC.getAllTransactions(account_id, page)).build();
    }

    @Path("/page")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionByPage(@Context HttpHeaders httpHeaders, @QueryParam("page") Integer page){
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        transactionControllers transactionC = new transactionControllers();
        return Response.status(200).entity(transactionC.getAllTransactions(account_id, page)).build();
    }

    @Path("/category")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionByCategory(@Context HttpHeaders httpHeaders, @QueryParam("category") String category, @QueryParam("page") Integer page){
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        transactionControllers transactionC = new transactionControllers();
        return Response.status(200).entity(transactionC.transactionListByCategory(account_id,page,category)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTransaction(@Context HttpHeaders httpHeaders, transactionViewModel data) {
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        transactionControllers transactionC = new transactionControllers();
        String resultMessage = transactionC.addTransaction(account_id, data);
        return Response.status(Response.Status.ACCEPTED).entity(new messageModel(resultMessage)).build();
    }

    @Path("/stats")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStats(@Context HttpHeaders httpHeaders){
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        transactionControllers transactionC = new transactionControllers();
        return Response.status(Response.Status.ACCEPTED).entity(transactionC.getTransactionStats(account_id)).build();
    }

    @Path("/transactionstats")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionStats(@Context HttpHeaders httpHeaders){
        authHelper authH = new authHelper();
        int account_id = authH.decodeAccountID(httpHeaders.getHeaderString("Authorization"));
        transactionControllers transactionC = new transactionControllers();
        return  Response.status(200).entity(transactionC.getTransactionCategoryWiseStats(account_id)).build();
    }

}
