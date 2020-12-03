/*********************************************************************************
 * Project: COMP3095 - Assignment 2
 * Assignment: Assignment 2
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 07/11/2020
 * Description: Controller that handles the login page, forgot password form, and
 * redirects to dashboard on successful. Checks if there is an active session, deletes
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;
import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.User;
import com.assignment2.COMP3095.services.ClientService;
import com.assignment2.COMP3095.services.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController {

    @Autowired
    ClientService repo;


    @Autowired
    RecaptchaService captchaService;

    @RequestMapping({"","/","/index","login"})
    public String index(Model model, HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        model.addAttribute("title", "Login");
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@Valid User user,
                            @ModelAttribute("client") Client client,
                            BindingResult br,
                            Model model,
                            HttpSession session,
                            RedirectAttributes redirectAttr,
                            @RequestParam(name="g-recaptcha-response") String recaptchaResponse,
                            HttpServletRequest request

    ) {
        String ip = request.getRemoteAddr();
        String captchaVerifyMessage =
                captchaService.verifyRecaptcha(ip, recaptchaResponse);

        if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", captchaVerifyMessage);
            model.addAttribute("invalidCaptcha", true);
            return "index";
        } else {
            if (user.getUsername().equals("") || user.getPassword().equals("")) {
                return "index";
            } else {
                if (repo.findByEmail(user.getUsername()) == null) {
                    model.addAttribute("invalidCombo", true);
                    return "index";
                } else {
                    if (user.getPassword().equals(repo.findPassword(user.getUsername()))) {
                        client = repo.findByEmail(user.getUsername());
                        session.setAttribute("client", client);
                        redirectAttr.addFlashAttribute("client", client);
                        return "redirect:/dashboard";
                    } else {
                        model.addAttribute("invalidCombo", true);
                        return "index";
                    }
                }
            }
        }
    }
}