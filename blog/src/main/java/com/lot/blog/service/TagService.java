package com.lot.blog.service;

import com.lot.blog.entity.Tag;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);

    Tag getTagByName(String name);

    Tag getTag(Long id);

    List<Tag> getTagsByString(String ids);

    List<Tag> getAllTag();

    List<Tag> getIndexTag();

    List<Tag> getIndexTag1();

    List<Tag> getTagList(Long id);

    int updateTag(Tag tag);

    int deleteTag(Long id);
}
