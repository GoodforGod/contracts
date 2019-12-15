package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.model.dao.Weapon;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import io.uml.contracts.storage.impl.WeaponStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    private final WeaponStorage weaponStorage;

    @Autowired
    public WeaponWebController(ClientStorage clientStorage,
                               MercenaryStorage mercenaryStorage,
                               WeaponStorage weaponStorage) {
        super(clientStorage, mercenaryStorage);
        this.weaponStorage = weaponStorage;
    }

    @GetMapping(WebMapper.WEAPON_TABLE)
    public ModelAndView table() {
        final ModelAndView view = new ModelAndView(PAGE_WEAPON_TABLE);
        final List<Weapon> all = weaponStorage.findAll();
        view.addObject("weapons", all);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @DeleteMapping(WebMapper.WEAPON_TABLE + "/{id}")
    public String delete(@PathVariable("id") String id) {
        weaponStorage.find(id).ifPresent(w -> {
                    w.setResponsible(null);
                    weaponStorage.save(w);
                    weaponStorage.delete(w.getId());
                });

        return WebMapper.redirect(WebMapper.WEAPON_TABLE);
    }

    @GetMapping(WebMapper.WEAPON_ADD)
    public ModelAndView addPage() {
        return new ModelAndView(PAGE_WEAPON_ADD);
    }

    @PostMapping(WebMapper.WEAPON_ADD)
    public String add(@RequestParam("type") Weapon.WeaponType type,
                      @RequestParam("name") String name,
                      @RequestParam("description") String description) {
        final Weapon weapon = new Weapon();
        final Mercenary mercenary = getMercenaryFromContext();
        weapon.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        weapon.setId(UUID.randomUUID().toString());
        weapon.setName(name);
        weapon.setDescription(description);
        weapon.setStatus(Weapon.WeaponStatus.GOOD);
        weapon.setType(type);
        weapon.setResponsible(mercenary);
        weaponStorage.save(weapon);

        return WebMapper.redirect(WebMapper.WEAPON_TABLE);
    }
}
