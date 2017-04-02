package view;

import java.util.ResourceBundle;

/**
 * Name:        ViewType
 * Effect:      Defines the views and their fxml paths.
 * Date:        31/03/2017
 * Tested:      False
 * @author      Alexandru Stoica
 * @version     1.0
 */

public enum ViewType implements ViewTypeInterface {

    FRAME {

        /** Application's Root Frame */

        @Override
        public String getTitle() {
            return ViewType.getDataFromBundle("application.title");
        }

        @Override
        public String getFXMLFile() {
            return "/fxml/ApplicationFrame.fxml";
        }
    };

    /**
     * Effect: Returns data from application properties.
     * @param key: The data's key in properties file.
     * @return String value from application.properties external files.
     */
    private static String getDataFromBundle(final String key) {
        return ResourceBundle.getBundle("application").getString(key);
    }
}
