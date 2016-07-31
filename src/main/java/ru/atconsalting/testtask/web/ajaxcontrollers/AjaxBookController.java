package ru.atconsalting.testtask.web.ajaxcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.atconsalting.testtask.service.BookService;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
@RestController
public class AjaxBookController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AjaxBookController.class);
    @Autowired
    private BookService service;
}
