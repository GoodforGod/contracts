package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.Client;
import io.uml.contracts.storage.impl.ClientStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static io.uml.contracts.config.TemplateMapper.PAGE_LOGIN;
import static io.uml.contracts.config.TemplateMapper.PAGE_REGISTER;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 14.06.2019
 */
@Controller
public class RegisterWebController {

    private final ClientStorage clientStorage;

    @Autowired
    public RegisterWebController(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    @GetMapping(WebMapper.REGISTER)
    public ModelAndView getPage() {
        return new ModelAndView(PAGE_REGISTER);
    }

    @GetMapping(WebMapper.LOGIN)
    public ModelAndView getLoginPage() {
        return new ModelAndView(PAGE_LOGIN);
    }

    @PostMapping(WebMapper.REGISTER)
    public ModelAndView register(@RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam(value = "planet", required = false) String planet,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password) {
        final ModelAndView view = new ModelAndView(PAGE_LOGIN);
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setPlanet(planet);
        client.setEmail(email);
        client.setPassword(password);
        clientStorage.save(client);
        return view;
    }
}
