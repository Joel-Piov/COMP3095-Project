package com.assignment2.COMP3095.controllers;


import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Profile;
import com.assignment2.COMP3095.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes("client")
@Controller
public class ListingController {

    @Autowired
    ClientService clientRepo;

    @RequestMapping({"/dashboard/user-listings/remove/{id}"})
    public String removeUser(
            @PathVariable int id,
            RedirectAttributes redirectAttr,
            Model model,
            HttpSession session) {
        if(clientRepo.findByRole("Admin").size() > 1){
            clientRepo.delete(id);
        }
        return "redirect:/dashboard/user-listings";
    }
}
