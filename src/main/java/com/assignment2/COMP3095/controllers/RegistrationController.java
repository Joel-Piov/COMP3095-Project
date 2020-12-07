/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Controller that handles the registration page form, creating a new client entry in the database
 * if all fields have been filled, and their corresponding profile.
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;
import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Profile;
import com.assignment2.COMP3095.services.ClientService;
import com.assignment2.COMP3095.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    ClientService repo;
    @Autowired
    ProfileService profileRepo;

    @RequestMapping({"/register"})
    public String register(Model model){
        Client client = new Client();
        model.addAttribute("client", client);
        return "client/register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("client") Client client, Profile profile, BindingResult br,
                               @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
                               RedirectAttributes redirectAttr,
                               Model model)
    {
        if(client.getPassword().equals(confirmPassword)) {
            if (br.hasErrors()) {
                return "client/register";
            } else {
                if (repo.findByEmail(client.getEmail()) != null) {
                    return "client/register";
                } else {
                    client.setRole("Client");
                    client.setRegisterDate(java.time.LocalDate.now());
                    repo.save(client);
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
                    redirectAttr.addFlashAttribute("registerSuccess", true);
                    return "redirect:/";
                }
            }
        } else {
            model.addAttribute("InvalidConfirm", true);
            return "client/register";
        }
    }

}
