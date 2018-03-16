package com.fryd.sdk.provider;

import com.github.scribejava.core.builder.api.ClientAuthenticationType;
import com.github.scribejava.core.builder.api.DefaultApi20;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public abstract class AbstractFrydProvider extends DefaultApi20 {

    @Override
    public ClientAuthenticationType getClientAuthenticationType() {
        return ClientAuthenticationType.REQUEST_BODY;
    }

    public abstract String getAPIBaseURL();

}
