import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.Status;
import twitter4j.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class U {
    public static Map<String, String> getMap(Status status) {
        User user = status.getUser();
        String name = user.getName();
        long id = user.getId();
        String email = user.getEmail();
        String tweet;
        if (status.getRetweetedStatus() != null && status.getRetweetedStatus().getText() != null) {
            tweet = status.getRetweetedStatus().getText().replace("\\", "\\\\").replace("\"", "\\\"");
        } else {
            tweet = status.getText().replace("\\", "\\\\").replace("\"", "\\\"");
        }

        String tweetLang = status.getLang();
        long tweetId = status.getId();
        String screenName = user.getScreenName();
        String location = user.getLocation();
        String description = user.getDescription();
        String userLang = user.getLang();
        String source = status.getSource();
        int followersCount = user.getFollowersCount();
        int friendsCount = user.getFriendsCount();
        long userCreatedAt = user.getCreatedAt().getTime();
        long createdAt = status.getCreatedAt().getTime();

        Place place = status.getPlace();

        String placeName = null;
        String placeFullName = null;
        String placeCountry = null;
        String placeCountryCode = null;
        String placeBoundingBoxtype = null;
        String placeGeometryType = null;
        GeoLocation[][] geoLocation = null;

        if (place != null) {
            placeName = place.getName();
            placeFullName = place.getFullName();
            placeCountry = place.getCountry();
            placeCountryCode = place.getCountryCode();
            placeBoundingBoxtype = place.getBoundingBoxType();
            placeGeometryType = place.getGeometryType();
            geoLocation = place.getGeometryCoordinates();
        }

        if (source != null && !source.isEmpty()) {
            source = getSource(source);
        }

        Map<String, String> parameterMap = new LinkedHashMap<>();

        parameterMap.put("id", String.valueOf(id));
        parameterMap.put("tweetId", String.valueOf(tweetId));
        parameterMap.put("name", name);
        parameterMap.put("email", email);
        parameterMap.put("description", description);
        parameterMap.put("followersCount", String.valueOf(followersCount));
        parameterMap.put("friendsCount", String.valueOf(friendsCount));
        parameterMap.put("userLang", userLang);
        parameterMap.put("userCreatedAt", String.valueOf(userCreatedAt));
        parameterMap.put("location", location);
        parameterMap.put("tweet", tweet);
        parameterMap.put("createdAt", String.valueOf(createdAt));
        parameterMap.put("source", source);
        parameterMap.put("placeName", placeName);
        parameterMap.put("placeFullName", placeFullName);
        parameterMap.put("placeCountry", placeCountry);
        parameterMap.put("tweetLang", tweetLang);
        parameterMap.put("placeCountryCode", placeCountryCode);
        parameterMap.put("placeBoundingBoxtype", placeBoundingBoxtype);
        parameterMap.put("placeGeometryType", placeGeometryType);
        parameterMap.put("geoLocation", Arrays.deepToString(geoLocation));
        parameterMap.put("screenName", screenName);
        for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
            if (entry.getValue() == null) {
                entry.setValue("null");
            }
        }
        return parameterMap;
    }

    private static String getSource(String sourceTag) {
        String source = null;
        Matcher matcher = Pattern.compile("<a (.*?)>(.*?)</a>").matcher(sourceTag);
        if (matcher.find()) {
            source = matcher.group(2);
        }
        return source;
    }
}
