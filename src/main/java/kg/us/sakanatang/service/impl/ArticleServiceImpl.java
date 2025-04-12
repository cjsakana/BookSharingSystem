package kg.us.sakanatang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kg.us.sakanatang.domain.entity.Article;
import kg.us.sakanatang.mapper.UserMapper;
import kg.us.sakanatang.service.ArticleService;
import kg.us.sakanatang.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author sakana
* @description 针对表【article(分享书籍表)】的数据库操作Service实现
* @createDate 2025-04-07 22:51:35
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
}




