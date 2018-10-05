package zone.fryd.sdk.service;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import zone.fryd.sdk.model.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

/**
 * Main Service to get everything you need from the fryd API.
 *
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydAPIService extends AbstractFrydAPIService {

    public FrydAPIService(OAuth20Service oauthService, String baseAPIUrl) {
        super(oauthService, baseAPIUrl);
    }

    /**
     * Gets the Location Information (also called fryd Spot Information)
     * that is part of your Account
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param locationId The id of the location/fryd Spot you own
     * @return The location you have asked for
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<Location> getFrydSpotById(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/location");
        request.setPayload("{\"location_id\":\""+locationId+"\"}");
        return handleRequest(new Location(), request);
    }

    /**
     * Gets the Location Information (also called fryd Spot Information)
     * that is part of your Account
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param locationId     The id of the location/fryd Spot you own
     * @param fields         The fields you want in your result
     * @return The location you have asked for
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<Location> getFrydSpotById(OAuth2AccessToken appAccessToken, String locationId, String... fields) throws InterruptedException, ExecutionException, IOException {
        String fieldsString = createFieldsString(fields);
        OAuthRequest request = createRequest(appAccessToken, "/location?fields=" + fieldsString);
        request.setPayload("{\"location_id\":\"" + locationId + "\"}");
        return handleRequest(new Location(), request);
    }

    /**
     * Gets Location Information (also called fryd Spot Information)
     * that is part of your Account in an asynchronous way
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param locationId The id of the location/fryd Spot you own
     * @return The location you have asked for
     */
    public Future<APIResponse<Location>> getFrydSpotByIdAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<Location> location = null;
            try {
                OAuthRequest request = createRequest(appAccessToken, "/location");
                request.setPayload("{\"location_id\":\""+locationId+"\"}");
                location = handleRequestAsync(new Location(), request);
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to get location by id", ex);
            }
            return location;
        });
    }

    /**
     * Gets Location Information (also called fryd Spot Information)
     * that is part of your Account in an asynchronous way
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param locationId     The id of the location/fryd Spot you own
     * @param fields         The fields you want in your result
     * @return The location you have asked for
     */
    public Future<APIResponse<Location>> getFrydSpotByIdAsync(OAuth2AccessToken appAccessToken, String locationId, String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<Location> location = null;
            try {
                String fieldsString = createFieldsString(fields);
                OAuthRequest request = createRequest(appAccessToken, "/location?fields=" + fieldsString);
                request.setPayload("{\"location_id\":\"" + locationId + "\"}");
                location = handleRequestAsync(new Location(), request);
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to get location by id", ex);
            }
            return location;
        });
    }

    /**
     * Gets a list of trophylists (including events) of your Location
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param locationId The id of the location/fryd Spot you own
     * @return A list of trophylists
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<List<Trophylist>> getTrophylistsFromLocation(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/trophylists");
        request.setPayload("{\"location_id\":\""+locationId+"\"}");
        return transformTrophylistResponse(handleRequest(new Trophylist.Trophylists(), request));
    }

    /**
     * Gets a list of trophylists (including events) of your Location
     * in an asynchronous way
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param locationId The id of the location/fryd Spot you own
     * @return A list of trophylists
     */
    public Future<APIResponse<List<Trophylist>>> getTrophylistsFromLocationAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<List<Trophylist>> trophylists = null;
            try {
                OAuthRequest request = createRequest(appAccessToken, "/trophylists");
                request.setPayload("{\"location_id\":\""+locationId+"\"}");
                trophylists = transformTrophylistResponse(handleRequestAsync(new Trophylist.Trophylists(), request));
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to get trophylists", ex);
            }
            return trophylists;
        });
    }

    /**
     * Gets a list of all the trophies of a trophylist/event
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param trophylistId An id of a trophylist/event of your location
     * @return A list of trophies part of a trophylist
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<List<Trophy>> getTrophiesOfList(OAuth2AccessToken appAccessToken, String trophylistId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/trophies");
        request.setPayload("{\"trophylist_id\":\""+trophylistId+"\"}");
        return transformTrophiesResponse(handleRequest(new Trophy.Trophies(), request));
    }

    /**
     * Gets a list of all the trophies of a trophylist/event in an asynchronous way
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param trophylistId An id of a trophylist/event of your location
     * @return A list of trophies part of a trophylist
     */
    public Future<APIResponse<List<Trophy>>> getTrophiesOfListAsync(OAuth2AccessToken appAccessToken, String trophylistId) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<List<Trophy>> trophies = null;
            try {
                OAuthRequest request = createRequest(appAccessToken, "/trophies");
                request.setPayload("{\"trophylist_id\":\""+trophylistId+"\"}");
                trophies = transformTrophiesResponse(handleRequestAsync(new Trophy.Trophies(), request));
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to get trophies of trophylist", ex);
            }
            return trophies;
        });
    }

    /**
     * Gets trophy information of a trophy from one of your trophylists/events
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param trophyId An id of a trophy of one of your trophylists/events
     * @return A trophy
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<Trophy> getTrophyById(OAuth2AccessToken appAccessToken, String trophyId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/trophy");
        request.setPayload("{\"trophy_id\":\""+trophyId+"\"}");
        return handleRequest(new Trophy(), request);
    }

    /**
     * Gets trophy information of a trophy from one of your trophylists/events
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param trophyId       An id of a trophy of one of your trophylists/events
     * @param fields         The fields you want in your result
     * @return A trophy
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<Trophy> getTrophyById(OAuth2AccessToken appAccessToken, String trophyId, String... fields) throws InterruptedException, ExecutionException, IOException {
        String fieldsString = createFieldsString(fields);
        OAuthRequest request = createRequest(appAccessToken, "/trophy?fields=" + fieldsString);
        request.setPayload("{\"trophy_id\":\"" + trophyId + "\"}");
        return handleRequest(new Trophy(), request);
    }

    /**
     * Gets trophy information of a trophy from one of your trophylists/events
     * in an asynchronous way
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param trophyId An id of a trophy of one of your trophylists/events
     * @return A trophy
     */
    public Future<APIResponse<Trophy>> getTrophyByIdAsync(OAuth2AccessToken appAccessToken, String trophyId) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<Trophy> trophy = null;
            try {
                OAuthRequest request = createRequest(appAccessToken, "/trophy");
                request.setPayload("{\"trophy_id\":\""+trophyId+"\"}");
                trophy = handleRequestAsync(new Trophy(), request);
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to get trophy by id", ex);
            }
            return trophy;
        });
    }

    /**
     * Gets trophy information of a trophy from one of your trophylists/events
     * in an asynchronous way
     *
     * @param appAccessToken A Token with the scope "AppInfo"
     * @param trophyId       An id of a trophy of one of your trophylists/events
     * @param fields         The fields you want in your result
     * @return A trophy
     */
    public Future<APIResponse<Trophy>> getTrophyByIdAsync(OAuth2AccessToken appAccessToken, String trophyId, String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<Trophy> trophy = null;
            try {
                String fieldsString = createFieldsString(fields);
                OAuthRequest request = createRequest(appAccessToken, "/trophy?fields=" + fieldsString);
                request.setPayload("{\"trophy_id\":\"" + trophyId + "\"}");
                trophy = handleRequestAsync(new Trophy(), request);
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to get trophy by id", ex);
            }
            return trophy;
        });
    }

    /**
     * Gets the Userinformation of the user whos token this is
     *
     * @param userAccessToken A Token with the scope "UserInfo"
     * @return A user
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<User> getUserInformation(OAuth2AccessToken userAccessToken) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(userAccessToken, "/user");
        return handleRequest(new User(), request);
    }

    /**
     * Gets the Userinformation of the user whos token this is
     *
     * @param userAccessToken A Token with the scope "UserInfo"
     * @param fields          The fields you want in your result
     * @return A user
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<User> getUserInformation(OAuth2AccessToken userAccessToken, String... fields) throws InterruptedException, ExecutionException, IOException {
        String fieldsString = createFieldsString(fields);
        OAuthRequest request = createRequest(userAccessToken, "/user?fields=" + fieldsString);
        return handleRequest(new User(), request);
    }

    /**
     * Gets the Userinformation of the user whos token this is in an asynchronous way
     *
     * @param userAccessToken A Token with the scope "UserInfo"
     * @return A user
     */
    public Future<APIResponse<User>> getUserInformationAsync(OAuth2AccessToken userAccessToken) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<User> user = null;
            try {
                OAuthRequest request = createRequest(userAccessToken, "/user");
                user = handleRequestAsync(new User(), request);
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to get user information", ex);
            }
            return user;
        });
    }

    /**
     * Gets the Userinformation of the user whos token this is in an asynchronous way
     *
     * @param userAccessToken A Token with the scope "UserInfo"
     * @param fields          The fields you want in your result
     * @return A user
     */
    public Future<APIResponse<User>> getUserInformationAsync(OAuth2AccessToken userAccessToken, String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<User> user = null;
            try {
                String fieldsString = createFieldsString(fields);
                OAuthRequest request = createRequest(userAccessToken, "/user?fields=" + fieldsString);
                user = handleRequestAsync(new User(), request);
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to get user information", ex);
            }
            return user;
        });
    }

    /**
     * Sends a secret to fryd to trigger a trophy
     *
     * @param userAccessToken A Token with the scope "UserInfo" of the user who achieved the trophy
     * @param appAccessToken A Token with the scope "AppInfo" of your location
     * @param locationId The id of the location/fryd Spot you own
     * @param secret The secret of the trophy to be achieved
     * @return Just a response with a message or error (hopefully not)
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public APIResponse<String> triggerTrophyProgress(OAuth2AccessToken userAccessToken, OAuth2AccessToken appAccessToken, String locationId, String secret) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = new OAuthRequest(Verb.POST, BASE_API_URL + "/trophycheck");
        request.addHeader("app_token", appAccessToken.getAccessToken());
        request.addHeader("user_token", userAccessToken.getAccessToken());
        request.setPayload("{\"location_id\":\""+locationId+"\", \"secret\":\""+secret+"\"}");

        return handleRequest("", request);
    }

    /**
     * Sends a secret to fryd to trigger a trophy in an asynchronous way
     *
     * @param userAccessToken A Token with the scope "UserInfo" of the user who achieved the trophy
     * @param appAccessToken A Token with the scope "AppInfo" of your location
     * @param locationId The id of the location/fryd Spot you own
     * @param secret The secret of the trophy to be achieved
     * @return Just a response with a message or error (hopefully not)
     */
    public Future<APIResponse<String>> triggerTrophyProgressAsync(OAuth2AccessToken userAccessToken, OAuth2AccessToken appAccessToken, String locationId, String secret) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<String> response = null;
            try {
                OAuthRequest request = new OAuthRequest(Verb.POST, BASE_API_URL + "/trophycheck");
                request.addHeader("app_token", appAccessToken.getAccessToken());
                request.addHeader("user_token", userAccessToken.getAccessToken());
                request.setPayload("{\"location_id\":\""+locationId+"\", \"secret\":\""+secret+"\"}");

                response = handleRequestAsync("", request);
            } catch (InterruptedException | ExecutionException | IOException ex) {
                logger.log(Level.WARNING, "Failed to trigger trophy", ex);
            }
            return response;
        });
    }
}

