package com.zq.blog.web.admin;

import com.zq.blog.po.SupperAdmin;
import com.zq.blog.po.User;
import com.zq.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Resource
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes, Model model){
            User user = userService.checkUser(username, password);
            if (user != null) {
                user.setPassword(null);
                session.setAttribute("user",user);
                model.addAttribute("user",user);
                return "admin/index";
            }else {
                attributes.addFlashAttribute("message","用户名密码错误");
                return "redirect:/admin";
            }

    }

    @GetMapping("/loginout")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
