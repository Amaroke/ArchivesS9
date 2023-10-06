package amaroke.projet_cm.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;

@Service
public class S3Service {

    private MinioClient minioClient;

    public S3Service(Environment environment) {

        minioClient = MinioClient.builder()
                .endpoint(environment.getProperty("s3.endpoint"))
                .credentials(environment.getProperty("s3.accesskey"), environment.getProperty("s3.secretkey"))
                .build();

    }

    public String getCover(Integer livreId) {
        try {
            String bucketName = "coursm2";
            String objectName = "amaroke" + livreId.toString();

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .method(Method.GET)
                    .build());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    public String addCover(Integer livreId) {
        try {
            String bucketName = "coursm2";
            String objectName = "amaroke" + livreId.toString();

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .method(Method.PUT)
                    .build());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";

    }

}
