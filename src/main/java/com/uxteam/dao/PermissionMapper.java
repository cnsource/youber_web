package com.uxteam.dao;

import com.uxteam.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @Select("select * from permission where id in (select id from account_permission where username = #{name})")
    List<Permission> getPermissionsByUserName(String name);
}
