package io.uml.contracts.controller;

import io.uml.contracts.model.dao.Client;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.util.WebControllerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static io.uml.contracts.util.TemplateMapper.LOGIN;
import static io.uml.contracts.util.TemplateMapper.REGISTER;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 14.06.2019
 */
@RestController
public class RegisterWebController {

    @Autowired
    private ClientStorage clientStorage;

    @GetMapping(WebControllerMapper.REGISTER)
    public ModelAndView getPage() {
        return new ModelAndView(REGISTER);
    }

    @GetMapping(WebControllerMapper.LOGIN)
    public ModelAndView getLoginPage() {
        return new ModelAndView(LOGIN);
    }

    @PostMapping(WebControllerMapper.REGISTER)
    public ModelAndView register(@RequestParam("name") String name,
                                 @RequestParam("name") String surname,
                                 @RequestParam(value = "name", required = false) String planet,
                                 @RequestParam("name") String email,
                                 @RequestParam("comment") String password) {
        final ModelAndView view = new ModelAndView(LOGIN);
        Client client = new Client(name, surname, planet);
        client.setEmail(email);
        client.setPassword(password);
        clientStorage.save(client);
        return view;
    }
}
