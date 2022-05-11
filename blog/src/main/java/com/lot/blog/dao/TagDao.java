package com.lot.blog.dao;

import com.lot.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagDao {

    int saveTag(Tag tag);

    Tag getTagByName(String name);

    Tag getTag(Long id);

    List<Tag> getAllTag();

    List<Tag> getIndexTag();

    List<Tag> getIndexTag1();

    List<Tag> getTagList(Long id);

    int updateTag(Tag tag);

    int deleteTag(Long id);
}
