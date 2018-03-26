package com.fryd.sdk.model;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public class TestModelJsonRepresentation {

    public static final String FULL_LOCATION_JSON = "{\"response\":{ \"_id\" : \"57d014e2b9ad432b045c9aah\", \"name\" :" +
            " \"Museum für vermoderne Kunst\", \"description\" : \"Hier gibt es alles vom Schimmeljogurt bis zur" +
            " sprechenden Selleriestange.\", \"type\" : \"LOCAL\", \"homepage\" : \"http://www.stinke-stinke.de\"," +
            " \"age_range\" : \"0-99\", \"email\" : \"poeppi@fryd.zone\", \"opening_time\" : { \"start_date\" :" +
            " \"Montag\", \"end_date\" : \"Freitag\", \"start_time\" : \"10.30 Uhr\", \"end_time\" : \"19 Uhr\"," +
            " \"addition\" : \"Sammstag und Sonntag Ruhetag\" }, \"address\" : { \"street\" : \"Bierstadter Höhe\"," +
            " \"postal_code\" : 65191, \"nr\" : \"24\", \"city\" : \"Wiesbaden\" }, \"trophylist_id\" :" +
            " \"57d019d6b9ad433518b1f670\", \"created\" : \"2016-10-31T00:00:00Z\", \"owner_id\" :" +
            " \"57a31fb78f30f31ce41c037f\", \"status\" : \"IN_REVIEW\", \"iconpic_id\" : \"5ab8f6fab6fa8c324bae81a7\"," +
            " \"pictures\" : [ \"5ab8f6fab6fa8c324bae81a7\" ] },\"uid\":\"A3DFDB6AC990BDB8A7B2E908\"}";

    public static final String MINIMAL_LOCATION_JSON = "{\"response\":{ \"_id\" : \"585157821d33a9228cb281e2\"," +
            " \"name\" : \"Gamitch\", \"description\" : \"fryd Twitch plugin\", \"type\" : \"ONLINE\", \"homepage\" :" +
            " \"http://www.gamitch.com\", \"age_range\" : \"12-99\", \"email\" : \"bla@bla.de\", \"trophylist_id\" :" +
            " \"585157841d33a9228cb281e3\", \"created\" : \"2016-12-14T00:00:00Z\", \"owner_id\" :" +
            " \"57a31fb78f30f31ce41c037f\", \"status\" : \"ACTIVE\" },\"uid\":\"A3DFDB6AC990BDB8A7B2E908\"}";

    public static final String LOCATION_NOT_PART_ERROR = "{ \"message\" : {\"type\" : \"ERROR\", \"text\" :" +
            " \"Location not part of external application.\"},\"uid\" : \"5E618CFA83E6A4082809F744\"}";

}
