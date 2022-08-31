package com.scarit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scarit.entity.ExerciseUser;
import com.scarit.entity.LoginUser;
import com.scarit.service.ExerciseUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class IndexController {

    @Resource
    private ExerciseUserService userService;

    @PostMapping("login")
    public ResponseEntity<LoginUser> login(@RequestBody @Validated ExerciseUser exerciseUser, BindingResult bindingResult) {

        //1.处理不合法的数据
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        allErrors.forEach( error -> log.error("登录时用户校验失败 ：{}",error.getDefaultMessage()));
        if(allErrors.size() > 0){
            return ResponseEntity.status(500).build();
        }

        //2.执行登录逻辑
        LoginUser loginUser = null;
        loginUser = userService.login(exerciseUser.getUserName(), exerciseUser.getPassword());
        return ResponseEntity.ok().body(loginUser);
    }
    @GetMapping("logout")
    public ResponseEntity<String> logout() {

        try{
            userService.logout();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok().body("退出成功");
    }
}
