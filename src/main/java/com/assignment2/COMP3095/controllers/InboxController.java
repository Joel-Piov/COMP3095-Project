/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: The controller that shows the inbox and sends the mail for both Admin and Client.
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;


import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Support;
import com.assignment2.COMP3095.services.ClientService;
import com.assignment2.COMP3095.services.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@SessionAttributes("client")
@Controller
public class InboxController {

    @Autowired
    SupportService repo;

    @Autowired
    ClientService clientRepo;

    @RequestMapping(value = "/dashboard/inbox/remove/{id}", method = RequestMethod.GET)
    public String deleteMessageProcess(@PathVariable int id,
                                       HttpSession sessionName,
                                       RedirectAttributes redirectAttr,
                                       Model model) {
        Client client = (Client) sessionName.getAttribute("client");
        Support message = repo.get(id);
        if(message.getAdminId() == 0 || message.getClientId() == 0){
            //When both inboxs delete the message it will be wipe from the database
            repo.delete(id);
        } else {
            if(client.getRole().equals("Admin")){
                message.setAdminId(0);
            } else {
                message.setClientId(0);
            }
            repo.save(message);
        }
        return "redirect:/dashboard/inbox";
    }

    @RequestMapping(value = "/dashboard/inbox/view/{id}", method = RequestMethod.GET)
    public String viewMessage(@PathVariable int id,
                                       HttpSession sessionName,
                                       RedirectAttributes redirectAttr,
                                       Model model) {
        Support message = repo.get(id);
        redirectAttr.addFlashAttribute("viewMessage", message);
        return "redirect:/dashboard/inbox";
    }


}
