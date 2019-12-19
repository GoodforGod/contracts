package io.uml.contracts.config;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.09.2017
 */
public class WebMapper {
    public static final String SECURED = "/sec";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";

    public static final String CONTRACT_TABLE = SECURED + "/contracts";
    public static final String CONTRACT_ADD = SECURED + "/contracts/create";
    public static final String CONTRACT_COMMENT = SECURED + "/contracts/comment";
    public static final String CONTRACT_VIEW = SECURED + "/contracts/view";

    public static final String MUSIC_TABLE = SECURED + "/music";
    public static final String MUSIC_ADD = SECURED + "/music/create";
    public static final String MUSIC_VIEW = SECURED + "/music/view";

    public static final String CLEAN_TABLE = SECURED + "/cleans";
    public static final String CLEAN_ADD = SECURED + "/cleans/create";

    public static final String FLIGHT_VIEW = SECURED + "/flights";
    public static final String FLIGHT_ADD = SECURED + "/flights/create";

    public static final String TACTIC_VIEW = SECURED + "/tactic";
    public static final String TACTIC_ADD = SECURED + "/tactic/create";
    public static final String TACTIC_COMMENT = SECURED + "/tactic/comment";

    public static final String WEAPON_TABLE = SECURED + "/weapons";
    public static final String WEAPON_ADD = SECURED + "/weapons/create";

    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_DOC = "/swagger*";

    public static String redirect(String value) {
        return "redirect:" + value;
    }
}
