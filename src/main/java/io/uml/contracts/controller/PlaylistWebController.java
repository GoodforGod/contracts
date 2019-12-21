package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import io.uml.contracts.storage.impl.PlaylistStorage;
import io.uml.contracts.storage.impl.SongStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static io.uml.contracts.config.TemplateMapper.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.12.2019
 */
@Controller
public class PlaylistWebController extends BaseWebController{

    private final PlaylistStorage playlistStorage;
    private final SongStorage songStorage;

    @Autowired
    public PlaylistWebController(ClientStorage clientStorage,
                                 MercenaryStorage mercenaryStorage,
                                 PlaylistStorage playlistStorage,
                                 SongStorage songStorage) {
        super(clientStorage, mercenaryStorage);
        this.playlistStorage = playlistStorage;
        this.songStorage = songStorage;
    }

    @GetMapping(WebMapper.PLAYLIST_TABLE + "/{id}")
    public ModelAndView viewPlaylist(@PathVariable("id") String id) {
        final ModelAndView view = new ModelAndView(PAGE_CONTRACT_VIEW);
        final Contract contract = verifyContract(id);

        view.addObject("contract", contract);
        view.addObject("comments", contract.getComments());
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @DeleteMapping(WebMapper.PLAYLIST_TABLE + "/{id}")
    public String deletePlaylist(@PathVariable("id") String id) {

        return WebMapper.redirect(WebMapper.CONTRACT_TABLE);
    }

    @GetMapping(WebMapper.PLAYLIST_TABLE)
    public ModelAndView getPlaylists() {
        final ModelAndView view = new ModelAndView(PAGE_PLAYLIST_TABLE);
        view.addObject("playlists", contracts);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @GetMapping(WebMapper.PLAYLIST_ADD)
    public ModelAndView getContractAdd() {
        return new ModelAndView(PAGE_PLAYLIST_ADD);
    }

    @PostMapping(WebMapper.PLAYLIST_ADD + "/{id}")
    public String addSong(@PathVariable("id") String contractId,
                                  @RequestParam("comment") String comment) {

        return WebMapper.redirect(WebMapper.CONTRACT_TABLE + "/" + contractId);
    }

    @PostMapping(WebMapper.PLAYLIST_ADD)
    public String createContract(@RequestParam("type") Contract.ContractType type,
                                 @RequestParam("planet") String planet,
                                 @RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("reward") String reward,
                                 @RequestParam("requirements") String requirements) {

        return WebMapper.redirect(WebMapper.PLAYLIST_VIEW);
    }
}
