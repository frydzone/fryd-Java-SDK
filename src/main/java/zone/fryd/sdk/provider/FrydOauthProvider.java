package zone.fryd.sdk.provider;

/**
 * Basic Provider for fryd Production Environment
 *
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydOauthProvider extends AbstractFrydProvider {

    protected FrydOauthProvider() {
    }

    private static class InstanceHolder {
        private static final FrydOauthProvider INSTANCE = new FrydOauthProvider();
    }

    @Override
    public String getAPIBaseURL() {
        return "https://api.fryd.zone/api";
    }

    public static FrydOauthProvider instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.fryd.zone/auth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://api.fryd.zone/auth";
    }

}
