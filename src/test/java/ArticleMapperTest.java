import kg.us.sakanatang.config.SpringConfig;
import kg.us.sakanatang.domain.vo.ArticleVO;
import kg.us.sakanatang.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ArticleMapperTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void findArticle() {
        int page = 1;
        int size = 10;
        int tagId = 0;
        String content = "";
        int offset = (page - 1) * size;
        articleMapper.getArticleVOList(tagId, content, offset, size);
    }
}
