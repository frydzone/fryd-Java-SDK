package com.fryd.sdk.service;

import com.fryd.sdk.model.APIResponse;
import com.fryd.sdk.model.TestModelJsonRepresentation;
import com.fryd.sdk.model.Trophy;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydAPITrophiesTest extends FrydAPITest {

    @Test
    public void testGetTrophiesOfListFullBody() throws Exception {
        APIResponse<List<Trophy>> responseTrophiesFullBody = frydAPIService.getTrophiesOfList(testAppAccessToken, "57d019d6b9ad433518b1f665");
        List<Trophy> trophies = responseTrophiesFullBody.getFrydDataType();
        assertThat(trophies.size(), is(10));

        for (Trophy trophy : trophies) {
            assertThat(trophy.getId(), notNullValue());
            assertThat(trophy.getName(), notNullValue());
            assertThat(trophy.getDescription(), notNullValue());
            assertThat(trophy.getType(), nullValue());
            assertThat(trophy.getAuthorId(), nullValue());
            assertThat(trophy.getListId(), nullValue());
            assertThat(trophy.getLocationId(), notNullValue());
            assertThat(trophy.getIconpicId(), notNullValue());
            assertThat(trophy.getPoints(), notNullValue());
            assertThat(trophy.getHidden(), notNullValue());
            assertThat(trophy.getOrder(), notNullValue());
            assertThat(trophy.getDifficulty(), nullValue());
            assertThat(trophy.getAuthorName(), nullValue());
            assertThat(trophy.getListName(), nullValue());
            assertThat(trophy.getRarity(), notNullValue());
        }

    }

    @Test
    public void testGetTrophiesOfListMinimalBody() throws Exception {
        ((FrydAPIServiceMock) frydAPIService).setTrophiesJsonResponse(TestModelJsonRepresentation.MINIMAL_TROPHIES_JSON);
        APIResponse<List<Trophy>> responseTrophiesFullBody = frydAPIService.getTrophiesOfList(testAppAccessToken, "57d019d6b9ad433518b1f670");
        List<Trophy> trophies = responseTrophiesFullBody.getFrydDataType();
        assertThat(trophies.size(), is(1));

        Trophy theOneAndOnlyTrophy = trophies.get(0);
        assertThat(theOneAndOnlyTrophy.getId(), is("58234579a970de1fe42609df"));
        assertThat(theOneAndOnlyTrophy.getName(), is("Museum für vermoderne Kunst komplett Erfolg"));
        assertThat(theOneAndOnlyTrophy.getDescription(), is("Du hast alle normalen Erfolge (die zu keinem Event gehören) von 'Museum für vermoderne Kunst' erreicht."));
        assertThat(theOneAndOnlyTrophy.getType(), nullValue());
        assertThat(theOneAndOnlyTrophy.getAuthorId(), nullValue());
        assertThat(theOneAndOnlyTrophy.getListId(), nullValue());
        assertThat(theOneAndOnlyTrophy.getLocationId(), is("57d014e2b9ad432b045c9aah"));
        assertThat(theOneAndOnlyTrophy.getIconpicId(), nullValue());
        assertThat(theOneAndOnlyTrophy.getPoints(), is(0));
        assertThat(theOneAndOnlyTrophy.getHidden(), is(false));
        assertThat(theOneAndOnlyTrophy.getOrder(), is(1));
        assertThat(theOneAndOnlyTrophy.getDifficulty(), nullValue());
        assertThat(theOneAndOnlyTrophy.getAuthorName(), nullValue());
        assertThat(theOneAndOnlyTrophy.getListName(), nullValue());
        assertThat(theOneAndOnlyTrophy.getRarity(), is(0.0f));

    }

    @Test
    public void testGetTrophyByIdFullBody() throws Exception {
        APIResponse<Trophy> responseTrophyFullBody = frydAPIService.getTrophyById(testAppAccessToken, "587e5c27c550b92754959cfe");

        Trophy theOneAndOnlyTrophy = responseTrophyFullBody.getFrydDataType();
        assertThat(theOneAndOnlyTrophy.getId(), is("587e5c27c550b92754959cfe"));
        assertThat(theOneAndOnlyTrophy.getName(), is("Gamitch komplett Erfolg"));
        assertThat(theOneAndOnlyTrophy.getDescription(), is("Du hast alle normalen Erfolge (die zu keinem Event gehören) von 'Gamitch' erreicht."));
        assertThat(theOneAndOnlyTrophy.getType(), is("GENERATED"));
        assertThat(theOneAndOnlyTrophy.getAuthorId(), is("587e5c27c550b92754959cff"));
        assertThat(theOneAndOnlyTrophy.getListId(), is("585157841d33a9228cb281e3"));
        assertThat(theOneAndOnlyTrophy.getLocationId(), is("585157821d33a9228cb281e2"));
        assertThat(theOneAndOnlyTrophy.getIconpicId(), is("5aba4906b6fa8c324bae81ab"));
        assertThat(theOneAndOnlyTrophy.getPoints(), is(0));
        assertThat(theOneAndOnlyTrophy.getHidden(), is(false));
        assertThat(theOneAndOnlyTrophy.getOrder(), is(1));
        assertThat(theOneAndOnlyTrophy.getDifficulty(), is("HARD"));
        assertThat(theOneAndOnlyTrophy.getAuthorName(), is("GENERATED"));
        assertThat(theOneAndOnlyTrophy.getListName(), is("Gamitch Erfolge"));
        assertThat(theOneAndOnlyTrophy.getRarity(), is(0.0f));

    }

}
