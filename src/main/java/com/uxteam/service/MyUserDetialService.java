package com.uxteam.service;

import com.uxteam.dao.AccountMapper;
import com.uxteam.dao.PermissionMapper;
import com.uxteam.model.Account;
import com.uxteam.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetialService implements UserDetailsService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    AccountMapper accountMapper;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountMapper.findAccountByUserName(username);
        if (account == null){
            throw new UsernameNotFoundException(username);
        }
        List<Permission> permissions = permissionMapper.getPermissionsByUserName(account.getUsername());
        UserDetails userDetails = User.withUsername(account.getUsername())
                .password(account.getPassword())
                .authorities(getPermissionsCode(permissions))
                .build();
        return userDetails;
    }
    private String[] getPermissionsCode(List<Permission> permissions){
        String[] pers = new String[permissions.size()];
        for(int i = 0;i<permissions.size();i++){
            pers[i] = permissions.get(i).getCode();
        }
        return pers;
    }
}
