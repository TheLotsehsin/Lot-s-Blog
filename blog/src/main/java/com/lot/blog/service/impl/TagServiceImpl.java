package com.lot.blog.service.impl;

import com.lot.blog.dao.TagDao;
import com.lot.blog.entity.Tag;
import com.lot.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog
 * @description: 标签业务接口实现
 * @author: lotsehsin
 * @create: 2022-03-03 14:51
 **/
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> getTagList(Long id) {
        return tagDao.getTagList(id);
    }

    @Override
    public List<Tag> getIndexTag() {
        return tagDao.getIndexTag();
    }

    @Override
    public List<Tag> getIndexTag1() {
        return tagDao.getIndexTag1();
    }

    @Override
    public List<Tag> getTagsByString(String ids) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(ids);
        for (Long long1 : longs){
            tags.add(tagDao.getTag(long1));
        }
        return tags;
    }

    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(Long.valueOf(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }
}
