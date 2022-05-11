package com.lot.blog.queryEntity;

import java.util.Date;
import java.util.List;

import com.lot.blog.entity.Tag;
import lombok.*;
/**
 * @program: blog
 * @description: 显示博客信息
 * @author: lotsehsin
 * @create: 2022-03-06 01:39
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowBlog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private String tagIds;
    private String description;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Date updateTime;
    private Long typeId;

    private List<Tag> tags;

    //初始化得到tagIds的集合，一会要用到
    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags) {
        if(!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags) {
                if(flag)
                    ids.append(",");
                else
                    flag = true;
                ids.append(tag.getId());
            }
            return ids.toString();
        }
        else
            return tagIds;
    }
}
