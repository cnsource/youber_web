package com.uxteam.dao;

import com.uxteam.model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Select("select * from account where username = #{username}")
    Account findAccountByUserName(String username);

    @Insert("insert into account (username,password)values(#{username},#{password})")
    int addAccount(Account account);
}
