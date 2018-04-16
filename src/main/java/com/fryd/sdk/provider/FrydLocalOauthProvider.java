package com.fryd.sdk.provider;

/**
 * Provider for fryd Local Testing Environment
 *
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydLocalOauthProvider extends AbstractFrydProvider {

    protected FrydLocalOauthProvider() {
    }

    @Override
    public String getAPIBaseURL() {
        return "http://localhost:8443/api";
    }

    private static class InstanceHolder {
        private static final FrydLocalOauthProvider INSTANCE = new FrydLocalOauthProvider();
    }

    public static FrydLocalOauthProvider instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "http://localhost:8443/auth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "http://localhost:8443/auth";
    }

}
