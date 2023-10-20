package amaroke.tpnote.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;

@Service
public class S3Service {

    private final MinioClient minioClient;
    private final String illustrations;

    public S3Service(Environment environment) {

        minioClient = MinioClient.builder()
                .endpoint(environment.getProperty("s3.endpoint"))
                .credentials(environment.getProperty("s3.accesskey"), environment.getProperty("s3.secretkey"))
                .build();
        illustrations = environment.getProperty("s3.examillustrations");

    }

    public String getCover(Integer restaurantId) {
        try {
            String objectName = "amaroke" + restaurantId.toString();

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(this.illustrations)
                    .object(objectName)
                    .method(Method.GET)
                    .build());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String addCover(Integer restaurantId) {
        try {
            String objectName = "amaroke" + restaurantId.toString();

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(this.illustrations)
                    .object(objectName)
                    .method(Method.PUT)
                    .build());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
