package kg.us.sakanatang.service;

import kg.us.sakanatang.domain.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sakana
* @description 针对表【tag(分享书籍表)】的数据库操作Service
* @createDate 2025-04-07 22:51:47
*/
public interface TagService extends IService<Tag> {
    // 创建标签
    Tag createTag(String name);
    
    // 删除标签
    boolean deleteTag(Integer id);
    
    // 更新标签
    boolean updateTag(Tag tag);
    
    // 获取所有标签
    List<Tag> getAllTags();
    
    // 批量获取标签
    List<Tag> getTagsByIds(List<Integer> ids);
}
