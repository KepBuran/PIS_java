package pis.lab2.manager;

import java.util.ResourceBundle;

public class Config {
    public static final String USERPACKS = "USERPACKS";
    public static final String QUESTION = "QUESTION";
    public static final String PACK = "PACK";
    public static final String CHANGEPACK = "CHANGEPACK";
    private static Config instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "config";
    public static final String MAIN = "MAIN";
    public static final String ALLPACKS = "ALLPACKS";
    public static final String INDEX = "INDEX";
    public static final String LOGIN = "LOGIN";

    public static final String REGISTERUSER = "REGISTERUSER";
    public static final String CREATEPACK = "CREATEPACK";
    public static final String CREATEQUESTION = "CREATEQUESTION";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
