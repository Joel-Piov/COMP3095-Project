package com.assignment2.COMP3095.controllers;


import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Profile;
import com.assignment2.COMP3095.services.ProfileService;
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
public class ProfileController {
    @Autowired
    ProfileService repo;

    @RequestMapping(value="/dashboard/profile/add", method= RequestMethod.POST)
    public String addProfile(@Valid @ModelAttribute("profile")Profile profile,
                             BindingResult br,
                             HttpSession sessionName,
                             RedirectAttributes redirectAttr,
                             Model model
                             ) {
        Client client = (Client) sessionName.getAttribute("client");
        profile.setClientId(client.getId());
//        profile.setClientFirstName(client.getFirstName());
//        profile.setClientLastName(client.getLastName());
//        profile.setEmail(client.getEmail());
//        profile.setAddress(client.getAddress());
//        profile.setCity(client.getCity());
//        profile.setCountry(client.getCountry());
//        profile.setPostalCode(client.getPostalCode());


        if(br.hasErrors()) {
            return "redirect:/profile";
        }
        else{
            if(repo.findByPostalCode(profile.getPostalCode()) != null){
                Profile existingProfile = repo.findByPostalCode(profile.getPostalCode());
                profile.setId(existingProfile.getId());
                repo.save(profile);
                return "redirect:/dashboard/profile";
            }
            else{
                repo.save(profile);
                return "redirect:/dashboard/profile";
            }
        }
    }
}
