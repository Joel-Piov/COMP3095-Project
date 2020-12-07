package com.assignment2.COMP3095.controllers;


import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Profile;
import com.assignment2.COMP3095.services.ClientService;
import com.assignment2.COMP3095.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@SessionAttributes("client")
@Controller
public class ListingController {

    @Autowired
    ClientService clientRepo;
    @Autowired
    ProfileService profileRepo;

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

    @RequestMapping(value="/dashboard/user-listings/add", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("newAdmin") Client client, Profile profile, BindingResult br,
                               @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
                               RedirectAttributes redirectAttr,
                               Model model)
    {
        if(client.getPassword().equals(confirmPassword)) {
            if (br.hasErrors()) {
                return "/dashboard/user-listings";
            } else {
                //checks if user already exists by email
                if (clientRepo.findByEmail(client.getEmail()) != null) {
                    return "/dashboard/user-listings";
                } else {
                    client.setRole("Admin");
                    client.setRegisterDate(java.time.LocalDate.now());
                    clientRepo.save(client);
                    profile.setClientId(client.getId());
                    profile.setClientFirstName(client.getFirstName());
                    profile.setClientLastName(client.getLastName());
                    profile.setEmail(client.getEmail());
                    profile.setAddress(client.getAddress());
                    profile.setCity(client.getCity());
                    profile.setCountry(client.getCountry());
                    profile.setPostalCode(client.getPostalCode());
                    profile.setPrefBilling(true);
                    profile.setPrefShipping(true);
                    profile.setClientDateOfBirth(client.getDateOfBirth());
                    profileRepo.save(profile);
//                    redirectAttr.addFlashAttribute("registerSuccess", true);
                    return "redirect:/dashboard/user-listings/add";
                }
            }
        } else {
//            model.addAttribute("InvalidConfirm", true);
            return "/dashboard/user-listings/";
        }
    }
}
