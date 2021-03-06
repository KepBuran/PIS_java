package pis.lab2.manager;

import java.util.ResourceBundle;

public class Message {
    private static Message instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "messages";
    public static final String SERVLET_EXCEPTION = "SERVLET_EXCEPTION";
    public static final String IO_EXCEPTION = "IO_EXCEPTION";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String EMPTY_PACKNAME = "EMPTY_PACKNAME";
    public static final String PACKNAME_ALREADY_EXISTS = "PACKNAME_ALREADY_EXISTS";

    public static final String EMPTY_QUESTION = "EMPTY_QUESTION";
    public static final String EMPTY_ANSWER = "EMPTY_ANSWER";

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
