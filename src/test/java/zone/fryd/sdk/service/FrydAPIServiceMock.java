package zone.fryd.sdk.service;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Response;
import lombok.Setter;
import zone.fryd.sdk.model.*;

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

    @Setter
    private String userJsonResponse = TestModelJsonRepresentation.FULL_USER_JSON;

    @Override
    public APIResponse<Location> getFrydSpotById(String locationId, OAuth2AccessToken appAccessToken) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, locationJsonResponse);
        return handleRequestInternal(new Location(), response);
    }

    @Override
    public Future<APIResponse<Location>> getFrydSpotByIdAsync(String locationId, OAuth2AccessToken appAccessToken) {
        return null;
    }

    @Override
    public APIResponse<List<Trophylist>> getEventsFromLocation(String locationId, OAuth2AccessToken appAccessToken) throws InterruptedException, ExecutionException, IOException {
        return null;
    }

    @Override
    public Future<APIResponse<List<Trophylist>>> getEventsFromLocationAsync(String locationId, OAuth2AccessToken appAccessToken) {
        return null;
    }

    @Override
    public APIResponse<List<Trophylist>> getTrophylistsFromLocation(String locationId, OAuth2AccessToken appAccessToken) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, trophylistJsonResponse);
        return transformTrophylistResponse(handleRequestInternal(new Trophylist.Trophylists(), response));
    }

    @Override
    public Future<APIResponse<List<Trophylist>>> getTrophylistsFromLocationAsync(String locationId, OAuth2AccessToken appAccessToken) {
        return null;
    }

    @Override
    public APIResponse<List<Trophy>> getTrophiesOfList(String trophylistId, OAuth2AccessToken appAccessToken) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, trophiesJsonResponse);
        return transformTrophiesResponse(handleRequestInternal(new Trophy.Trophies(), response));
    }

    @Override
    public Future<APIResponse<List<Trophy>>> getTrophiesOfListAsync(String trophylistId, OAuth2AccessToken appAccessToken) {
        return null;
    }

    @Override
    public APIResponse<Trophy> getTrophyById(String trophyId, OAuth2AccessToken appAccessToken) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, trophyJsonResponse);
        return handleRequestInternal(new Trophy(), response);
    }

    @Override
    public Future<APIResponse<Trophy>> getTrophyByIdAsync(String trophyId, OAuth2AccessToken appAccessToken) {
        return null;
    }

    @Override
    public APIResponse<User> getUserInformation(OAuth2AccessToken userAccessToken) throws InterruptedException, ExecutionException, IOException {
        Response response = new Response(200, "", headers, userJsonResponse);
        return handleRequestInternal(new User(), response);
    }

    @Override
    public Future<APIResponse<User>> getUserInformationAsync(OAuth2AccessToken userAccessToken) {
        return null;
    }

    @Override
    public APIResponse<String> triggerTrophyProgress(OAuth2AccessToken userAccessToken, OAuth2AccessToken appAccessToken, String locationId, String secret) throws InterruptedException, ExecutionException, IOException {
        return null;
    }

    @Override
    public Future<APIResponse<String>> triggerTrophyProgressAsync(OAuth2AccessToken userAccessToken, OAuth2AccessToken appAccessToken, String locationId, String secret) {
        return null;
    }
}
