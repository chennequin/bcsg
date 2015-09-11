package com.bcsg.controller;

import com.bcsg.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * This is the controller for adding cards using a CSV file.
 */
@Controller
@RequestMapping("/form/fileUploadCsv")
public class CsvFileUploadController {

    @Autowired
    private CardService service;

    @RequestMapping(method = RequestMethod.POST)
    public String processUploadCsv(@RequestParam MultipartFile file, RedirectAttributes redirectAttrs) throws IOException {

        service.addCardCsv(file);

        final String message = "File '" + file.getOriginalFilename() + "' uploaded successfully";

        // store a success message for rendering on the next request after redirect
        redirectAttrs.addFlashAttribute("message", message);

        return "redirect:/cards";
    }

}
