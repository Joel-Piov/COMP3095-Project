/*********************************************************************************
 * Project: COMP3095 - Assignment 2
 * Assignment: Assignment 2
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 07/11/2020
 * Description: Controller that handles the dashboard view and redirecting to login
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;
import com.assignment2.COMP3095.models.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@SessionAttributes("client")
@Controller
public class DashboardController {

    String redirectUrl = "redirect:/login";

    @RequestMapping("/dashboard")
    public String dashboardConfig(
            RedirectAttributes redirectAttr,
            HttpSession session
    ){
        return loginError(redirectAttr, redirectUrl, session);
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String dashboardPost(
            RedirectAttributes redirectAttr,
            HttpSession session
    ){
        return loginError(redirectAttr, redirectUrl, session);
    }

    private String loginError(
            RedirectAttributes redAtt,
            String url,
            HttpSession sessionName
    ){
        Client client = (Client) sessionName.getAttribute("client");

        if(client == null){
            redAtt.addFlashAttribute("loginRequired", true);
            return url;
        }
        else if(client.getRole().equals("Client") || client.getRole().equals("Admin")) {
            return "/dashboard";
        }
        else{
            redAtt.addFlashAttribute("loginRequired", true);
            return url;
        }
    }
}