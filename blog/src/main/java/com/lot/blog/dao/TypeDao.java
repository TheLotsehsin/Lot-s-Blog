package com.lot.blog.dao;

import com.lot.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface TypeDao {

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
