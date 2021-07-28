package org.example.routes.filters;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.example.helper.authHelper;
import org.example.model.messageModel;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;


@Provider
public class securityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    private static final String SECURED_URL_PREFIX_1 = "auth/logout";

    private static final String UNSECURED_URL_PREFIX_1 = "/";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX_1)){
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if(authHeader != null && authHeader.size() > 0){
                try {
                    String authToken = authHeader.get(0);
                    authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                    byte[] decodedBytes = Base64.getDecoder().decode(authToken);
                    String decodedString = new String(decodedBytes);
                    StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                    String email = tokenizer.nextToken();
                    int account_id = Integer.parseInt(tokenizer.nextToken());
                    authHelper auth = new authHelper();
                    if(auth.checkloggedIn(email, account_id)){
                        return;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new messageModel("User cannot access the resource"))
                    .build();

            requestContext.abortWith(unauthorizedStatus);
        }


    }
}
