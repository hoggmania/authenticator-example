package org.keycloak.example.bankid;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

import java.util.UUID;

/**
 * Created by st on 10/07/17.
 */
public class BankIdAuthenticator implements Authenticator {

    public void authenticate(AuthenticationFlowContext ctx) {
        String requestId = ctx.getClientSession().getNote("requestId");
        if (requestId == null) {
            requestId = UUID.randomUUID().toString();
            ctx.getClientSession().setNote("requestId", requestId);
        } else if (requestId.equals("DONE")) {
            ctx.success();
            return;
        }

        System.out.println("COMPLETE WITH: \ncurl -d \"sessionId=" + ctx.getClientSession().getId() + "&requestId=" + requestId + "\" -X POST http://localhost:8080/auth/realms/" + ctx.getRealm().getName() +  "/bankid-callback");
        ctx.challenge(ctx.form().createForm("waiting.ftl"));
    }

    public void action(AuthenticationFlowContext authenticationFlowContext) {
    }

    public boolean requiresUser() {
        return true;
    }

    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return true;
    }

    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    public void close() {

    }
}
