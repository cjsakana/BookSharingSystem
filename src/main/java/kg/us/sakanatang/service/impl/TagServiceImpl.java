package kg.us.sakanatang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kg.us.sakanatang.domain.entity.Tag;
import kg.us.sakanatang.service.TagService;
import kg.us.sakanatang.mapper.TagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sakana
* @description 针对表【tag(分享书籍表)】的数据库操作Service实现
* @createDate 2025-04-07 22:51:47
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    @Override
    public Tag createTag(String name) {
        return null;
    }

    @Override
    public boolean deleteTag(Integer id) {
        return false;
    }

    @Override
    public boolean updateTag(Tag tag) {
        return false;
    }

    @Override
    public List<Tag> getAllTags() {
        return null;
    }

    @Override
    public List<Tag> getTagsByIds(List<Integer> ids) {
        return null;
    }
}




