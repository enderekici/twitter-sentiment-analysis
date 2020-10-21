import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.PropertiesFileCredentialsProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AWSAccoundHolder {
    private static final AWSAccoundHolder       instance = new AWSAccoundHolder("AwsCredentials.properties");
    private              AWSCredentialsProvider awsCredentialsProvider;
    private              String                 region;

    private AWSAccoundHolder(String credentialsFilePath) {
        try (final InputStream stream = this.getClass().getResourceAsStream(credentialsFilePath)) {
            Properties properties = new Properties();
            properties.load(stream);
            this.awsCredentialsProvider = new PropertiesFileCredentialsProvider(this.getClass().getResource(credentialsFilePath).getPath());
            this.region = properties.getProperty("region");
        } catch (IOException e) {
            log.error("Error while obtaining credentials", e);
        }
    }

    public static AWSAccoundHolder getInstance() {
        return instance;
    }

    public AWSCredentialsProvider getAwsCredentialsProvider() {
        return awsCredentialsProvider;
    }

    public String getRegion() {
        return region;
    }
}
