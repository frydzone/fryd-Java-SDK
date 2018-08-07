package zone.fryd.sdk.service;

import zone.fryd.sdk.provider.AbstractFrydProvider;
import zone.fryd.sdk.provider.FrydOauthProvider;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.Getter;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Main Service to get authorization codes and Access Tokens.
 *
 * Basically a fryd styled wrapper for ScribeJava.
 * (https://github.com/scribejava/scribejava)
 *
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydOAuthService {

    private final String CLIENT_ID;

    private final String CLIENT_SECRET;

    private final String RETURN_URL;

    @Getter
    private OAuth20Service oauthService;

    @Getter
    private AbstractFrydProvider oauthProvider;

    public FrydOAuthService(String clientId, String clientSecret, String returnUrl) {
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        this.RETURN_URL = returnUrl;
        this.oauthProvider = FrydOauthProvider.instance();

        createOAuthService();
    }

    public FrydOAuthService(String CLIENT_ID, String clientSecret, String returnUrl, AbstractFrydProvider oauthProvider) {
        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = clientSecret;
        this.RETURN_URL = returnUrl;
        this.oauthProvider = oauthProvider;

        createOAuthService();
    }

    private void createOAuthService() {
        this.oauthService = new ServiceBuilder(this.CLIENT_ID)
                .apiSecret(this.CLIENT_SECRET)
                .callback(this.RETURN_URL)
                .build(this.oauthProvider);
    }

    /**
     * Creates a FrydAPIService which can be used to query the
     * fryd API.
     *
     * @return a brand-new FrydAPIService
     */
    public FrydAPIService createFrydAPIService() {
        return new FrydAPIService(this.oauthService, this.oauthProvider.getAPIBaseURL());
    }

    /**
     * Returns the authorization URL that must be visited to get an
     * Authorization Code... to get a personal Access Token for that User
     *
     * @return the authorization URL
     */
    public String getAuthorizationUrl() {
        return this.oauthService.getAuthorizationUrl();
    }

    /**
     * Gets an Access Token with the Scope "UserInfo" to get
     * Information of the User how created the code
     *
     * @param code
     * @return An Access Token with the scope "UserInfo"
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public OAuth2AccessToken getUserAccessToken(String code) throws InterruptedException, ExecutionException, IOException {
        return this.oauthService.getAccessToken(code);
    }

    /**
     * Gets an Access Token in an async way with the Scope "UserInfo" to get
     * Information of the User how created the code.
     *
     * @param code
     * @return An Access Token with the scope "UserInfo"
     */
    public Future<OAuth2AccessToken> getUserAccessTokenAsync(String code) {
        return this.oauthService.getAccessTokenAsync(code);
    }

    /**
     * Gets an Access Token with the Scope "AppInfo" to get
     * Information of your fryd Spots and everything about them
     *
     * @return An Access Token with the scope "AppInfo"
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public OAuth2AccessToken getAppAccessToken() throws InterruptedException, ExecutionException, IOException {
        return this.oauthService.getAccessTokenClientCredentialsGrant();
    }

    /**
     * Gets an Access Token in an async way with the Scope "AppInfo" to get
     * Information of your fryd Spots and everything about them
     *
     * @return An Access Token with the scope "AppInfo"
     */
    public Future<OAuth2AccessToken> getAppAccessTokenAsync() {
        return this.oauthService.getAccessTokenClientCredentialsGrantAsync();
    }

    /**
     * Gets an Access Token with the Scope "AppInfo" or "UserInfo"
     * depending on the provided token, using an existing Token.
     *
     * @return An Access Token with the scope "AppInfo" or "UserInfo"
     */
    public OAuth2AccessToken refreshToken(OAuth2AccessToken anyAccessToken) throws InterruptedException, ExecutionException, IOException {
        return this.oauthService.refreshAccessToken(anyAccessToken.getRefreshToken());
    }

    /**
     * Gets an Access Token, in an async way, with the Scope "AppInfo" or "UserInfo"
     * depending on the provided token, using an existing Token.
     *
     * @return An Access Token with the scope "AppInfo" or "UserInfo"
     */
    public Future<OAuth2AccessToken> refreshTokenAsync(OAuth2AccessToken anyAccessToken) {
        return this.oauthService.refreshAccessTokenAsync(anyAccessToken.getRefreshToken());
    }

}
