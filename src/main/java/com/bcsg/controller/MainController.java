package com.bcsg.controller;

import com.bcsg.controller.form.AddCardFormBean;
import com.bcsg.service.CardService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.inject.Inject;

/**
 * This is the main controller of the webApp.
 */
@Controller
@SessionAttributes("addCardFormBean")
public class MainController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);

    @Inject
    private CardService service;

    @ModelAttribute("addCardFormBean")
    public AddCardFormBean createCardFormBean() {
        return new AddCardFormBean();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "redirect:/cards";
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public String showCards(final Model model) {

        model.addAttribute("cards", service.getAllCards());

        return "index";
    }

}
