package ru.atconsalting.testtask.web.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.atconsalting.testtask.LoggedUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
public class ModelInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && !modelAndView.isEmpty()) {
            LoggedUser loggedUser = LoggedUser.safeGet();
            if (loggedUser != null) {
                modelAndView.getModelMap().addAttribute("userTo", loggedUser.getUserTo());
            }
        }
    }
}