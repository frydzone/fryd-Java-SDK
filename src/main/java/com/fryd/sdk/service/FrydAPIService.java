package com.fryd.sdk.service;

import com.fryd.sdk.model.*;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

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

    public APIResponse<Location> getLocationById(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/location");
        request.setPayload("{\"location_id\":\""+locationId+"\"}");
        return handleRequest(new Location(), request);
    }

    public Future<APIResponse<Location>> getLocationByIdAsync(OAuth2AccessToken appAccessToken, String locationId) {
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

    public APIResponse<List<Trophylist>> getTrophylistsFromLocation(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/trophylists");
        request.setPayload("{\"location_id\":\""+locationId+"\"}");
        return transformTrophylistResponse(handleRequest(new Trophylist.Trophylists(), request));
    }

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

    public APIResponse<List<Trophy>> getTrophiesOfList(OAuth2AccessToken appAccessToken, String trophylistId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/trophies");
        request.setPayload("{\"trophylist_id\":\""+trophylistId+"\"}");
        return transformTrophiesResponse(handleRequest(new Trophy.Trophies(), request));
    }

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

    public APIResponse<Trophy> getTrophyById(OAuth2AccessToken appAccessToken, String trophyId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/trophy");
        request.setPayload("{\"trophy_id\":\""+trophyId+"\"}");
        return handleRequest(new Trophy(), request);
    }

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

    public APIResponse<User> getUserInformation(OAuth2AccessToken userAccessToken) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(userAccessToken, "/user");
        return handleRequest(new User(), request);
    }

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

    public APIResponse<String> triggerTrophyProgress(OAuth2AccessToken userAccessToken, OAuth2AccessToken appAccessToken, String locationId, String secret) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = new OAuthRequest(Verb.POST, BASE_API_URL+"/trophy/check");
        request.addHeader("app_token", appAccessToken.getAccessToken());
        request.addHeader("user_token", userAccessToken.getAccessToken());
        request.setPayload("{\"location_id\":\""+locationId+"\", \"secret\":\""+secret+"\"}");

        return handleRequest("", request);
    }

    public Future<APIResponse<String>> triggerTrophyProgressAsync(OAuth2AccessToken userAccessToken, OAuth2AccessToken appAccessToken, String locationId, String secret) {
        return CompletableFuture.supplyAsync(() -> {
            APIResponse<String> response = null;
            try {
                OAuthRequest request = new OAuthRequest(Verb.POST, BASE_API_URL+"/trophy/check");
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

