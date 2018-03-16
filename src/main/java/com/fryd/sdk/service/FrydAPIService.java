package com.fryd.sdk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fryd.sdk.model.Location;
import com.fryd.sdk.provider.AbstractFrydProvider;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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
        OAuthRequest request = new OAuthRequest(Verb.POST, BASE_API_URL+"/location");
        request.addHeader("token", appAccessToken.getAccessToken());
        request.setPayload("{\"location_id\":\""+locationId+"\"}");

        Response response = oauthService.execute(request);
        String responseJson = response.getBody();

        JSONObject responseJsonObj = new JSONObject(responseJson);
        JSONObject locationJson = responseJsonObj.getJSONObject("response");

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(locationJson.toString(), Location.class);
    }


}

