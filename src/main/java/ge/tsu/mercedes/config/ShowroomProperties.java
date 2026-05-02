package ge.tsu.mercedes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "showroom")
public class ShowroomProperties {

    private String name;
    private String supportEmail;
    private int featuredLimit;
    private String uploadDir;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public int getFeaturedLimit() {
        return featuredLimit;
    }

    public void setFeaturedLimit(int featuredLimit) {
        this.featuredLimit = featuredLimit;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
