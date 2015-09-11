package com.bcsg.controller;

import com.bcsg.controller.form.AddCardFormBean;
import com.bcsg.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * This is the controller for adding cards manually.
 */
@Controller
@RequestMapping("/form/addCard")
@SessionAttributes("addCardFormBean")
public class AddCardFormController {

    @Autowired
    private CardService service;

    @ModelAttribute("addCardFormBean")
    public AddCardFormBean createCardFormBean() {
        return new AddCardFormBean();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@Valid AddCardFormBean formBean, BindingResult result, Model model, RedirectAttributes redirectAttrs) {

        model.addAttribute("cards", service.getAllCards());

        if (result.hasErrors()) {
            model.addAttribute("mainNodeCssClass", "addCard");
            return "index";
        }

        // add card to database
        service.addCard(formBean.getBank(), formBean.getNumber(), formBean.getExpiry());

        final String message = "Card successfully added.";

        // store a success message for rendering on the next request after redirect
        redirectAttrs.addFlashAttribute("message", message);

        return "redirect:/cards";
    }

}
