# Welcome to the Java SDK for fryd

[![Circle CI](https://circleci.com/gh/frydzone/fryd-Java-SDK.png?style=badge)](https://circleci.com/gh/frydzone/fryd-Java-SDK)

## What is this for?

With this SDK, you can build Trophies into your Application and get access to fryds database. For more information visit our [public wiki](http://publicwiki.fryd.zone)... which is only in German at the moment (Sorry).

The fryd API basically works like any other OATH2 Interface with a (sightly different) REST API. Meaning you need to get a token, either of your fryd Spot or a user (depending on what you want to know), to use the API.

For a detailed view of the API please have a look [here](http://publicwiki.fryd.zone/index.php?title=Schnittstellen_Beschreibung).

## What do I need and how do I get it?

 * The first thing you need, is a fryd Developer account. You can either use a fryd account that you already have, by making it a developer account. Or you can register a new account and make it a developer account right from the start.

 * After that you need to create your own fryd Spot and some trophies in the fryd frontend. 
 
 * Then you need to set up the "API Anbindung". Just like with every other OAUTH2 interface you need to provide a valid **redirect URL** and a valid **privacy policy URL**. Fryd then generates a **Client id** and a **Client Secret** for your fryd Spot. Please keep the client secret to youself, this is basically your password for accessing the fryd API.
 
 * That's it. Now that you have an id and a secret you are ready to go. 

## How does this work?

Small disclamer before we start. This SDK is basically a wrapper of [scribejava](https://github.com/scribejava/scribejava). Sooner or later we will add our API to their supported APIs but we are not there yet.

### Where to find the library?
You can pull the frydSDK from the central maven repository, just add this to your pom.xml file:

```xml
<dependency>
    <groupId>zone.fryd.sdk</groupId>
    <artifactId>frydSDK</artifactId>
    <version>0.8.8</version>
</dependency>
```
or this to your build.gradle file:
```groovy
compile 'zone.fryd.sdk:frydSDK:0.8.8'
```
### How to make a connection to fryd?

Put your fryd Spot in "Test Mode" in the Developers Dashboard. When your spot is in test mode you can test every trophy without compromising your profile. After quitting the test mode, every progress made during the test by you will be reset.

Ohhh and one other thing: Every function has an *async* brother, so it's for you to decide what you want to use.

Create a FrydOAuthService and provide you **Client Id**, **Client Secret** and your **Redirect URL**.

```java
FrydOAuthService frydOAuthService = 
new FrydOAuthService("clientID", "clientSecret", "https://returnURL.com/reply");
```
Now it is time to get some tokens. Like with every OAUTH2 interface, there are different ways to get a token. You can get a basic token and a refresh token of your fryd Spot with line of code.
```java
OAuth2AccessToken appAccessToken = frydOAuthService.getAppAccessToken();
```
With this kind of token you can get all information regarding your fryd Spot, like informations of your trophylists or trophies within.
The next kind of token is the one from a user. This one is more difficult because you need the users consent to get one. First 
you need the authorization URL for the user to visit to give his consent.

```java
String authorizationUrl = frydOAuthService.getAuthorizationUrl();
```
After the user is finished you get a redirect back to your redirect URL with an authorization code. Looking something like this: 
```
https://returnURL.com/reply?code=eyJhbGci....xcoivjuywk
```
Use the code to get a token and a refresh token of the user.
```java
OAuth2AccessToken userAccessToken = frydOAuthService.getUserAccessToken("code");
```
With this kind of token you can get all user information that the user is willing to share. You also need this token and a token belonging directly to your spot to trigger an achieved trophy for the user.

The last way to get a token is to use a refresh token to get a new one.
```java
OAuth2AccessToken appAccessToken2 = frydOAuthService.refreshToken(appAccessToken);
OAuth2AccessToken userAccessToken2 = frydOAuthService.refreshToken(userAccessToken);
```

### How to use the API?

To use the API you need two things. A token and an object of the FrydAPIService. You can create a FrydAPIService easily from your FrydOAuthService.
```java
FrydAPIService frydAPIService = frydOAuthService.createFrydAPIService();
```
Now it should be easy to get the information you need, just remember to use the correct token for the job. You can get the needed ids of entities from the fryd developer dashboard.

Just one speciality, triggering a trophy for someone. For that you need a general token of you fryd Spot and a user specific token of the user who has achieved the trophy. You also need the secret key of the trophy you wanna trigger (Can be found in the developer dashboard aswell).
```java
frydAPIService.triggerTrophyProgress(userAccessToken, appAccessToken, "YourFrydSpotId", "KeyForTheTrophy");
```

## Thank you
Big thank you to the developers of [scribejava](https://github.com/scribejava/scribejava) for building and maintainig this great libary :)


