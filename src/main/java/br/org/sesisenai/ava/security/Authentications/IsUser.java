package br.org.sesisenai.ava.security.Authentications;


import br.org.sesisenai.ava.security.model.USerDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class IsUser implements AuthorizationManager<RequestAuthorizationContext> {
    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> suplier, RequestAuthorizationContext object) {
        Boolean decision = false;
        USerDetails uSerDetails = (USerDetails) suplier.get().getPrincipal();
        Map<String ,String> variables = object.getVariables();
        Long userid = Long.parseLong(variables.get("id"));
        if (uSerDetails.getUsuario().getId() == userid){
            decision=true;
        }

        return new AuthorizationDecision(decision);
    }
}
