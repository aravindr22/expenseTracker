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
}
