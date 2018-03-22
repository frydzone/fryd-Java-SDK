package com.fryd.sdk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fryd.sdk.model.Location;
import com.fryd.sdk.model.Trophylist;
import com.fryd.sdk.provider.AbstractFrydProvider;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Main Service to get everything you need from the fryd API.
 *
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydAPIService {

    private final OAuth20Service oauthService;

    private final String BASE_API_URL;

    public FrydAPIService(OAuth20Service oauthService) {
        this.oauthService = oauthService;
        this.BASE_API_URL = ((AbstractFrydProvider)oauthService.getApi()).getAPIBaseURL();
    }

    public Location getLocationById(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/location");
        request.setPayload("{\"location_id\":\""+locationId+"\"}");
        return handleRequest(new Location(), request);
    }

    public Future<Location> getLocationByIdAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return CompletableFuture.supplyAsync(() -> {
            Location location = null;
            try {
                OAuthRequest request = createRequest(appAccessToken, "/location");
                request.setPayload("{\"location_id\":\""+locationId+"\"}");
                location = handleRequestAsync(new Location(), request);
            } catch (InterruptedException | ExecutionException | IOException ex) {
                // TODO: Add logger
                ex.printStackTrace();
            }
            return location;
        });
    }

    public List<Trophylist> getTrophylistsFromLocationId(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = createRequest(appAccessToken, "/trophylists");
        request.setPayload("{\"location_id\":\""+locationId+"\"}");
        return handleRequest(new Trophylist.Trophylists(), request).getTrophylists();
    }

    public Future<List<Trophylist>> getTrophylistsFromLocationIdAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return CompletableFuture.supplyAsync(() -> {
            List<Trophylist> trophylists = null;
            try {
                OAuthRequest request = createRequest(appAccessToken, "/trophylists");
                request.setPayload("{\"location_id\":\""+locationId+"\"}");
                trophylists = handleRequestAsync(new Trophylist.Trophylists(), request).getTrophylists();
            } catch (InterruptedException | ExecutionException | IOException ex) {
                // TODO: Add logger
                ex.printStackTrace();
            }
            return trophylists;
        });
    }

    private OAuthRequest createRequest(OAuth2AccessToken appAccessToken, String urlAddon) {
        OAuthRequest request = new OAuthRequest(Verb.POST, BASE_API_URL+urlAddon);
        request.addHeader("token", appAccessToken.getAccessToken());

        return request;
    }

    private <T> T handleRequest(T t, OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
        Response response = oauthService.execute(request);
        String responseJson = response.getBody();

        return handleRequestInternal(t, responseJson);
    }

    private <T> T handleRequestAsync(T t, OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
        Future<Response> responseFuture = oauthService.executeAsync(request);
        Response response = responseFuture.get();
        String responseJson = response.getBody();

        return handleRequestInternal(t, responseJson);
    }

    @SuppressWarnings("unchecked")
    private <T> T handleRequestInternal(T t, String responseJson) throws IOException {
        JSONObject responseJsonObj = new JSONObject(responseJson);
        JSONObject json = responseJsonObj.getJSONObject("response");

        ObjectMapper mapper = new ObjectMapper();
        return (T)mapper.readValue(json.toString(), t.getClass());
    }

}

