import com.amazonaws.services.comprehend.model.DetectSentimentResult;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.log4j.Log4j2;

/**
 * Created by cemserit on 12.06.2018.
 */
@Log4j2
public class TwitterListener implements StatusListener {
    private final AtomicLong limitCounter;

    public TwitterListener() {
        this.limitCounter = new AtomicLong();
    }

    @Override
    public void onException(Exception e) {
        log.warn("Stream feed failed", e);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice arg) {
        log.debug("deletion notice {}", arg);
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        log.debug("scrub geo {} {}", userId, upToStatusId);
    }

    @Override
    public void onStallWarning(StallWarning warning) {
        log.debug("stall warning {}", warning);
    }

    @Override
    public void onStatus(Status status) {
        sendStream(status);
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        log.debug("Track limitation notice {}", numberOfLimitedStatuses);
    }

    private void sendStream(Status status) {
        limitCounter.incrementAndGet();
        String statusText = status.getText();
        String translatedText = null;
        String tmp;
        boolean isTranslated = false;
        if (!Language.en.name().equals(status.getLang())) {
            translatedText = AWSTranslateManager.translate(statusText, status.getLang(), Language.en.name());
            tmp = translatedText;
            isTranslated = true;
        } else {
            tmp = statusText;
        }
        DetectSentimentResult sentimentResult = AWSComprehendManager.analyze(tmp, Language.en.name());
        log.info(MessageFormat.format("{0}-{1}-{2}-{3}-{4}-{5}", limitCounter.get(), isTranslated, statusText, translatedText, sentimentResult, status));

    }
}
