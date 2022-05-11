package com.lot.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lot.blog.entity.Tag;
import com.lot.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @program: blog
 * @description: 标签控制类
 * @author: lotsehsin
 * @create: 2022-03-03 14:58
 **/
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String enterTags(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pagenum
            , Model model) {
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pagenum, 5, orderBy);
        List<Tag> tagList = tagService.getAllTag();
        //得到分页结果对象
        PageInfo<Tag> typePageInfo = new PageInfo<>(tagList);
        model.addAttribute("pageInfo", typePageInfo);
        return "admin/adminTags";
    }

    @GetMapping("/tags/input")
    public String inpuTags(Model model){
        //返回一个tag对象给前端th:object，这里这个Tag类里面的id和name是nul
        model.addAttribute("tag",new Tag());
        return "admin/tagsInput";
    }

    @GetMapping("/tags/{id}/input")
    public String enterUpdateTags(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tagsInput";
    }

    @PostMapping("/tags")
    public String postTags(Tag tag, Model model,RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            model.addAttribute("message","标签添加重复");
            return "redirect:/admin/tags/input";
        }
        int res = tagService.saveTag(tag);
        if (res ==0 ){
            attributes.addFlashAttribute("message","标签添加失败");
        }else{
            attributes.addFlashAttribute("message","标签添加成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String updateTags(@PathVariable Long id, Tag tag, Model model,RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            model.addAttribute("message","标签内容重复");
            return "redirect:/admin/tags/input";
        }
        int res = tagService.updateTag(tag);
        if (res ==0 ){
            attributes.addFlashAttribute("message","标签更新失败");
        }else{
            attributes.addFlashAttribute("message","标签更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTags(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","标签删除成功");
        return "redirect:/admin/tags";
    }

}
