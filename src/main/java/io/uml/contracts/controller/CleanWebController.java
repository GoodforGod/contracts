package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.CleaningLog;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.storage.impl.CleaningLogStorage;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static io.uml.contracts.config.TemplateMapper.PAGE_CLEAN_ADD;
import static io.uml.contracts.config.TemplateMapper.PAGE_CLEAN_TABLE;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 14.12.2019
 */
@Controller
public class CleanWebController extends BaseWebController {

    private final CleaningLogStorage cleaningLogStorage;

    @Autowired
    public CleanWebController(ClientStorage clientStorage,
                              MercenaryStorage mercenaryStorage,
                              CleaningLogStorage cleaningLogStorage) {
        super(clientStorage, mercenaryStorage);
        this.cleaningLogStorage = cleaningLogStorage;
    }

    @GetMapping(WebMapper.CLEAN_TABLE)
    public ModelAndView table() {
        final ModelAndView view = new ModelAndView(PAGE_CLEAN_TABLE);
        final List<CleaningLog> all = cleaningLogStorage.findAll();
        all.sort(Comparator.comparing(CleaningLog::getCleanDate).reversed());
        view.addObject("cleans", all);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @DeleteMapping(WebMapper.CLEAN_TABLE + "/{id}")
    public String delete(@PathVariable("id") String id) {
        cleaningLogStorage.find(id)
                .filter(l -> !l.isPast())
                .ifPresent(l -> {
                    l.setResponsible(null);
                    cleaningLogStorage.save(l);
                    cleaningLogStorage.delete(l.getId());
                });

        return WebMapper.redirect(WebMapper.CLEAN_TABLE);
    }

    @GetMapping(WebMapper.CLEAN_ADD)
    public ModelAndView addPage() {
        return new ModelAndView(PAGE_CLEAN_ADD);
    }

    @PostMapping(WebMapper.CLEAN_ADD)
    public String add(@RequestParam("date") String date,
                      @RequestParam("description") String description) {
        final CleaningLog log = new CleaningLog();
        log.setCleanDate(Timestamp.valueOf(LocalDate.parse(date).atStartOfDay()));
        if(log.isPast())
            return WebMapper.redirect(WebMapper.CLEAN_ADD + "?error=true");

        log.setDescription(description);
        log.setId(UUID.randomUUID().toString());
        final Mercenary mercenary = getMercenaryFromContext();
        log.setResponsible(mercenary);

        cleaningLogStorage.save(log);

        return WebMapper.redirect(WebMapper.CLEAN_TABLE);
    }
}
