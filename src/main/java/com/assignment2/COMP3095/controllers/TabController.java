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
import com.assignment2.COMP3095.models.Profile;
import com.assignment2.COMP3095.models.Support;
import com.assignment2.COMP3095.repo.SupportRepo;
import com.assignment2.COMP3095.services.CardService;
import com.assignment2.COMP3095.services.ClientService;
import com.assignment2.COMP3095.services.ProfileService;
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
    @Autowired
    ProfileService profileRepo;
    @Autowired
    SupportRepo supportRepo;
    @Autowired
    ClientService clientRepo;
    String redirectUrl = "redirect:/login";

    //======================================SHARED TABS==============================================================//

    //Profile Tab ---------> Checks to see if the user is a Client or Admin and provides the correct profile page
    @RequestMapping({"dashboard/profile", "/dashboard/profile/add", "/dashboard/profile/remove/{id}"})
    public String profile(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session,
            Profile profile
    ) {
        return checkSharedAccess(redirectAttr, "client/profile", "admin/profile-admin", model, "Profile", session, profile);
    }

    //Inbox Tab
    @RequestMapping(value = "dashboard/inbox", method = RequestMethod.GET)
    public String inbox(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session,
            Profile profile
    ) {
        return checkSharedAccess(redirectAttr, "client/inbox", "admin/inbox-admin", model, "Inbox", session, profile);
    }

    //======================================CLIENT TABS==============================================================//

    //Credit Info Tab
    @RequestMapping({"dashboard/credit", "/dashboard/credit/add", "/dashboard/credit/remove/{id}"})
    public String credit(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session,
            Card card,
            Profile profile,
            Support support
    ) {
        return checkClientAccess(redirectAttr, "client/credit-profile", model, "Credit Profile", session, profile, card, support);
    }

    //Support Tab
    @RequestMapping({"dashboard/support", "dashboard/support/sendMessage"})
    public String support(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session,
            Support support,
            Profile profile,
            Card card
    ) {
        return checkClientAccess(redirectAttr, "client/support", model, "Support", session, profile, card, support);
    }

    //======================================ADMIN TABS==============================================================//

    //User Listings Tab
    @RequestMapping(value = "dashboard/user-listings")
    public String userListing(
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session
    ) {
        return checkAdminAccess(redirectAttr, "admin/userListing-admin", model, "Clients", session);
    }

    //======================================CHECK FUNCTIONS=========================================================//

    //function that checks access privileges and reroutes
    private String checkClientAccess(
            RedirectAttributes redAtt,
            String viewName,
            Model modelName,
            String tabTitle,
            HttpSession sessionName,
            Profile profile,
            Card card,
            Support support
    ) {
        Client client = (Client) sessionName.getAttribute("client");

        if (client == null) {
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        } else if (client.getRole().equals("Client")) {
            int clientId = client.getId();
            List<Profile> clientProfileList = profileRepo.findProfileByClientId(clientId);
            List<Card> clientCardList = cardRepo.findByClientId(clientId);
            List<Support> supportMessageList = supportRepo.findMessagesByClientId(clientId);
            modelName.addAttribute("clientProfiles", clientProfileList);
            modelName.addAttribute("clientCards", clientCardList);
            modelName.addAttribute("messages", supportMessageList);

            modelName.addAttribute("title", tabTitle);
            modelName.addAttribute("profile", profile);
            modelName.addAttribute("card", card);
            modelName.addAttribute("support", support);

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
            List<Client> clientList = clientRepo.findByRole("Client");
            List<Client> adminList =  clientRepo.findByRole("Admin");

            modelName.addAttribute("title", tabTitle);
            modelName.addAttribute("clientList", clientList);
            modelName.addAttribute("adminList", adminList);

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
            HttpSession sessionName,
            Profile profile
    ) {
        Client client = (Client) sessionName.getAttribute("client");

        if (client == null) {
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        } else if (client.getRole().equals("Client")) {
            List<Profile> clientProfileList = profileRepo.findProfileByClientId(client.getId());
            List<Support> supportMessageList = supportRepo.findMessagesByClientId(client.getId());

            modelName.addAttribute("clientProfiles", clientProfileList);
            modelName.addAttribute("messages", supportMessageList);
            modelName.addAttribute("title", tabTitle);
            modelName.addAttribute("profile", profile);

            return viewNameClient;
        } else if (client.getRole().equals("Admin")) {
            modelName.addAttribute("title", tabTitle);
            Profile adminProfile = profileRepo.findByClientId(client.getId());
            List<Support> supportMessageList = supportRepo.findMessagesByAdminId(client.getId());
            modelName.addAttribute("adminProfile", adminProfile);
            modelName.addAttribute("messages", supportMessageList);
            modelName.addAttribute("profile", profile);
            return viewNameAdmin;
        } else {
            redAtt.addFlashAttribute("loginRequired", true);
            return redirectUrl;
        }
    }
}
