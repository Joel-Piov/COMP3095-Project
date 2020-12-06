package com.assignment2.COMP3095.controllers;

import com.assignment2.COMP3095.models.Card;
import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.repo.CardRepo;
import com.assignment2.COMP3095.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@SessionAttributes("client")
@Controller
public class CardController {

    @Autowired
    CardService repo;

    @RequestMapping(value = "/dashboard/credit/add", method = RequestMethod.POST)
    public String addCard(@Valid @ModelAttribute("card") Card card,
                          BindingResult br,
                          HttpSession sessionName,
                          RedirectAttributes redirectAttr,
                          Model model) {
        Client client = (Client) sessionName.getAttribute("client");
        card.setClientId((int) client.getId());
        if (br.hasErrors()) {
            return "redirect:/dashboard/credit";
        } else {
            if (repo.findByCardNumber(card.getCardNumber()) != null) {
                Card existingCard = repo.findByCardNumber(card.getCardNumber());
                card.setId(existingCard.getId());
                repo.save(card);
                return "redirect:/dashboard/credit";
            } else {
                repo.save(card);
                //redirectAttr.addFlashAttribute("registerSuccess", true);
                return "redirect:/dashboard/credit";
            }
        }
    }

}
