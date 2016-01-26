package com.mkyong.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkyong.web.model.User;

@Controller
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private static final ObjectMapper om = new ObjectMapper();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView printWelcome(HttpServletRequest request) {

        ModelAndView result = new ModelAndView();
        result.addObject("resources", request.getContextPath() + "/resources");
        result.addObject("logo", "mkyong.com");
        result.addObject("title", "Spring MVC + Mustache template");
        result.addObject("jumbo-title", "Spring MVC + Mustache template");
        result.addObject("jumbo-desc", "Maven + Spring MVC + Mustache JS, ScriptTemplate example.");

        //1. Test data type
        result.addObject("id", 100);
        result.addObject("username", "mkyong");

        //2. Test List
        result.addObject("scriptTemplates", getScriptTemplate());

        //3. Test Object
        result.addObject("user", new User("abc@gmail.com", 0));

        //4. Test List<Object>
        List<User> list = new ArrayList<>();
        list.add(new User("aaa@gmail.com", 1));
        list.add(new User("bbb@yahoo.com", 2));
        list.add(new User("ccc@hotmail.com", 3));
        result.addObject("users_json", convertObjectToJson(list));

        result.addObject("users", list);

        result.setViewName("hello");
        return result;

    }

    private List<String> getScriptTemplate() {

        List<String> scriptTemplates = new ArrayList<>();
        scriptTemplates.add("Handlebars");
        scriptTemplates.add("Mustache");
        scriptTemplates.add("React");
        scriptTemplates.add("EJS");
        scriptTemplates.add("ERB");
        scriptTemplates.add("String templates");
        return scriptTemplates;

    }

    //Jackson2 - Convert Java Object to JSON format
    public static String convertObjectToJson(Object obj) {
        String result = "";
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error In JSON conversion : {}", e);
        }
        return result;
    }

}