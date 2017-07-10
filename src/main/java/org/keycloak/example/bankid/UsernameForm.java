package org.keycloak.example.bankid;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.KeycloakModelUtils;

/**
 * Created by st on 10/07/17.
 */
public class UsernameForm implements Authenticator {
    public void authenticate(AuthenticationFlowContext context) {
        context.challenge(context.form().createForm("username.ftl"));
    }

    public void action(AuthenticationFlowContext context) {
        String username = context.getHttpRequest().getDecodedFormParameters().getFirst("username");
        if (username != null) {
            UserModel user = null;
            try {
                user = KeycloakModelUtils.findUserByNameOrEmail(context.getSession(), context.getRealm(), username);
                context.setUser(user);
                context.success();
                return;
            } catch (ModelDuplicateException mde) {
            }
        }

        context.challenge(context.form()
                .setError("User not found")
                .createForm("username.ftl"));
    }

    public boolean requiresUser() {
        return false;
    }

    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {

    }

    public void close() {

    }
}
