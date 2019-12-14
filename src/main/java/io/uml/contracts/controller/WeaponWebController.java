package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.WeaponLog;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import io.uml.contracts.storage.impl.WeaponLogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static io.uml.contracts.config.TemplateMapper.PAGE_WEAPON_ADD;
import static io.uml.contracts.config.TemplateMapper.PAGE_WEAPON_TABLE;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 14.12.2019
 */
@Controller
public class WeaponWebController extends BaseWebController {

    private final WeaponLogStorage weaponLogStorage;

    @Autowired
    public WeaponWebController(ClientStorage clientStorage,
                               MercenaryStorage mercenaryStorage,
                               WeaponLogStorage weaponLogStorage) {
        super(clientStorage, mercenaryStorage);
        this.weaponLogStorage = weaponLogStorage;
    }

    @GetMapping(WebMapper.WEAPON_TABLE)
    public ModelAndView table() {
        final ModelAndView view = new ModelAndView(PAGE_WEAPON_TABLE);
        final List<WeaponLog> all = weaponLogStorage.findAll();
        view.addObject("weapons", all);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @GetMapping(WebMapper.WEAPON_ADD)
    public ModelAndView addPage() {
        return new ModelAndView(PAGE_WEAPON_ADD);
    }

    @PostMapping(WebMapper.WEAPON_ADD)
    public String add(@RequestParam("type") Contract.ContractType type,
                      @RequestParam("planet") String planet,
                      @RequestParam("title") String title,
                      @RequestParam("description") String description,
                      @RequestParam("reward") String reward,
                      @RequestParam("comment") String comment) {
        final WeaponLog log = new WeaponLog();

        weaponLogStorage.save(log);

        return WebMapper.redirect(WebMapper.WEAPON_TABLE);
    }
}
