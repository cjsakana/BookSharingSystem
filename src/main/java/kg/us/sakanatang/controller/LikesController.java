package kg.us.sakanatang.controller;

import kg.us.sakanatang.common.ApiResponse;
import kg.us.sakanatang.domain.vo.LikesVO;
import kg.us.sakanatang.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

    @Autowired
    private LikesService likesService;

    @PostMapping("/like")
    public ApiResponse<String> like(@RequestAttribute(value = "id", required = true) int userid, @RequestBody Integer articleId) {
        if (!likesService.likeArticle(userid, articleId)) {
            return ApiResponse.fail(500, "获取失败");
        }
        return ApiResponse.success();
    }

    @PostMapping("/unlike")
    public ApiResponse<String> unlike(@RequestAttribute(value = "id", required = true) int userid, @RequestBody Integer articleId) {
        try {
            if (!likesService.unlikeArticle(userid, articleId)) {
                return ApiResponse.fail(500, "获取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ApiResponse.success();
    }

    @GetMapping("/user/{userId}/count")
    public ApiResponse<Integer> countUserReceived(@PathVariable Integer userId) {
        int count = likesService.countLikesReceivedByUser(userId);
        return ApiResponse.success(count);
    }

    @GetMapping("/article/{articleId}/count")
    public ApiResponse<Integer> countArticleLikes(@PathVariable Integer articleId) {
        int count = likesService.countLikesByArticle(articleId);
        return ApiResponse.success(count);
    }

    @GetMapping("/isLiked")
    public ApiResponse<Boolean> isLiked(@RequestAttribute(value = "id", required = true) int userid,
                                        @RequestParam Integer articleId) {
        try {
            boolean flag = likesService.isLiked(userid, articleId);
            return ApiResponse.success(flag);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.fail(500, "失败");
        }

    }

    @GetMapping("/list")
    public ApiResponse<List<LikesVO>> getLikesList(
            @RequestAttribute(value = "id", required = true) int userid,
            @RequestParam long createdAt,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        try {
            List<LikesVO>likesVOS= likesService.getLikesList(userid, new Date(createdAt), page, size);
            return ApiResponse.success(likesVOS);
        }catch (Exception e){
            return ApiResponse.fail(500,"fail");

        }
    }
}