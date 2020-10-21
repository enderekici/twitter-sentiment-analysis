import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class Main {
    public static void main(String[] args) {
        String[] strings = "coronavirus".split(",");
        String[] languages = "tr".split(",");
        TwitterStream twitterStream = TwitterStreamFactory.getSingleton().addListener(new TwitterListener()).filter(new FilterQuery(0, null, strings, null, languages));
    }
}
