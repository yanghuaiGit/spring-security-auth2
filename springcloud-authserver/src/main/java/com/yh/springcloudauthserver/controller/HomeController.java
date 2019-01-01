package com.yh.springcloudauthserver.controller;


import com.yh.springcloudauthserver.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yangyibo on 17/1/18.
 * @author Yh
 */


@Controller
public class HomeController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }


    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }

    @RequestMapping(value = "/login")

    public String login(){

        return "login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String getList(@RequestParam(required = false)String code){
        if(code==null){
            return "hello getList";
        }

        //将lab02的callback操作复制到这儿就可以了 转发的一个请求啊

        return "hello getList"+code;
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String save(){
        return "hello save";
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public String update(){
        return "hello update";
    }
}
