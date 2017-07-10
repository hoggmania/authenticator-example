package org.keycloak.example.bankid;

import org.keycloak.models.ClientSessionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserSessionModel;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by st on 10/07/17.
 */
public class BankIdCallbackResource implements RealmResourceProvider {

    private KeycloakSession session;

    public BankIdCallbackResource(KeycloakSession session) {
        this.session = session;
    }

    public Object getResource() {
        return this;
    }

    @POST
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public void bankdIdCallback(@FormParam("requestId") String requestId, @FormParam("sessionId") String sessionId) {
        ClientSessionModel session = this.session.sessions().getClientSession(sessionId);
        String note = session.getNote("requestId");
        if (note != null && note.equals(requestId)) {
            session.setNote("requestId", "DONE");
            System.out.println("RECEIVED CALLBACK " + requestId);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    public void close() {
    }
}
