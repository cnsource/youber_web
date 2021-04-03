package com.uxteam.controller;

import com.uxteam.dao.AccountMapper;
import com.uxteam.model.Account;
import com.uxteam.model.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {
    @Autowired
    AccountMapper accountMapper;

    @GetMapping("/registe")
    public String registe() {

        return "registe";
    }
    @PostMapping("/registe")
    @ResponseBody
    public ResultMsg registe(Account account){
        int addAccount = 0;
        boolean status = true;
        String message = "";

        System.out.println("account"+account);
        if(accountMapper.findAccountByUserName(account.getUsername())==null){
            addAccount = accountMapper.addAccount(account);
            if (addAccount > 0)
                message = "账户注册成功";
            else {
                status = false;
                message = "账户注册失败";
            }
        }else {
            status = false;
            message = "账户名已存在";
        }

        return new ResultMsg()
                .status(status)
                .message(message)
                .item("account",account);
    }
}
