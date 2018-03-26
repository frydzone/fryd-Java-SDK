package com.fryd.sdk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fryd.sdk.model.APIResponse;
import com.fryd.sdk.model.Location;
import com.fryd.sdk.model.Trophylist;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public abstract class AbstractFrydAPIService {

    protected final static Logger logger = Logger.getLogger(FrydAPIService.class.getName());

    private ObjectMapper mapper = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    private final OAuth20Service oauthService;

    @Getter
    private final String BASE_API_URL;

    public AbstractFrydAPIService(OAuth20Service oauthService, String baseAPIUrl) {
        this.oauthService = oauthService;
        this.BASE_API_URL = baseAPIUrl;
    }

    public abstract APIResponse<Location> getLocationById(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException;

    public abstract Future<APIResponse<Location>> getLocationByIdAsync(OAuth2AccessToken appAccessToken, String locationId);

    public abstract APIResponse<List<Trophylist>> getTrophylistsFromLocation(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException;

    public abstract Future<APIResponse<List<Trophylist>>> getTrophylistsFromLocationAsync(OAuth2AccessToken appAccessToken, String locationId);

    protected OAuthRequest createRequest(OAuth2AccessToken appAccessToken, String urlAddon) {
        OAuthRequest request = new OAuthRequest(Verb.POST, BASE_API_URL+urlAddon);
        request.addHeader("token", appAccessToken.getAccessToken());

        return request;
    }

    protected <T> APIResponse<T> handleRequest(T t, OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
        Response response = oauthService.execute(request);
        System.out.println(response.getCode()+" message:"+response.getMessage()+" headers:"+response.getHeaders()+" body:"+response.getBody());

        String responseJson = response.getBody();

        return handleRequestInternal(t, responseJson);
    }

    protected <T> APIResponse<T> handleRequestAsync(T t, OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
        Future<Response> responseFuture = oauthService.executeAsync(request);
        Response response = responseFuture.get();
        String responseJson = response.getBody();

        return handleRequestInternal(t, responseJson);
    }

    @SuppressWarnings("unchecked")
    protected <T> APIResponse<T> handleRequestInternal(T t, String responseJson) throws IOException {
        JSONObject responseJsonObj = new JSONObject(responseJson);

        JSONObject jsonResponse = null;
        JSONObject jsonMessage = null;
        APIResponse<T> apiResponse = new APIResponse<>();

        // Get response with actual answer, if there
        try {
            jsonResponse = responseJsonObj.getJSONObject("response");
        } catch (JSONException ignored) {
            // Can be there or not, no big deal
        }

        if (jsonResponse != null) {
            T mappedT = (T) mapper.readValue(jsonResponse.toString(), t.getClass());
            apiResponse.setFrydDataType(mappedT);
        }

        // Get message in case of an error or info message
        try {
            jsonMessage = responseJsonObj.getJSONObject("message");
        } catch (JSONException ignored) {
            // Can be there or not, no big deal
        }

        if (jsonMessage != null) {
            apiResponse.setMessage(jsonMessage.getString("text"));
            apiResponse.setType(jsonMessage.getString("type"));
        }

        String uid = responseJsonObj.getString("uid");
        apiResponse.setUid(uid);

        return apiResponse;
    }
}
