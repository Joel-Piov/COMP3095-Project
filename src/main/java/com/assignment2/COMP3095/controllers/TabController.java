/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 04/12/2020
 * Description: Controller that handles the routing and logic for the tabs for the Admin and Client users.
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

    //======================================SHARED TABS==============================================================//

    //Profile Tab ---------> Checks to see if the user is a Client or Admin and provides the correct profile page
    @RequestMapping(value="dashboard/profile", method = RequestMethod.GET )
    public String profile(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkBothAccess(redirectAttr, "profile", "profile-admin", model, "Profile", session);
    }

    //Inbox Tab
    @RequestMapping(value="dashboard/inbox", method = RequestMethod.GET )
    public String inbox(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkBothAccess(redirectAttr, "inbox","inbox-admin", model, "Inbox", session);
    }

    //======================================CLIENT TABS==============================================================//

    //Credit Info Tab
    @RequestMapping(value="dashboard/credit", method = RequestMethod.GET )
    public String credit(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkAccess(redirectAttr, "credit-profile", model, "Credit Profile", session);
    }

    //Support Tab
    @RequestMapping(value="dashboard/support", method = RequestMethod.GET)
    public String support(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkAccess(redirectAttr, "support", model, "Support", session);
    }

    //======================================ADMIN TABS==============================================================//

    //User Listings Tab
        @RequestMapping(value="dashboard/user-listings", method = RequestMethod.GET)
        public String userListing(
                RedirectAttributes redirectAttr,
                Model model,
                HttpSession session
        ){
            return checkAccess(redirectAttr, "userListing-admin", model, "Clients", session);
        }

    //User Listings Tab
    @RequestMapping(value="dashboard/admin-listings", method = RequestMethod.GET)
    public String adminListing(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkAccess(redirectAttr, "adminListing-admin", model, "Admins", session);
    }

    //======================================CHECK FUNCTIONS=========================================================//

    //function that checks access privileges and reroutes
    private String checkAccess(
            RedirectAttributes redAtt,
            String viewName,
            Model modelName,
            String tabTitle,
            HttpSession sessionName
    ) {
        Client client = (Client) sessionName.getAttribute("client");

        if (client == null) {
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        } else if (client.getRole().equals("Client") || client.getRole().equals("Admin")) {
            modelName.addAttribute("title", tabTitle);
            return viewName;
        } else {
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        }
    }

    private String checkBothAccess(
        RedirectAttributes redAtt,
        String viewNameClient,
        String viewNameAdmin,
        Model modelName,
        String tabTitle,
        HttpSession sessionName
    ){
        Client client = (Client)sessionName.getAttribute("client");

        if(client == null){
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        }
        else if(client.getRole().equals("Client"))
        {
            modelName.addAttribute("title", tabTitle);
            return viewNameClient;
        }
        else if(client.getRole().equals("Admin"))
        {
            modelName.addAttribute("title", tabTitle);
            return viewNameAdmin;
        }
        else{
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        }
    }



}
