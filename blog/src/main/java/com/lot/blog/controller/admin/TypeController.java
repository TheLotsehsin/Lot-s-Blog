package com.lot.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lot.blog.dao.TypeDao;
import com.lot.blog.entity.Type;
import com.lot.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog
 * @description: 类型控制类
 * @author: lotsehsin
 * @create: 2022-03-01 11:48
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    //分页查询分类列表
    @GetMapping("/types")
    public String enterTypes(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pagenum
            , Model model) {
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pagenum, 5, orderBy);
        List<Type> typeList = typeService.getAllType();
        //得到分页结果对象
        PageInfo<Type> typePageInfo = new PageInfo<>(typeList);
        model.addAttribute("pageInfo", typePageInfo);
        return "admin/adminTypes";
    }

    //进入新增分类页面
    @GetMapping("/types/input")
    public String inputType(Model model) {
        //返回一个type对象给前端th:object，这里这个Type类里面的id和name是null
        model.addAttribute("type", new Type());
        return "admin/typesInput";
    }

    //进入修改页面
    @GetMapping("/types/{id}/input")
    public String enterUpdateType(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/typesInput";
    }

    //新增分类(没带id是添加标签)
    @PostMapping("/types")
    public String postType(Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "标签重复添加");
            return "redirect:/admin/types/input";
        }
        int res = typeService.saveType(type);
        if (res == 1) {
            attributes.addFlashAttribute("message", "添加成功！");
        }else{
            attributes.addFlashAttribute("message", "添加失败！");
        }
        return "redirect:/admin/types";

    }

    //类型信息更新(带id是修改标签)
    @PostMapping("/types/{id}")
    public String updateType(@PathVariable Long id,Type type,RedirectAttributes attributes){
        Type type2 = typeService.getTypeByName(type.getName());
        if (type2 != null) {
            attributes.addFlashAttribute("message", "标签重复添加");
            return "redirect:/admin/types/input";
        }
        int res = typeService.updateType(type);
        if (res == 1) {
            attributes.addFlashAttribute("message", "修改成功！");
        }else{
            attributes.addFlashAttribute("message", "修改失败！");
        }
        return "redirect:/admin/types";
    }

    //类型信息删除
    @GetMapping("/types/{id}/delete")
    public  String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        int res = typeService.deleteType(id);
        if (res == 1){
            attributes.addFlashAttribute("message", "添加成功！");
        }else{
            attributes.addFlashAttribute("message", "添加失败！");
        }
        return "redirect:/admin/types";
    }
}
