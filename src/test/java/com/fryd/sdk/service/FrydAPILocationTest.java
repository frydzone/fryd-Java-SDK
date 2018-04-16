package com.fryd.sdk.service;

import com.fryd.sdk.model.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public class FrydAPILocationTest extends FrydAPITest {

    @Test
    public void testGetLocationByIdFullBody() throws Exception {
        APIResponse<Location> responseLocationFullBody = frydAPIService.getFrydSpotById(testAppAccessToken, "57d014e2b9ad432b045c9aah");
        Location locationFullBody = responseLocationFullBody.getFrydDataType();

        assertThat(locationFullBody.getId(), is("57d014e2b9ad432b045c9aah"));
        assertThat(locationFullBody.getName(), is("Museum für vermoderne Kunst"));
        assertThat(locationFullBody.getDescription(), is("Hier gibt es alles vom Schimmeljogurt bis zur sprechenden Selleriestange."));
        assertThat(locationFullBody.getType(), is("LOCAL"));
        assertThat(locationFullBody.getHomepage(), is("http://www.stinke-stinke.de"));
        assertThat(locationFullBody.getAgeRange(), is("0-99"));
        assertThat(locationFullBody.getEmail(), is("poeppi@fryd.zone"));

        OpeningTimes openingTime = locationFullBody.getOpeningTime();
        assertThat(openingTime.getStartDate(), is("Montag"));
        assertThat(openingTime.getEndDate(), is("Freitag"));
        assertThat(openingTime.getStartTime(), is("10.30 Uhr"));
        assertThat(openingTime.getEndTime(), is("19 Uhr"));
        assertThat(openingTime.getAddition(), is("Sammstag und Sonntag Ruhetag"));

        Address address = locationFullBody.getAddress();
        assertThat(address.getStreet(), is("Bierstadter Höhe"));
        assertThat(address.getPostalCode(), is(65191));
        assertThat(address.getNr(), is("24"));
        assertThat(address.getCity(), is("Wiesbaden"));

        assertThat(locationFullBody.getTrophylistId(), is("57d019d6b9ad433518b1f670"));
        assertThat(locationFullBody.getCreated(), is(LocalDateTime.of(2016, 10, 31, 0, 0, 0)));
        assertThat(locationFullBody.getOwnerId(), is("57a31fb78f30f31ce41c037f"));
        assertThat(locationFullBody.getStatus(), is("IN_REVIEW"));
        assertThat(locationFullBody.getIconpicId(), is("5ab8f6fab6fa8c324bae81a7"));

        List<String> pictures = locationFullBody.getPictures();
        assertThat(pictures.size(), is(1));
        assertThat(pictures.get(0), is("5ab8f6fab6fa8c324bae81a7"));

    }

    @Test
    public void testGetLocationByIdMinimalBody() throws Exception {
        ((FrydAPIServiceMock) frydAPIService).setLocationJsonResponse(TestModelJsonRepresentation.MINIMAL_LOCATION_JSON);
        APIResponse<Location> responseLocationMinimalBody = frydAPIService.getFrydSpotById(testAppAccessToken, "585157821d33a9228cb281e2");
        Location locationMinimalBody = responseLocationMinimalBody.getFrydDataType();

        assertThat(locationMinimalBody.getId(), is("585157821d33a9228cb281e2"));
        assertThat(locationMinimalBody.getName(), is("Gamitch"));
        assertThat(locationMinimalBody.getDescription(), is("fryd Twitch plugin"));
        assertThat(locationMinimalBody.getType(), is("ONLINE"));
        assertThat(locationMinimalBody.getHomepage(), is("http://www.gamitch.com"));
        assertThat(locationMinimalBody.getAgeRange(), is("12-99"));
        assertThat(locationMinimalBody.getEmail(), is("bla@bla.de"));

        OpeningTimes openingTime = locationMinimalBody.getOpeningTime();
        assertThat(openingTime, nullValue());

        Address address = locationMinimalBody.getAddress();
        assertThat(address, nullValue());

        assertThat(locationMinimalBody.getTrophylistId(), is("585157841d33a9228cb281e3"));
        assertThat(locationMinimalBody.getCreated(), is(LocalDateTime.of(2016, 12, 14, 0, 0, 0)));
        assertThat(locationMinimalBody.getOwnerId(), is("57a31fb78f30f31ce41c037f"));
        assertThat(locationMinimalBody.getStatus(), is("ACTIVE"));
        assertThat(locationMinimalBody.getIconpicId(), nullValue());

        List<String> pictures = locationMinimalBody.getPictures();
        assertThat(pictures, nullValue());
    }

    @Test
    public void testGetLocationByIdNotPartError() throws Exception {
        ((FrydAPIServiceMock) frydAPIService).setLocationJsonResponse(TestModelJsonRepresentation.LOCATION_NOT_PART_ERROR);
        APIResponse<Location> responseLocationError = frydAPIService.getFrydSpotById(testAppAccessToken, "5");

        assertThat(responseLocationError.getFrydDataType(), nullValue());
        assertThat(responseLocationError.getMessage(), is("Location not part of external application."));
        assertThat(responseLocationError.getType(), is("ERROR"));

    }

}