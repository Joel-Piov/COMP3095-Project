package com.assignment2.COMP3095.controllers;

import com.assignment2.COMP3095.models.Card;
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
public class ProfileController {
    @Autowired
    ProfileService repo;
    @Autowired
    ClientService clientRepo;

    String redirectUrl = "redirect:/login";


    @RequestMapping(value = "/dashboard/profile/remove/{id}", method = RequestMethod.GET)
    public String deleteProfile(@PathVariable int id, HttpSession sessionName) {
        Client client = (Client) sessionName.getAttribute("client");
        List<Profile> ProfileList = repo.findProfileByClientId(client.getId());
        if (ProfileList.size() > 1) {
            repo.delete(id);
            return "redirect:/dashboard/profile";
        } else {
            //needs an error message
            return "redirect:/dashboard/profile";
        }
    }

    @RequestMapping(value = "/dashboard/profile/add", method = RequestMethod.POST)
    public String addProfile(@Valid @ModelAttribute("profile") Profile profile,
                             BindingResult br,
                             HttpSession sessionName,
                             RedirectAttributes redirectAttr,
                             Model model
    ) {
        Client client = (Client) sessionName.getAttribute("client");
        profile.setClientId(client.getId());

        if (client.getRole().equals("Client")) {
            if (br.hasErrors()) {
                System.out.println(br);
                return "redirect:/dashboard/profile";
            } else {
                if (profile.getPrefBilling() || profile.getPrefShipping()) {
                    //If new profile is new preference, set all others to false
                    if (profile.getPrefBilling() && !profile.getPrefShipping()) {
                        setPreferredBilling(client.getId());
                    } else if (profile.getPrefShipping() && !profile.getPrefBilling()) {
                        setPreferredShipping(client.getId());
                    } else if (profile.getPrefShipping() && profile.getPrefBilling()) {
                        setPreferredBilling(client.getId());
                        setPreferredShipping(client.getId());
                    }
                }
                //checks if there is a profile that belongs to the user with entered postal code
                if (repo.findByPostalCode(profile.getPostalCode()) != null) {
                    Profile existingProfile = repo.findByPostalCode(profile.getPostalCode());
                    profile.setId(existingProfile.getId());
                }
                //updates or saves new profile to profile table, updates current user information to match new profile
                repo.save(profile);

                client.setFirstName(profile.getClientFirstName());
                client.setLastName(profile.getClientLastName());
                client.setEmail(profile.getEmail());
                client.setAddress(profile.getAddress());
                client.setCity(profile.getCity());
                client.setCountry(profile.getCountry());
                client.setPostalCode(profile.getPostalCode());
                client.setDateOfBirth(profile.getClientDateOfBirth());

                clientRepo.save(client);

                //Sets all profiles to share the client's name and DOB update
                syncProfileNameAndDob(client.getId(), client);
            }
        } else {
            if (br.hasErrors()) {
                System.out.println(br);
                return "redirect:/dashboard/profile";
            } else {
                Profile adminProfile = repo.findByClientId(client.getId());
                profile.setId(adminProfile.getId());
                profile.setClientId(client.getId());
                //Save profile changes to Profiles Table
                repo.save(profile);

                //Update User Information in Clients Table
                client.setFirstName(profile.getClientFirstName());
                client.setLastName(profile.getClientLastName());
                client.setEmail(profile.getEmail());
                client.setAddress(profile.getAddress());
                client.setCity(profile.getCity());
                client.setCountry(profile.getCountry());
                client.setPostalCode(profile.getPostalCode());
                client.setDateOfBirth(profile.getClientDateOfBirth());

                clientRepo.save(client);

                //Sets all profiles to share the client's name and DOB update
                syncProfileNameAndDob(client.getId(), client);
            }
        }
        return "redirect:/dashboard/profile";

    }

    private void syncProfileNameAndDob(int clientId, Client currClient) {
        List<Profile> ProfileList = repo.findProfileByClientId(clientId);
        for (int i = 0; i < ProfileList.size(); i++) {
            Profile tempProfile = ProfileList.get(i);
            tempProfile.setClientFirstName(currClient.getFirstName());
            tempProfile.setClientLastName(currClient.getLastName());
            tempProfile.setClientDateOfBirth(currClient.getDateOfBirth());

            repo.save(tempProfile);
        }
    }

    private void setPreferredBilling(int clientId) {
        List<Profile> ProfileList = repo.findProfileByClientId(clientId);
        for (int i = 0; i < ProfileList.size(); i++) {
            Profile tempProfile = ProfileList.get(i);
            tempProfile.setPrefBilling(false);
            repo.save(tempProfile);
        }
    }

    private void setPreferredShipping(int clientId) {
        List<Profile> ProfileList = repo.findProfileByClientId(clientId);
        for (int i = 0; i < ProfileList.size(); i++) {
            Profile tempProfile = ProfileList.get(i);
            tempProfile.setPrefShipping(false);
            repo.save(tempProfile);
        }
    }
}
