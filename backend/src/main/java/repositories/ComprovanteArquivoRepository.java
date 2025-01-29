package repositories;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import jdbc.BaseRespository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ComprovanteArquivoRepository extends BaseRespository {
    private static String bucket = "academiacomprovante";

    static {
        try {
            if (!getMinioClient().bucketExists(BucketExistsArgs.builder().bucket(bucket).build())){
                getMinioClient().makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
                System.out.println("Bucket " + bucket + " criado");
            } else{
                System.out.println("Bucket " + bucket + " já existe");
            }
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    public int uploadFile(String nomeArquivo, String tipoArquivo, FileInputStream inputStream) {
        try{
            getMinioClient().
                    putObject(
                            PutObjectArgs.
                                    builder()
                                    .bucket(bucket)
                                    .object(nomeArquivo.concat(".").concat(tipoArquivo))
                                    .stream(inputStream, -1, 10485760)
                                    .build());
            return 1;
        } catch (ServerException | InsufficientDataException | IOException | NoSuchAlgorithmException |
                 InvalidKeyException | InvalidResponseException | XmlParserException | InternalException |
                 ErrorResponseException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream getObjectFile(String nomeArquivo, String tipoArquivo) {
        InputStream file;
        try {
            file = getMinioClient().getObject(GetObjectArgs.builder().bucket(bucket).object(nomeArquivo.concat(".").concat(tipoArquivo)).build());
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public String getBucket() {
        return bucket;
    }
}
