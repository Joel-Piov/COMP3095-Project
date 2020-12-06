/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 04/12/2020
 * Description: Controller that handles adding/updating cards. Cards submitted with an existing card number will updated.
 *********************************************************************************/

package com.assignment2.COMP3095.controllers;

import com.assignment2.COMP3095.models.Card;
import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.repo.CardRepo;
import com.assignment2.COMP3095.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@SessionAttributes("client")
@Controller
public class CardController {

    @Autowired
    CardService repo;

    @RequestMapping(value = "/dashboard/credit/remove/{id}",method = RequestMethod.GET)
        public String deleteCard(@PathVariable int id) {
            repo.delete(id);
            return "redirect:/dashboard/credit";
        }

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
            if(card.getPrefCard()){
                //If card is new pref card set all to false
                setPreferredCard(client.getId());
            }
            if (repo.findByCardNumber(card.getCardNumber()) != null) {
                //if card number exists, update the card
                Card existingCard = repo.findByCardNumber(card.getCardNumber());
                card.setId(existingCard.getId());
            }
            repo.save(card);
            return "redirect:/dashboard/credit";
        }
    }

    private void setPreferredCard(int clientId){
        List<Card> CardList = repo.findByClientId(clientId);
        for(int i = 0; i < CardList.size(); i++){
            Card tempCard = CardList.get(i);
            tempCard.setPrefCard(false);
            repo.save(tempCard);
        }
    }


}
