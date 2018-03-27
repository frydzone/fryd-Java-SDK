package com.fryd.sdk.service;

import com.fryd.sdk.model.*;
import com.github.scribejava.core.model.OAuth2AccessToken;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydAPIServiceMock extends AbstractFrydAPIService {

    public FrydAPIServiceMock() {
        super(null, "mock://url.com");
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
        return handleRequestInternal(new Location(), locationJsonResponse);
    }

    @Override
    public Future<APIResponse<Location>> getLocationByIdAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return null;
    }

    @Override
    public APIResponse<List<Trophylist>> getTrophylistsFromLocation(OAuth2AccessToken appAccessToken, String locationId) throws InterruptedException, ExecutionException, IOException {
        return transformTrophylistResponse(handleRequestInternal(new Trophylist.Trophylists(), trophylistJsonResponse));
    }

    @Override
    public Future<APIResponse<List<Trophylist>>> getTrophylistsFromLocationAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return null;
    }

    @Override
    public APIResponse<List<Trophy>> getTrophiesOfList(OAuth2AccessToken appAccessToken, String trophylistId) throws InterruptedException, ExecutionException, IOException {
        return transformTrophiesResponse(handleRequestInternal(new Trophy.Trophies(), trophiesJsonResponse));
    }

    @Override
    public Future<APIResponse<List<Trophy>>> getTrophiesOfListAsync(OAuth2AccessToken appAccessToken, String trophylistId) {
        return null;
    }

    @Override
    public APIResponse<Trophy> getTrophyById(OAuth2AccessToken appAccessToken, String trophyId) throws InterruptedException, ExecutionException, IOException {
        return handleRequestInternal(new Trophy(), trophyJsonResponse);
    }

    @Override
    public Future<APIResponse<Trophy>> getTrophyByIdAsync(OAuth2AccessToken appAccessToken, String trophyId) {
        return null;
    }
}
