package kg.us.sakanatang.controller;

//import kg.us.sakanatang.service.S3Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/api/images")
//public class ImageController {
//
//    @Autowired
//    private S3Service s3Service;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
//        try {
//            String imageUrl = s3Service.uploadImage(file);
//            return ResponseEntity.ok(imageUrl);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("图片上传失败: " + e.getMessage());
//        }
//    }
//}