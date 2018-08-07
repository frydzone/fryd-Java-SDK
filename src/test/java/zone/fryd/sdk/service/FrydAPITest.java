package zone.fryd.sdk.service;

import com.github.scribejava.core.model.OAuth2AccessToken;
import org.junit.Before;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public abstract class FrydAPITest {

    protected AbstractFrydAPIService frydAPIService;

    protected OAuth2AccessToken testAppAccessToken = new OAuth2AccessToken("1");

    protected OAuth2AccessToken testUserAccessToken = new OAuth2AccessToken("2");

    @Before
    public void setUpMockInterface() throws Exception {
        frydAPIService = new FrydAPIServiceMock();
    }


}
