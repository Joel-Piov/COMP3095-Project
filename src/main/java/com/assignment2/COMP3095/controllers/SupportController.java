package com.assignment2.COMP3095.controllers;

import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Support;
import com.assignment2.COMP3095.repo.SupportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@SessionAttributes("client")
@Controller
public class SupportController {

    @Autowired
    private SupportRepo repo;

    @RequestMapping(value = "/dashboard/support/sendMessage", method = RequestMethod.POST)
    public String sendMessage(@Valid @ModelAttribute("support") Support support,
                          BindingResult br,
                          HttpSession sessionName,
                          RedirectAttributes redirectAttr,
                          Model model) {
        Client client = (Client) sessionName.getAttribute("client");
        support.setClientId(client.getId());

        //NEED TO CHANGE TO ADMIN TO FIND ADMIN wHO HAS SMALLEST QUEUE
        support.setAdminId(client.getId());

        if (br.hasErrors()) {
            return "redirect:/dashboard/profile";
        } else {
            repo.save(support);
        }
        return "redirect:/dashboard/support";


    }
}
