package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.CleaningLog;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.Flight;
import io.uml.contracts.storage.impl.CleaningLogStorage;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static io.uml.contracts.config.TemplateMapper.*;

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
        view.addObject("cleans", all);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @GetMapping(WebMapper.CLEAN_ADD)
    public ModelAndView addPage() {
        return new ModelAndView(PAGE_CLEAN_ADD);
    }

    @PostMapping(WebMapper.CLEAN_ADD)
    public String add(@RequestParam("type") Contract.ContractType type,
                      @RequestParam("planet") String planet,
                      @RequestParam("title") String title,
                      @RequestParam("description") String description,
                      @RequestParam("reward") String reward,
                      @RequestParam("comment") String comment) {
        final CleaningLog log = new CleaningLog();

        cleaningLogStorage.save(log);

        return WebMapper.redirect(WebMapper.CLEAN_TABLE);
    }
}
