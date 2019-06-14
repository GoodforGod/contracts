package io.uml.contracts.util;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.09.2017
 */
public class WebMapper {
    public static final String SECURED = "/sec";

    public static final String CONTRACTS = SECURED + "/contracts";
    public static final String CONTRACT_ADD = SECURED + "/contracts/create";
    public static final String CONTRACT_VIEW = SECURED + "/contracts/view";

    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String JOURNAL = SECURED + "/journal";
    public static final String LOGOUT = SECURED + "/logout";

    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_DOC = "/swagger*";

    public static String redirect(String value) {
        return "redirect:" + value;
    }
}
