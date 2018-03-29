package com.fryd.sdk.service;

import com.fryd.sdk.model.*;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Response;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydAPIServiceMock extends AbstractFrydAPIService {

    public FrydAPIServiceMock() {
        super(null, "mock://url.com");
    }

    private Map<String,String> headers = new HashMap<>();

    {
        headers.put("content-type", "application/json; charset=utf-8");
    }

    @Setter
    private String locationJsonResponse = TestModelJsonRepresentation.FULL_LOCATION_JSON;

    @Setter
    private String trophylistJsonResponse = TestModelJsonRepresentation.FULL_TROPHYLISTS_JSON;

    @Setter
    private String trophiesJsonResponse = TestModelJsonRepresentation.FULL_TROPHIES_JSON;

    @Setter
    private String trophyJsonResponse = TestModelJsonRepresentation.FULL_TROPHY_JSON;

    @Override
    public APIResponse<Location> getLocationById(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, locationJsonResponse);
        return handleRequestInternal(new Location(), response);
    }

    @Override
    public Future<APIResponse<Location>> getLocationByIdAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return null;
    }

    @Override
    public APIResponse<List<Trophylist>> getTrophylistsFromLocation(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, trophylistJsonResponse);
        return transformTrophylistResponse(handleRequestInternal(new Trophylist.Trophylists(), response));
    }

    @Override
    public Future<APIResponse<List<Trophylist>>> getTrophylistsFromLocationAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return null;
    }

    @Override
    public APIResponse<List<Trophy>> getTrophiesOfList(OAuth2AccessToken appAccessToken, String trophylistId) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, trophiesJsonResponse);
        return transformTrophiesResponse(handleRequestInternal(new Trophy.Trophies(), response));
    }

    @Override
    public Future<APIResponse<List<Trophy>>> getTrophiesOfListAsync(OAuth2AccessToken appAccessToken, String trophylistId) {
        return null;
    }

    @Override
    public APIResponse<Trophy> getTrophyById(OAuth2AccessToken appAccessToken, String trophyId) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, trophyJsonResponse);
        return handleRequestInternal(new Trophy(), response);
    }

    @Override
    public Future<APIResponse<Trophy>> getTrophyByIdAsync(OAuth2AccessToken appAccessToken, String trophyId) {
        return null;
    }
}
