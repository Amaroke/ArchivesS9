package amaroke.exofinal.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;

@Service
public class S3Service {

    private final MinioClient minioClient;
    private final String coverBucket;
    private final String screenshotBucket;

    public S3Service(Environment environment) {

        minioClient = MinioClient.builder()
                .endpoint(environment.getProperty("s3.endpoint"))
                .credentials(environment.getProperty("s3.accesskey"), environment.getProperty("s3.secretkey"))
                .build();
        coverBucket = environment.getProperty("s3.cover");
        screenshotBucket = environment.getProperty("s3.screenshot");

    }

    public String getCover(Integer serieId) {
        try {
            String objectName = "amaroke" + serieId.toString();

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(this.coverBucket)
                    .object(objectName)
                    .method(Method.GET)
                    .build());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String addCover(Integer serieId) {
        try {
            String objectName = "amaroke" + serieId.toString();

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(this.coverBucket)
                    .object(objectName)
                    .method(Method.PUT)
                    .build());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getScreenshot(Integer serieId) {
        try {
            String objectName = "amaroke" + serieId.toString();

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(this.screenshotBucket)
                    .object(objectName)
                    .method(Method.GET)
                    .build());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String addScreenshot(Integer serieId) {
        try {
            String objectName = "amaroke" + serieId.toString();

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(this.screenshotBucket)
                    .object(objectName)
                    .method(Method.PUT)
                    .build());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
