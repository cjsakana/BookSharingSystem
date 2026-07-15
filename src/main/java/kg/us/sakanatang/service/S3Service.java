package kg.us.sakanatang.service;

//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Objects;
//
//@Service
//public class S3Service {
//
//    @Autowired
//    private AmazonS3 amazonS3;
//
//    @Value("${aws.s3.bucket-name}")
//    private String bucketName;
//
//    public String uploadImage(MultipartFile multipartFile) throws IOException {
//        // 转换MultipartFile为File
//        File file = convertMultiPartToFile(multipartFile);
//
//        // 生成唯一文件名
//        String fileName = generateFileName(multipartFile);
//
//        // 上传文件到S3
//        uploadFileToS3(fileName, file);
//
//        // 删除临时文件
//        file.delete();
//
//        // 返回文件URL
//        return amazonS3.getUrl(bucketName, fileName).toString();
//    }
//
//    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }
//
//    private String generateFileName(MultipartFile multiPart) {
//        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
//    }
//
//    private void uploadFileToS3(String fileName, File file) {
//        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
//                .withCannedAcl(CannedAccessControlList.PublicRead));
//    }
//}