package com.fryd.sdk.service;

import com.fryd.sdk.model.APIResponse;
import com.fryd.sdk.model.Location;
import com.fryd.sdk.model.Trophylist;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
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

    private APIResponse<List<Trophylist>> transformTrophylistResponse(APIResponse<Trophylist.Trophylists> oldResponse) {
        Trophylist.Trophylists trophylists = oldResponse.getFrydDataType();
        List<Trophylist> trophies = null;
        if (trophylists != null) {
            trophies = trophylists.getTrophylists();
        }
        return new APIResponse<>(trophies, oldResponse.getType(), oldResponse.getMessage(), oldResponse.getUid());
    }

}

