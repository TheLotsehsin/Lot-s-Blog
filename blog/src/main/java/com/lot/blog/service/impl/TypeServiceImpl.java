package com.lot.blog.service.impl;

import com.lot.blog.dao.TypeDao;
import com.lot.blog.entity.Type;
import com.lot.blog.hander.NotFoundException;
import com.lot.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: blog
 * @description: 类型实业务现类
 * @author: lotsehsin
 * @create: 2022-02-28 23:11
 **/

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;



    @Override
    public int saveType(Type type){
        return typeDao.saveType(type);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public  List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public List<Type> getIndexType() {
        return typeDao.getIndexType();
    }

    @Override
    public List<Type> getIndexType1() {
        return typeDao.getIndexType1();
    }

    @Override
    public List<Type> getTypeList(Long id){
        return typeDao.getTypeList(id);
    }

    @Override
    public int updateType(Type type){
        return typeDao.updateType(type);
    }

    public int deleteType(Long id){
        return typeDao.deleteType(id);
    }
}
