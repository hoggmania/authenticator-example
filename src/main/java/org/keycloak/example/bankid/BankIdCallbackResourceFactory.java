package org.keycloak.example.bankid;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

/**
 * Created by st on 10/07/17.
 */
public class BankIdCallbackResourceFactory implements RealmResourceProviderFactory {

    public RealmResourceProvider create(KeycloakSession session) {
        return new BankIdCallbackResource(session);
    }

    public void init(org.keycloak.Config.Scope scope) {
    }

    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
    }

    public void close() {
    }

    public String getId() {
        return "bankid-callback";
    }
}
