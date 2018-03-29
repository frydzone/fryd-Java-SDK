package com.fryd.sdk.model;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public class TestModelJsonRepresentation {

    public static final String FULL_LOCATION_JSON = "{\"response\":{ \"id\" : \"57d014e2b9ad432b045c9aah\", \"name\" :" +
            " \"Museum für vermoderne Kunst\", \"description\" : \"Hier gibt es alles vom Schimmeljogurt bis zur" +
            " sprechenden Selleriestange.\", \"type\" : \"LOCAL\", \"homepage\" : \"http://www.stinke-stinke.de\"," +
            " \"age_range\" : \"0-99\", \"email\" : \"poeppi@fryd.zone\", \"opening_time\" : { \"start_date\" :" +
            " \"Montag\", \"end_date\" : \"Freitag\", \"start_time\" : \"10.30 Uhr\", \"end_time\" : \"19 Uhr\"," +
            " \"addition\" : \"Sammstag und Sonntag Ruhetag\" }, \"address\" : { \"street\" : \"Bierstadter Höhe\"," +
            " \"postal_code\" : 65191, \"nr\" : \"24\", \"city\" : \"Wiesbaden\" }, \"trophylist_id\" :" +
            " \"57d019d6b9ad433518b1f670\", \"created\" : \"2016-10-31T00:00:00Z\", \"owner_id\" :" +
            " \"57a31fb78f30f31ce41c037f\", \"status\" : \"IN_REVIEW\", \"iconpic_id\" : \"5ab8f6fab6fa8c324bae81a7\"," +
            " \"pictures\" : [ \"5ab8f6fab6fa8c324bae81a7\" ] },\"uid\":\"A3DFDB6AC990BDB8A7B2E908\"}";

    public static final String MINIMAL_LOCATION_JSON = "{\"response\":{ \"id\" : \"585157821d33a9228cb281e2\"," +
            " \"name\" : \"Gamitch\", \"description\" : \"fryd Twitch plugin\", \"type\" : \"ONLINE\", \"homepage\" :" +
            " \"http://www.gamitch.com\", \"age_range\" : \"12-99\", \"email\" : \"bla@bla.de\", \"trophylist_id\" :" +
            " \"585157841d33a9228cb281e3\", \"created\" : \"2016-12-14T00:00:00Z\", \"owner_id\" :" +
            " \"57a31fb78f30f31ce41c037f\", \"status\" : \"ACTIVE\" },\"uid\":\"A3DFDB6AC990BDB8A7B2E908\"}";

    public static final String LOCATION_NOT_PART_ERROR = "{ \"message\" : {\"type\" : \"ERROR\", \"text\" :" +
            " \"Location not part of external application.\"},\"uid\" : \"5E618CFA83E6A4082809F744\"}";

    public static final String FULL_TROPHYLISTS_JSON = "{\"response\":{\"trophylists\":[{\"id\":\"57d019d6b9ad433518b1f665\"," +
            "\"name\":\"fryd Erfolge\",\"description\":\"Alle fryd Erfolge\",\"type\":\"LISTE\",\"iconpic_id\":" +
            "\"5aba2f18b6fa8c324bae81a8\"},{\"id\":\"57d019d6b9ad433518b1f666\",\"name\":\"fryd Bewerber Event\"," +
            "\"description\":\"Erfolge für den Fall das mal jemand neues zu fryd kommt\",\"type\":\"EVENT\",\"start\":" +
            "\"2016-11-01T00:00:00Z\",\"end\":\"2017-02-01T00:00:00Z\",\"iconpic_id\":\"5aba2f18b6fa8c324bae81a8\"}," +
            "{\"id\":\"57d019d6b9ad433518b1f668\",\"name\":\"fryd Alpha Event\",\"description\":\"Erfolge für den" +
            " Start von Antman.\",\"type\":\"EVENT\",\"start\":\"2016-02-01T00:00:00Z\",\"end\":\"2017-04-01T00:00:00Z\"" +
            ",\"iconpic_id\":\"5aba2f18b6fa8c324bae81a8\"},{\"id\":\"57d019d6b9ad433518b1f66X\",\"name\":" +
            "\"fryd Test Erfolge\",\"description\":\"Alle fryd Test Erfolge\",\"type\":\"LISTE\",\"iconpic_id\"" +
            ":\"5aba2f18b6fa8c324bae81a8\"}]},\"uid\":\"FCA9E5F072733A515AF35533\"}";

    public static final String MINIMAL_TROPHYLISTS_JSON = "{\"response\":{\"trophylists\":[{\"id\":\"57d019d6b9ad433518b1f669\"," +
            "\"name\":\"Stadt Eppstein Erfolge\",\"description\":\"Alle Stadt Eppstein Erfolge\",\"type\":\"LISTE\"}]}" +
            ",\"uid\":\"FCA9E5F072733A515AF35533\"}";

    public static final String FULL_TROPHIES_JSON = "{\"response\":{\"trophys\":[{\"id\":\"58234579a970de1fe42609da\"," +
            "\"name\":\"fryd komplett Erfolg\",\"description\":\"Du hast alle normalen Erfolge (die zu keinem Event" +
            " gehören) von 'fryd' erreicht.\",\"location_id\":\"57d014e2b9ad432b045c9aae\",\"points\":0,\"hidden\"" +
            ":false,\"order\":1,\"iconpic_id\":\"5aba4906b6fa8c324bae81ab\",\"rarity\":0.0},{\"id\"" +
            ":\"57d13ba1b9ad43348cd2399a\",\"name\":\"Der Erste\",\"description\":\"Finde einen besonderen Ort\"," +
            "\"location_id\":\"57d014e2b9ad432b045c9aae\",\"points\":10,\"hidden\":false,\"order\":2,\"iconpic_id\":" +
            "\"5aba4906b6fa8c324bae81ab\",\"rarity\":50.0},{\"id\":\"57d13ba1b9ad43348cd2399k\",\"name\":" +
            "\"Der Einfache\",\"description\":\"Schaltet sich praktisch von selbst frei\",\"location_id\":" +
            "\"57d014e2b9ad432b045c9aae\",\"points\":10,\"hidden\":false,\"order\":3,\"iconpic_id\":" +
            "\"5aba4906b6fa8c324bae81ab\",\"rarity\":0.0},{\"id\":\"57d13ba1b9ad43348cd2399b\",\"name\":\"Der Zweite\"" +
            ",\"description\":\"Finde 2 verschiedene Punkte\",\"location_id\":\"57d014e2b9ad432b045c9aae\",\"points\":15," +
            "\"hidden\":false,\"order\":4,\"iconpic_id\":\"5aba4906b6fa8c324bae81ab\",\"rarity\":0.0}," +
            "{\"id\":\"57d13ba1b9ad43348cd2399c\",\"name\":\"Der Dritte\",\"description\":\"Mach irgendwas seltsames" +
            " um diesen Erfolg zu kriegen\",\"location_id\":\"57d014e2b9ad432b045c9aae\",\"points\":20,\"hidden\"" +
            ":true,\"order\":5,\"iconpic_id\":\"5aba4906b6fa8c324bae81ab\",\"rarity\":25.0},{\"id\":" +
            "\"57d13ba1b9ad43348cd2399h\",\"name\":\"Der Vierte\",\"description\":\"Finde 3 verschiedene Punkte\"," +
            "\"location_id\":\"57d014e2b9ad432b045c9aae\",\"points\":25,\"hidden\":false,\"order\":6,\"iconpic_id\":" +
            "\"5aba4906b6fa8c324bae81ab\",\"rarity\":0.0},{\"id\":\"582061f5a970de15d0bc730d\"" +
            ",\"name\":\"Der Fünfte\",\"description\":\"Teil eines viel größeren Erfolgs\",\"location_id\"" +
            ":\"57d014e2b9ad432b045c9aae\",\"points\":15,\"hidden\":false,\"order\":7,\"iconpic_id\":" +
            "\"5aba4906b6fa8c324bae81ab\",\"rarity\":0.0},{\"id\":\"5823208031ac2d53c786847d\",\"name\":" +
            "\"Der Sechste\",\"description\":\"Erreiche den 'Fünften' und finde einen Schlüssel.\",\"location_id\":" +
            "\"57d014e2b9ad432b045c9aae\",\"points\":15,\"hidden\":false,\"order\":8,\"iconpic_id\":" +
            "\"5aba4906b6fa8c324bae81ab\",\"rarity\":0.0},{\"id\":\"582061f5a970de15d0bc730c\"," +
            "\"name\":\"Der Gemeinsame\",\"description\":\"Erreiche 2 Erfolge\",\"location_id\":" +
            "\"57d014e2b9ad432b045c9aae\",\"points\":50,\"hidden\":false,\"order\":9,\"iconpic_id\":" +
            "\"5aba4906b6fa8c324bae81ab\",\"rarity\":0.0},{\"id\":\"582061f5a970de15d0bc730p\",\"name\":\"" +
            "Der Gemeinsame Number 2\",\"description\":\"Erreiche 3 Erfolge\",\"location_id\":\"57d014e2b9ad432b045c9aae\"" +
            ",\"points\":50,\"hidden\":false,\"order\":10,\"iconpic_id\":\"5aba4906b6fa8c324bae81ab\",\"rarity\":0.0}]}," +
            "\"uid\":\"FB4327F5A079E4967223B80D\"}";

    public static final String MINIMAL_TROPHIES_JSON = "{\"response\":{\"trophys\":[{\"id\":\"58234579a970de1fe42609df\"," +
            "\"name\":\"Museum für vermoderne Kunst komplett Erfolg\",\"description\":\"Du hast alle normalen Erfolge " +
            "(die zu keinem Event gehören) von 'Museum für vermoderne Kunst' erreicht.\",\"location_id\":" +
            "\"57d014e2b9ad432b045c9aah\",\"points\":0,\"hidden\":false,\"order\":1,\"rarity\":0.0}]}," +
            "\"uid\":\"FB4327F5A079E4967223B80D\"}";

    public static final String FULL_TROPHY_JSON = "{\"response\" : {\"id\" : \"587e5c27c550b92754959cfe\"," +
            "\"name\" : \"Gamitch komplett Erfolg\",\"description\" : \"Du hast alle normalen Erfolge (die zu " +
            "keinem Event gehören) von 'Gamitch' erreicht.\",\"type\" : \"GENERATED\", \"author_id\" : " +
            "\"587e5c27c550b92754959cff\",\"list_id\" : \"585157841d33a9228cb281e3\",\"location_id\":\"585157821d33a9228cb281e2\"," +
            "\"points\" : 0, \"money\" : 0, \"hidden\" : false, \"order\" : 1,\"iconpic_id\":\"5aba4906b6fa8c324bae81ab\"" +
            ", \"difficulty\" : \"HARD\",\"author_name\" : \"GENERATED\", \"list_name\" : \"Gamitch Erfolge\", \"rarity\" : 0.0}," +
            "\"uid\" : \"22CF96D87599DF3AD6865877\"}";
}