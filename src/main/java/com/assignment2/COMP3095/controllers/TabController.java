/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 04/12/2020
 * Description: Controller that handles the routing and logic for the tabs for the Admin and Client users.
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;
import com.assignment2.COMP3095.models.Card;
import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes("client")
@Controller
public class TabController {

    @Autowired
    CardService cardRepo;

    String redirectUrl = "redirect:/login";

    //======================================SHARED TABS==============================================================//

    //Profile Tab ---------> Checks to see if the user is a Client or Admin and provides the correct profile page
    @RequestMapping(value="dashboard/profile", method = RequestMethod.GET )
    public String profile(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkSharedAccess(redirectAttr, "client/profile", "admin/profile-admin", model, "Profile", session);
    }

    //Inbox Tab
    @RequestMapping(value="dashboard/inbox", method = RequestMethod.GET )
    public String inbox(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkSharedAccess(redirectAttr, "client/inbox","admin/inbox-admin", model, "Inbox", session);
    }

    //======================================CLIENT TABS==============================================================//

    //Credit Info Tab
    @RequestMapping(value="dashboard/credit", method = RequestMethod.GET )
    public String credit(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session,
            Card card
    ){
        Client client = (Client) session.getAttribute("client");
        model.addAttribute("card", card);
        List<Card> clientCardList = cardRepo.findByClientId(client.getId());
        model.addAttribute("clientCards", clientCardList);
        return checkClientAccess(redirectAttr, "client/credit-profile", model, "Credit Profile", session);
    }

    //Support Tab
    @RequestMapping(value="dashboard/support", method = RequestMethod.GET)
    public String support(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkClientAccess(redirectAttr, "client/support", model, "Support", session);
    }

    //======================================ADMIN TABS==============================================================//

    //User Listings Tab
        @RequestMapping(value="dashboard/user-listings", method = RequestMethod.GET)
        public String userListing(
                RedirectAttributes redirectAttr,
                Model model,
                HttpSession session
        ){
            return checkAdminAccess(redirectAttr, "admin/userListing-admin", model, "Clients", session);
        }

    //Admin Listings Tab
    @RequestMapping(value="dashboard/admin-listings", method = RequestMethod.GET)
    public String adminListing(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ){
        return checkAdminAccess(redirectAttr, "admin/adminListing-admin", model, "Admins", session);
    }

    //======================================CHECK FUNCTIONS=========================================================//

    //function that checks access privileges and reroutes
    private String checkClientAccess(
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
        } else if (client.getRole().equals("Client")) {
            modelName.addAttribute("title", tabTitle);
            return viewName;
        } else {
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        }
    }

    //function that checks access privileges and reroutes
    private String checkAdminAccess(
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
        } else if (client.getRole().equals("Admin")) {
            modelName.addAttribute("title", tabTitle);
            return viewName;
        } else {
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        }
    }


    private String checkSharedAccess(
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
