package zone.fryd.sdk.service;

import org.junit.Test;
import zone.fryd.sdk.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydAPIUserTest extends FrydAPITest {

    @Test
    public void testGetUserInformationFullBody() throws Exception {
        APIResponse<User> userInformation = frydAPIService.getUserInformation(testUserAccessToken);
        User user = userInformation.getFrydDataType();

        assertThat(user.getId(), is("57a31fb78f30f31ce41c037p"));
        assertThat(user.getUsername(), is("Davwe"));
        assertThat(user.getFirstname(), is("Heinz"));
        assertThat(user.getLastname(), is("Hummer"));
        assertThat(user.getEmail(), is("heinz@hummer.eu"));

        Address address = user.getAddress();
        assertThat(address.getStreet(), is("Hummer Höhe"));
        assertThat(address.getPostalCode(), is(66666));
        assertThat(address.getNr(), is("17"));
        assertThat(address.getCity(), is("Fischbach"));

        assertThat(user.getXp(), is(20));
        assertThat(user.getAvatarPicId(), is("57a33c288f30f31a7cdc20c6"));
        assertThat(user.getBirthday(), is(LocalDate.of(1986, 11, 1)));
        assertThat(user.getLevel(), is(2));
        assertThat(user.getSex(), is("MALE"));

        List<Progress> progressList = user.getProgress();
        for (Progress progress : progressList) {
            assertThat(progress.getId(), notNullValue());
            assertThat(progress.getName(), notNullValue());
            assertThat(progress.getAchieved(), notNullValue());
            assertThat(progress.getListId(), notNullValue());
            assertThat(progress.getLocationId(), notNullValue());
            assertThat(progress.getLocationName(), notNullValue());
            assertThat(progress.getPoints(), notNullValue());
            assertThat(progress.getTrophyId(), notNullValue());
            assertThat(progress.getLastUpdated(), notNullValue());

        }
    }

    @Test
    public void testGetUserInformationMinimalBody() throws Exception {
        ((FrydAPIServiceMock) frydAPIService).setUserJsonResponse(TestModelJsonRepresentation.MINIMAL_USER_JSON);
        APIResponse<User> userInformation = frydAPIService.getUserInformation(testUserAccessToken);
        User user = userInformation.getFrydDataType();

        assertThat(user.getId(), is("57a31fb78f30f31ce41c0387"));
        assertThat(user.getUsername(), is("tyranted"));
        assertThat(user.getFirstname(), nullValue());
        assertThat(user.getLastname(), nullValue());
        assertThat(user.getEmail(), nullValue());

        Address address = user.getAddress();
        assertThat(address, nullValue());

        assertThat(user.getXp(), is(25));
        assertThat(user.getAvatarPicId(), nullValue());
        assertThat(user.getBirthday(), nullValue());
        assertThat(user.getLevel(), is(2));

        List<Progress> progressList = user.getProgress();
        assertThat(progressList, nullValue());

    }

}
