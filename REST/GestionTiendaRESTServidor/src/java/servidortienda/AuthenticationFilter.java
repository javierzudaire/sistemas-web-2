/*
 *  Javier Zudaire
 */
package servidortienda;

import java.io.IOException;
import java.security.Principal;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author javierzudaire
 */
@Authenticate
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the Authorization header from the request
        String authorizationHeader
                = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        JwtTokenUtil token = new JwtTokenUtil();

        // Extract the token from the Authorization header
        String request_token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        System.out.print("Nueva petición");

        if (token.validateToken(request_token) == false) {
            abortWithUnauthorized(requestContext);
            System.out.print("Token not valid");
            return;
        }

        token.getUser(request_token);

        final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
        requestContext.setSecurityContext(new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                System.out.print(token.getUser(request_token) + " ha accedido a la aplicación");
                return () -> token.getUser(request_token);
            }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return currentSecurityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return AUTHENTICATION_SCHEME;
            }
        });

//        try {
//
//            // Validate the token
//            System.out.println("HHHEHHEEH");
//            System.out.println("AQUI" + token);
//            validateToken(token);
//
//        } catch (Exception e) {
//            abortWithUnauthorized(requestContext);
//        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .status(401)
                        .build());
    }

}

//@Provider
//@PreMatching
//public class AuthenticationFilter {
//
//    public void hola() {
//        System.out.println("Hola");
//    }
//
//    public String filter(@Context HttpHeaders headers) {
//        List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
//
//        if (authHeaders.get(0) == null) {
//            return "hola";
//        }
//
//        return "nono";
//    }
//
//}
