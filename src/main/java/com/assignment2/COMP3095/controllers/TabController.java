/*********************************************************************************
 * Project: COMP3095 - Assignment 2
 * Assignment: Assignment 2
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 07/11/2020
 * Description: Controller that handles the 4 tabs views, with the same authorization check as controller -
 * checks to see if user has been logged in with correct credentials
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;
import com.assignment2.COMP3095.models.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@SessionAttributes("client")
@Controller
public class TabController {

    String redirectUrl = "redirect:/login";

    @RequestMapping(value="dashboard/tab1", method = RequestMethod.GET )
    public String tab1(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkAccess(redirectAttr, "wip", model, "Tab 1", session);
    }

    @RequestMapping(value="dashboard/tab2", method = RequestMethod.GET )
    public String tab2(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkAccess(redirectAttr, "wip", model, "Tab 2", session);
    }

    @RequestMapping(value="dashboard/tab3", method = RequestMethod.GET )
    public String tab3(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkAccess(redirectAttr, "wip", model, "Tab 3", session);
    }

    @RequestMapping(value="dashboard/tab4", method = RequestMethod.GET)
    public String tab4(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkAccess(redirectAttr, "wip", model, "Tab 4", session);
    }

    //function that checks access privileges and reroutes
    private String checkAccess(
            RedirectAttributes redAtt,
            String viewName,
            Model modelName,
            String tabTitle,
            HttpSession sessionName
    ){
        Client client = (Client)sessionName.getAttribute("client");

        if(client == null){
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        }
        else if(client.getRole().equals("Client") || client.getRole().equals("Admin"))
        {
            modelName.addAttribute("title", tabTitle);
            return viewName;
        }
        else{
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        }
    }
}
