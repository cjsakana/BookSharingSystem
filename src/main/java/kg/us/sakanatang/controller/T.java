package kg.us.sakanatang.controller;

import kg.us.sakanatang.common.ApiResponse;
import kg.us.sakanatang.domain.entity.User;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class T {
    @GetMapping("")
    public ApiResponse<String> hello(int a) {
        System.out.println(a);
        return ApiResponse.success("Hello word "+a);
    }
    @PostMapping("")
    public ApiResponse<String> hello2(int a) {
        System.out.println(a);
        return ApiResponse.success("Hello word2 "+a);
    }
}
