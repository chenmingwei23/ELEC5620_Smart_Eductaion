package com.ELEC5620.controller;

import com.ELEC5620.anotation.LoginRequired;
import com.ELEC5620.entity.User;
import com.ELEC5620.service.UserService;
import com.ELEC5620.util.EmailClient;
import com.ELEC5620.util.HostHolder;
import com.ELEC5620.util.NineyardConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller //有这样的注解的bean才会被扫描或者@service, @Repository
@RequestMapping("/user")
public class UserController implements NineyardConstants {
    @Autowired
    private UserService userService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${server.address}")
    private String ip;

    @Value("${server.port}")
    private String port;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public String getSigninPage() {

        return "/user/signin";
    }

    @RequestMapping(path = "/signin", method = RequestMethod.POST)
    public String signin(String email, String password, boolean rememberme, Model model, HttpServletResponse response) {
        System.out.println(email);
        System.out.println(password);
        System.out.println(rememberme);
        int expiredSeconds = rememberme ? REMEMBER_EXPIRED_SECONDS : DEFAULT_EXPIRED_SECONDS;
        Map<String, Object> map = userService.login(email, password, expiredSeconds);
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie);
            return "redirect:/user/profile";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            return "/user/signin";
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "/user/register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(Model model, User user, int type) {
        user.setType(type);
        user.setCreateTime(new Date());
        user.setCompanyId(-1);
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()) {
            String url = "/user/create-company/" + user.getId();
            return "redirect:" + url;
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/user/register";
        }
    }

    @LoginRequired
    @RequestMapping(path = "/create-company/{userId}", method = RequestMethod.GET)
    public String getCreatePage(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("userId", userId);
        return "/user/company-creation";
    }


    @LoginRequired
    @RequestMapping(path = "/create-company", method = RequestMethod.GET)
    public String getCreatClientCompanyPage(Model model) {
        model.addAttribute("userId", -1);
        return "/user/company-creation";
    }


    @LoginRequired
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/user/login";
    }

    @RequestMapping(path = "/send-email", method = RequestMethod.GET)
    public String getForgetPage() {
        return "/user/send-email";
    }

    @RequestMapping(path = "/send-email", method = RequestMethod.POST)
    public String sendForgetEmail(String email) {
        User user = userService.findByEmail(email);
        String code = userService.encodeUserId(user.getId());
        String content = "Dear " + user.getFirstName() + ", " +
                "please visit this page to reset your password: " +
                ip + ":" + port + contextPath + "/user/reset-password/" + code;
        emailClient.sendMail(user.getEmail(), EMAIL_TITLE_RESETPASSWORD, content);
        return "/user/send-email";
    }

    @RequestMapping(path = "/reset-password/{code}", method = RequestMethod.GET)
    public String resetPassword(Model model, @PathVariable("code") String code) {
        model.addAttribute("code", code);
        return "/user/reset-password";
    }


    // 获取验证码
    @RequestMapping(path = "/reset-password", method = RequestMethod.POST)
    public String resetPassword(Model model, String code, String password) {
        Map<String, Object> map = userService.resetPassword(code, password);
        if (map == null || map.isEmpty()) {
            return "redirect: /user/signin";
        } else {
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            return "/user/reset-password/" + code;
        }
    }

    @LoginRequired
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String getupdatePage(Model model) {
        model.addAttribute("departments", ALL_DEPARTMENT);
        model.addAttribute("positions", ALL_POSITION);
        return "/user/profile";
    }

    @LoginRequired
    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public String updatePage(String mobile, String landline, String address, String department,
                             String position, String otherContactName, String otherContactNumber) {
        User user = hostHolder.getUser();
        if (mobile != null) {
            user.setMobile(mobile);
        }
        if (landline != null) {
            user.setLandline(landline);
        }
        if (address != null) {
            user.setAddress(address);
        }
        if (department != null) {
            user.setDepartment(department);
        }
        if (position != null) {
            user.setPosition(position);
        }
        if (otherContactName != null) {
            user.setOtherContactName(otherContactName);
        }
        if (otherContactNumber != null) {
            user.setOtherContactNumber(otherContactNumber);
        }
        userService.updateUser(user);
        return "redirect:/user/profile";
    }

    @LoginRequired
    @RequestMapping(path = "/client-list", method = RequestMethod.GET)
    public String getClientList(Model model) {
        User user = hostHolder.getUser();
        List<User> clients = userService.selectClient(user.getId());
        model.addAttribute("clients", clients);
        return "/user/client-list";
    }

    @LoginRequired
    @RequestMapping(path = "/employee-creation", method = RequestMethod.GET)
    public String getEmployeeCreationPage(Model model) {
        model.addAttribute("departments", ALL_DEPARTMENT);
        model.addAttribute("positions", ALL_POSITION);
        return "/user/employee-creation";
    }

    @RequestMapping(path = "/employee-creation", method = RequestMethod.POST)
    public String employeeCreation(Model model, User user) {
        user.setPmId(hostHolder.getUser().getId());
        user.setCreateTime(new Date());
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()) {
            return "redirect:/user/employee-list";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/user/employee-creation";
        }
    }

    @LoginRequired
    @RequestMapping(path = "/client-creation", method = RequestMethod.POST)
    public String clientCreation(Model model, User user) {
        user.setPmId(hostHolder.getUser().getId());
        user.setCreateTime(new Date());
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()) {
            return "redirect:/user/client-list";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/user/client-creation";
        }
    }
}
