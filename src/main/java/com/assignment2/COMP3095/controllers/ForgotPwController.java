/*********************************************************************************
 * Project: COMP3095 - Assignment 2
 * Assignment: Assignment 2
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 07/11/2020
 * Description: Controller that handles the form if a user has forgotten their password. User inputs an email that is
 * checked against the database for a matching entry. On success, a recovery email will be sent.
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;

import com.assignment2.COMP3095.models.User;
import com.assignment2.COMP3095.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForgotPwController {

    @Autowired
    ClientService repo;

    @RequestMapping("/login/forgotPassword")
    public String retrievePw(User user, Model model)
    {
        model.addAttribute("user", user);
        return "shared/forgotPassword";
    }

    @RequestMapping(value = "/login/forgotPassword", method = RequestMethod.POST)
    public String forgotPw(User user, Model model)
    {
        if(repo.findByEmail(user.getUsername()) == null)
        {
            model.addAttribute("pwResetFail", true);
            return "shared/forgotPassword";
        }
        else
        {
            model.addAttribute("pwResetSuccess", true);
            return "shared/forgotPassword";
        }
    }
}
