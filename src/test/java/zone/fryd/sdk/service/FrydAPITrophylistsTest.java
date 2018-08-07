package zone.fryd.sdk.service;

import zone.fryd.sdk.model.APIResponse;
import zone.fryd.sdk.model.TestModelJsonRepresentation;
import zone.fryd.sdk.model.Trophylist;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydAPITrophylistsTest extends FrydAPITest {

    @Test
    public void testGetTrophylistsFromLocationFullBody() throws Exception  {
        APIResponse<List<Trophylist>> responseTrophylistsFullBody = frydAPIService.getTrophylistsFromLocation(testAppAccessToken, "57d014e2b9ad432b045c9aah");
        List<Trophylist> trophylists = responseTrophylistsFullBody.getFrydDataType();

        assertThat(trophylists.size(), is(4));

        for (Trophylist list : trophylists) {
            String type = list.getType();

            assertThat(list.getId(), notNullValue());
            assertThat(list.getName(), notNullValue());
            assertThat(list.getDescription(), notNullValue());
            assertThat(list.getType(), notNullValue());
            assertThat(list.getIconpicId(), notNullValue());

            if (type.equals("EVENT")) {
                assertThat(list.getStart(), notNullValue());
                assertThat(list.getEnd(), notNullValue());
            }
        }
    }

    @Test
    public void testGetTrophylistsFromLocationMinimalBody() throws Exception  {
        ((FrydAPIServiceMock) frydAPIService).setTrophylistJsonResponse(TestModelJsonRepresentation.MINIMAL_TROPHYLISTS_JSON);
        APIResponse<List<Trophylist>> responseTrophylistsMinimalBody = frydAPIService.getTrophylistsFromLocation(testAppAccessToken, "57d014e2b9ad432b045c9aag");
        List<Trophylist> trophylists = responseTrophylistsMinimalBody.getFrydDataType();

        assertThat(trophylists.size(), is(1));
        Trophylist theOneAndOnlyTrophylist = trophylists.get(0);

        assertThat(theOneAndOnlyTrophylist.getId(), is("57d019d6b9ad433518b1f669"));
        assertThat(theOneAndOnlyTrophylist.getName(), is("Stadt Eppstein Erfolge"));
        assertThat(theOneAndOnlyTrophylist.getDescription(), is("Alle Stadt Eppstein Erfolge"));
        assertThat(theOneAndOnlyTrophylist.getType(), is("LISTE"));
        assertThat(theOneAndOnlyTrophylist.getIconpicId(), nullValue());
        assertThat(theOneAndOnlyTrophylist.getStart(), nullValue());
        assertThat(theOneAndOnlyTrophylist.getEnd(), nullValue());
    }


}
