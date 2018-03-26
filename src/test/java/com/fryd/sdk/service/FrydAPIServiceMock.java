package com.fryd.sdk.service;

import com.fryd.sdk.model.APIResponse;
import com.fryd.sdk.model.Location;
import com.fryd.sdk.model.TestModelJsonRepresentation;
import com.fryd.sdk.model.Trophylist;
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
        return null;
    }

    @Override
    public Future<APIResponse<List<Trophylist>>> getTrophylistsFromLocationAsync(OAuth2AccessToken appAccessToken, String locationId) {
        return null;
    }
}
