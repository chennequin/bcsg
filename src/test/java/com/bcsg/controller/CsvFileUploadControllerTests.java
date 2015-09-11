package com.bcsg.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by christophe on 11/09/2015.
 */
@WebAppConfiguration
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class CsvFileUploadControllerTests {

    @Autowired
    protected WebApplicationContext context;

    @Test
    public void readCsv() throws Exception {

        final MockMultipartFile file = new MockMultipartFile("file", "original", null, "Royal Bank of Canada;4519-4532-4524-2456;10/2017".getBytes());

        webAppContextSetup(context).build()
                .perform(fileUpload("/form/fileUploadCsv").file(file))
                .andExpect(flash().attribute("message", "File 'original' uploaded successfully"));
    }
}
