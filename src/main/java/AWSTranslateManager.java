import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

public class AWSTranslateManager {
    private static final AmazonTranslate translate = AmazonTranslateClient.builder()
            .withCredentials(AWSAccoundHolder.getInstance().getAwsCredentialsProvider())
            .withRegion(AWSAccoundHolder.getInstance().getRegion())
            .build();

    private AWSTranslateManager() {
        //private constructor
    }

    public static String translate(String message, String src, String dst) {
        TranslateTextRequest request = new TranslateTextRequest()
                .withText(message)
                .withSourceLanguageCode(src)
                .withTargetLanguageCode(dst);
        TranslateTextResult result = translate.translateText(request);
        return result.getTranslatedText();
    }
}
