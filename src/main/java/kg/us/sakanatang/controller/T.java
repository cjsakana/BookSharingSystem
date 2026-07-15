package kg.us.sakanatang.controller;

import kg.us.sakanatang.common.ApiResponse;
import kg.us.sakanatang.domain.entity.User;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class T {

    @GetMapping("")
    public ApiResponse<T> hello() {
        return ApiResponse.fail(500, "用户注册失败");
    }

}
