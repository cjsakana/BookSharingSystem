package kg.us.sakanatang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kg.us.sakanatang.domain.entity.Likes;
import kg.us.sakanatang.service.LikesService;
import kg.us.sakanatang.mapper.LikesMapper;
import org.springframework.stereotype.Service;

/**
* @author sakana
* @description 针对表【likes(点赞表)】的数据库操作Service实现
* @createDate 2025-04-08 13:33:47
*/
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes>
    implements LikesService{

}




