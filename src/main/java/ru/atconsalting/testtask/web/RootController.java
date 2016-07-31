package ru.atconsalting.testtask.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
@Controller
public class RootController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList() {
        return "userList";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String mealList() {
        return "bookList";
    }
}
