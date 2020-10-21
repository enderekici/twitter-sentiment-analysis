import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;

public class AWSComprehendManager {
    private static final AmazonComprehend comprehendClient = AmazonComprehendClientBuilder.standard()
            .withCredentials(AWSAccoundHolder.getInstance().getAwsCredentialsProvider())
            .withRegion(AWSAccoundHolder.getInstance().getRegion())
            .build();

    private AWSComprehendManager() {
        //private constructor
    }

    public static DetectSentimentResult analyze(String message, String lang) {
        DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest().withText(message)
                .withLanguageCode(lang);
        return comprehendClient.detectSentiment(detectSentimentRequest);
    }
}
