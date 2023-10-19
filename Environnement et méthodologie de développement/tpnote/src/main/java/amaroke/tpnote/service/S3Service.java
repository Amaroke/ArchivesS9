package amaroke.tpnote.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.minio.MinioClient;

@Service
public class S3Service {

    private final MinioClient minioClient;
    private final String bucket;

    public S3Service(Environment environment) {

        minioClient = MinioClient.builder()
                .endpoint(environment.getProperty("s3.endpoint"))
                .credentials(environment.getProperty("s3.accesskey"), environment.getProperty("s3.secretkey"))
                .build();
        bucket = environment.getProperty("s3.bucket");

    }

}
