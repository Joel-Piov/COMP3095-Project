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
    public String registerUser(@Valid @ModelAttribute("newAdmin") Client newAdmin, Profile newProfile, BindingResult br,
                               @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
                               RedirectAttributes redirectAttr,
                               Model model)
    {
        if(newAdmin.getPassword().equals(confirmPassword)) {
            if (br.hasErrors()) {
                return "/dashboard/user-listings";
            } else {
                //checks if user already exists by email
                if (clientRepo.findByEmail(newAdmin.getEmail()) != null) {
                    return "/dashboard/user-listings";
                } else {
                    newAdmin.setRole("Admin");
                    newAdmin.setRegisterDate(java.time.LocalDate.now());
                    clientRepo.save(newAdmin);
                    newProfile.setClientId(newAdmin.getId());
                    newProfile.setClientFirstName(newAdmin.getFirstName());
                    newProfile.setClientLastName(newAdmin.getLastName());
                    newProfile.setEmail(newAdmin.getEmail());
                    newProfile.setAddress(newAdmin.getAddress());
                    newProfile.setCity(newAdmin.getCity());
                    newProfile.setCountry(newAdmin.getCountry());
                    newProfile.setPostalCode(newAdmin.getPostalCode());
                    newProfile.setPrefBilling(true);
                    newProfile.setPrefShipping(true);
                    newProfile.setClientDateOfBirth(newAdmin.getDateOfBirth());
                    profileRepo.save(newProfile);
                    redirectAttr.addFlashAttribute("registerSuccess", true);
                    return "redirect:/dashboard/user-listings/add";
                }
            }
        } else {
//            model.addAttribute("InvalidConfirm", true);
            return "/dashboard/user-listings/";
        }
    }
}
