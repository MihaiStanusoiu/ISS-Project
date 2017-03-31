package view;

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
            return "Confery";
        }

        @Override
        public String getFXMLFile() {
            return "/fxml/ApplicationFrame.fxml";
        }
    }
}
