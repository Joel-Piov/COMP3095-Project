package com.assignment2.COMP3095.controllers;

import com.assignment2.COMP3095.models.Card;
import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Support;
import com.assignment2.COMP3095.repo.SupportRepo;
import com.assignment2.COMP3095.services.ClientService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SessionAttributes("client")
@Controller
public class SupportController {

    @Autowired
    private SupportRepo repo;

    @Autowired
    private ClientService clientRepo;


    @RequestMapping(value = "/dashboard/support/sendMessage", method = RequestMethod.POST)
    public String sendMessage(@Valid @ModelAttribute("support") Support support,
                          BindingResult br,
                          HttpSession sessionName,
                          RedirectAttributes redirectAttr,
                          Model model) {
        Client client = (Client) sessionName.getAttribute("client");
        support.setClientId(client.getId());
        support.setAdminId(assignSupportToAdmin());
        support.setCaseCode(String.valueOf(support.getAdminId()) + makeCaseCode());
        if (br.hasErrors()) {
            return "redirect:/dashboard/profile";
        } else {
            repo.save(support);
        }
        return "redirect:/dashboard/support";

    }

    //Method to assign support case to the admin with the least amount of cases
    private int assignSupportToAdmin(){
        List<Client> adminList = clientRepo.findByRole("Admin");
        Client admin = null;
        int messageCount = 0;
        for (int i = 0; i < adminList.size(); i++) {
            if(admin == null) {
                admin = adminList.get(i);
                messageCount = repo.findMessagesByAdminId(admin.getId()).size();
            } else {
                Client tempAdmin = adminList.get(i);
                int tempCount = repo.findMessagesByAdminId(tempAdmin.getId()).size();
                if(tempCount < messageCount){
                    admin = tempAdmin;
                }
            }
        }
        return admin.getId();
    }

    private String makeCaseCode(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("mm-yy-hh-mm-ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
