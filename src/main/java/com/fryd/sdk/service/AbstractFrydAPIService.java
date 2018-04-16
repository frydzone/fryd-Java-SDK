package com.fryd.sdk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fryd.sdk.model.*;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.common.base.Strings;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public abstract class AbstractFrydAPIService {

    protected final static Logger logger = Logger.getLogger(FrydAPIService.class.getName());

    private ObjectMapper mapper = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    protected final OAuth20Service oauthService;

    @Getter
    protected final String BASE_API_URL;

    public AbstractFrydAPIService(OAuth20Service oauthService, String baseAPIUrl) {
        this.oauthService = oauthService;
        this.BASE_API_URL = baseAPIUrl;
    }

    public abstract APIResponse<Location> getFrydSpotById(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException;

    public abstract Future<APIResponse<Location>> getFrydSpotByIdAsync(OAuth2AccessToken appAccessToken, String locationId);

    public abstract APIResponse<List<Trophylist>> getTrophylistsFromLocation(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException;

    public abstract Future<APIResponse<List<Trophylist>>> getTrophylistsFromLocationAsync(OAuth2AccessToken appAccessToken, String locationId);

    public abstract APIResponse<List<Trophy>> getTrophiesOfList(OAuth2AccessToken appAccessToken, String trophylistId) throws InterruptedException, ExecutionException, IOException;

    public abstract Future<APIResponse<List<Trophy>>> getTrophiesOfListAsync(OAuth2AccessToken appAccessToken, String trophylistId);

    public abstract APIResponse<Trophy> getTrophyById(OAuth2AccessToken appAccessToken, String trophyId) throws InterruptedException, ExecutionException, IOException;

    public abstract Future<APIResponse<Trophy>> getTrophyByIdAsync(OAuth2AccessToken appAccessToken, String trophyId);

    public abstract APIResponse<User> getUserInformation(OAuth2AccessToken userAccessToken) throws InterruptedException, ExecutionException, IOException;

    public abstract Future<APIResponse<User>> getUserInformationAsync(OAuth2AccessToken userAccessToken);

    public abstract APIResponse<String> triggerTrophyProgress(OAuth2AccessToken userAccessToken, OAuth2AccessToken appAccessToken, String locationId, String secret) throws InterruptedException, ExecutionException, IOException;

    public abstract Future<APIResponse<String>> triggerTrophyProgressAsync(OAuth2AccessToken userAccessToken, OAuth2AccessToken appAccessToken, String locationId, String secret);

    protected OAuthRequest createRequest(OAuth2AccessToken appAccessToken, String urlAddon) {
        OAuthRequest request = new OAuthRequest(Verb.POST, BASE_API_URL+urlAddon);
        request.addHeader("token", appAccessToken.getAccessToken());

        return request;
    }

    protected <T> APIResponse<T> handleRequest(T t, OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
        Response response = oauthService.execute(request);
//        System.out.println(response.getCode()+" message:"+response.getMessage()+" headers:"+response.getHeaders()+" body:"+response.getBody());
        return handleRequestInternal(t, response);

    }

    protected <T> APIResponse<T> handleRequestAsync(T t, OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
        Future<Response> responseFuture = oauthService.executeAsync(request);
        Response response = responseFuture.get();
        return handleRequestInternal(t, response);
    }

    @SuppressWarnings("unchecked")
    protected <T> APIResponse<T> handleRequestInternal(T t, Response response) throws IOException {
        String responseJson = response.getBody();

        // If there is no body, there must have been an error
        if (Strings.isNullOrEmpty(responseJson)) {
            APIResponse<T> errorResponse = new APIResponse<>();
            int code = response.getCode();
            String message = code + " " + response.getMessage();

            String authenticateHeader = response.getHeader("WWW-Authenticate");
            if (!Strings.isNullOrEmpty(authenticateHeader)) {
                message = message + " (" + authenticateHeader + ")";
            }

            if (code >= 200 && code <= 399) {
                errorResponse.setType("INFO");
            }else {
                errorResponse.setType("ERROR");
            }

            errorResponse.setMessage(message);

            return errorResponse;
        }else {
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

    protected APIResponse<List<Trophylist>> transformTrophylistResponse(APIResponse<Trophylist.Trophylists> oldResponse) {
        Trophylist.Trophylists trophylists = oldResponse.getFrydDataType();
        List<Trophylist> trophies = null;
        if (trophylists != null) {
            trophies = trophylists.getTrophylists();
        }
        return new APIResponse<>(trophies, oldResponse.getType(), oldResponse.getMessage(), oldResponse.getUid());
    }

    protected APIResponse<List<Trophy>> transformTrophiesResponse(APIResponse<Trophy.Trophies> oldResponse) {
        Trophy.Trophies trophylists = oldResponse.getFrydDataType();
        List<Trophy> trophies = null;
        if (trophylists != null) {
            trophies = trophylists.getTrophys();
        }
        return new APIResponse<>(trophies, oldResponse.getType(), oldResponse.getMessage(), oldResponse.getUid());
    }
}
