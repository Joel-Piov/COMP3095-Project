/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Controller that handles the logout method that invalidates the session
 * and redirects to the login.
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;

import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionAttributes("client")
@Controller
public class LogoutController {

    @Autowired
    ClientService clientRepo;

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession sessionName){
        HttpSession session = request.getSession();

            Client currentUser = (Client) sessionName.getAttribute("client");
            currentUser.setLastLoginDate(java.time.LocalDate.now());
            clientRepo.save(currentUser);

            session.invalidate();
            return "redirect:/";
    }
}
