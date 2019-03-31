package by.voinilo.localization;

import java.util.Locale;
import java.util.ResourceBundle;


public enum Res {

    INSTANCE;

    private ResourceBundle bundle;

     Res() {
        setLocale(Locale.getDefault());
    }

   void setLocale(Locale locale) {
       //путь к пропертям относительно src
        bundle = ResourceBundle.getBundle("by.voinilo.localization.Strings", locale);
    }

    String get(String key) {
        return bundle.getString(key);
    }
}
