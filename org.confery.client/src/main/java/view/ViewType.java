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

    CONFERENCES {

        /** Conferences View */
        @Override
        public String getFXMLFile() {
            return "/fxml/ConferencesView.fxml";
        }

    },

    MY_CONFERENCES {

        /** My Conferences View */
        @Override
        public String getFXMLFile() {
            return "/fxml/MyConferencesView.fxml";
        }

    },

    CONFERENCE {

        /** ConferenceEntity View */
        @Override
        public String getFXMLFile() {
            return "/fxml/ConferenceView.fxml";
        }

    },

    USER {

        /** UserEntity View */
        @Override
        public String getFXMLFile() {
            return "/fxml/UserView.fxml";
        }

    },

    CONFERENCE_ITEM {

        /** ConferenceEntity Item View */
        @Override
        public String getFXMLFile() {
            return "/fxml/ConferenceItemView.fxml";
        }

    },

    USER_ITEM {

        /** UserEntity Item View */
        @Override
        public String getFXMLFile() {
            return "/fxml/UserItemView.fxml";
        }

    },

    LOGIN {

        /** Login View */
        @Override
        public String getFXMLFile() {
            return "/fxml/LoginView.fxml";
        }

    },

    SIGN_UP {

        /** Sign Up View */
        @Override
        public String getFXMLFile() {
            return "/fxml/SignUpView.fxml";
        }

    },

    USERS {

        /** Users View */
        @Override
        public String getFXMLFile() {
            return "/fxml/UsersView.fxml";
        }

    },

    NOTIFICATIONS {

        /** Notifications View */
        @Override
        public String getFXMLFile() {
            return "/fxml/NotificationsView.fxml";
        }

    },

    PROFILE {

        /** Profile View */
        @Override
        public String getFXMLFile() {
            return "/fxml/ProfileView.fxml";
        }

    },

    NOTIFICATION_ITEM {

        /** NotificationEntity Item View */
        @Override
        public String getFXMLFile() {
            return "/fxml/NotificationItemView.fxml";
        }

    };

    /**
     * Effect: Returns data from application properties.
     * @param key: The data's key in properties file.
     * @return String value from application.properties external files.
     */
    @SuppressWarnings("all")
    protected static String getDataFromBundle(final String key) {
        return ResourceBundle.getBundle("application").getString(key);
    }
}
