package com.lot.blog.service;

import com.lot.blog.entity.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);

    Type getTypeByName(String name);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getIndexType();

    List<Type> getIndexType1();

    List<Type> getTypeList(Long id);

    int updateType(Type type);

    int deleteType(Long id);
}
