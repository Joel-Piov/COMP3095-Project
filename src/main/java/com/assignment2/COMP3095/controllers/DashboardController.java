/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Controller that handles the dashboard view and redirecting to login
 * if access is denied
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;
import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Profile;
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
public class DashboardController {

    @Autowired
    ProfileService profileRepo;

    @Autowired
    ClientService clientRepo;

    String redirectUrl = "redirect:/login";

    @RequestMapping("/dashboard")
    public String dashboardConfig(
            RedirectAttributes redirectAttr,
            HttpSession session,
            Model model
    ){
        return loginError(redirectAttr, redirectUrl, session, model);
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String dashboardPost(
            RedirectAttributes redirectAttr,
            HttpSession session,
            Model model
    ){
        return loginError(redirectAttr, redirectUrl, session, model);
    }

    private String loginError(
            RedirectAttributes redAtt,
            String url,
            HttpSession sessionName,
            Model model
    ){
        Client client = (Client) sessionName.getAttribute("client");


        if(client == null){
            redAtt.addFlashAttribute("loginRequired", true);
            return url;
        }
        else if(client.getRole().equals("Client") || client.getRole().equals("Admin")){
            Profile billingPref = profileRepo.findBillingPrefByClientId(client.getId());
            Profile shippingPref = profileRepo.findShippingPrefByClientId(client.getId());

            model.addAttribute("clientBillPref", billingPref);
            model.addAttribute("clientShipPref", shippingPref);

            if(client.getRole().equals("Client")) {
                return "client/dashboard";
            }
            else {
                List<Client> clientList = clientRepo.findByRole("Client");
                model.addAttribute("clientList", clientList);

                return "admin/dashboard-admin";
            }
        }
        else{
            redAtt.addFlashAttribute("loginRequired", true);
            return url;
        }
    }
}