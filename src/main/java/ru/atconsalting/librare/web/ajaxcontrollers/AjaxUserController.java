package ru.atconsalting.librare.web.ajaxcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.atconsalting.librare.model.User;
import ru.atconsalting.librare.service.UserService;
import ru.atconsalting.librare.to.UserTo;
import ru.atconsalting.librare.util.userUtil.UserUtil;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
@RestController
@RequestMapping(value = "/ajax/users")
public class AjaxUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AjaxUserController.class);
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserTo> getAllUsers(String userName) {
        LOGGER.info("Get All Users");
        return service.getAllUsers().stream()
                .map(UserUtil::asTo)
                .collect(toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserTo getUser(@PathVariable("id") Long id) {
        LOGGER.info("Get user with id" + id);
        return UserUtil.asTo(service.get(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
        LOGGER.info("delete user with id" + id);
        service.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(UserTo userTo) {
        LOGGER.info("create or update user with id" + userTo);
        try {
            if (userTo.getId() == null || userTo.getId() == 0) {
                service.saveUser(UserUtil.createFromTo(userTo));
            } else {
                service.updateUser(UserUtil.updateFromTo(new User(), userTo));
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("exception.duplicate_userName");
        }
    }

}
