package com.fryd.sdk.provider;

/**
 * Provider for fryd TestProduction Environment
 *
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydProdTestOauthProvider extends AbstractFrydProvider {

    protected FrydProdTestOauthProvider() {
    }

    private static class InstanceHolder {
        private static final FrydProdTestOauthProvider INSTANCE = new FrydProdTestOauthProvider();
    }

    public static FrydProdTestOauthProvider instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAPIBaseURL() {
        return "https://apitest.fryd.zone/api";
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://apitest.fryd.zone/auth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://apitest.fryd.zone/auth";
    }

}
