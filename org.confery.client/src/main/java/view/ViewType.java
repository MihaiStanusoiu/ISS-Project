package view;

import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum ViewType implements ViewTypeInterface {

    CONFERENCES {
        /** Conferences View */
        @Override
        public String getFXMLFile() {
            return "/fxml/grid/ConferencesView.fxml";
        }

    },

    ADD_CONFERENCE {
        /** My Conferences View */
        @Override
        public String getFXMLFile() {
            return "/fxml/add/AddConferenceView.fxml";
        }

    },

    MEMBERS_CONFERENCE {
        /** My Conferences View */
        @Override
        public String getFXMLFile() {
            return "/fxml/add/AddMembersEditionView.fxml";
        }

    },

    USER_CELL_LIST_ITEM {
        /** My Conferences View */
        @Override
        public String getFXMLFile() {
            return "/fxml/list/UserCellListView.fxml";
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
            return "/fxml/entity/ConferenceView.fxml";
        }

    },

    USER {
        /** UserEntity View */
        @Override
        public String getFXMLFile() {
            return "/fxml/entity/UserView.fxml";
        }

    },

    CONFERENCE_ITEM {
        /** ConferenceEntity Item View */
        @Override
        public String getFXMLFile() {
            return "/fxml/pagination/ConferenceItemView.fxml";
        }

    },

    USER_ITEM {
        /** UserEntity Item View */
        @Override
        public String getFXMLFile() {
            return "/fxml/pagination/UserItemView.fxml";
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
            return "/fxml/grid/UsersView.fxml";
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
            return "/fxml/pagination/NotificationItemView.fxml";
        }

    },

    EDITION_ITEM {
        @Override
        public String getFXMLFile() {
            return "/fxml/pagination/EditionItemView.fxml";
        }

    },

    EDITION {
        @Override
        public String getFXMLFile() {
            return "/fxml/entity/EditionView.fxml";
        }

    },

    SUBMISSION_ITEM {
        @Override
        public String getFXMLFile() {
            return "/fxml/pagination/SubmissionItemView.fxml";
        }

    },

    ADD_EDITION {
        @Override
        public String getFXMLFile() {
            return "/fxml/add/AddEditionView.fxml";
        }
    },

    ADD_SESSION {
        @Override
        public String getFXMLFile() {
            return "/fxml/add/AddSessionsEditionView.fxml";
        }
    },

    ADD_SUBMISSION {
        @Override
        public String getFXMLFile() {
            return "/fxml/add/AddSubmissionsEditionView.fxml";
        }
    },

    ADD_SUBMISSION_TO_EDITION {
        @Override
        public String getFXMLFile() {
            return "/fxml/add/AddSubmissionView.fxml";
        }
    },

    SESSION_ITEM {
        @Override
        public String getFXMLFile() {
            return "/fxml/pagination/SessionItemView.fxml";
        }
    },

    SESSION {
        @Override
        public String getFXMLFile() {
            return "/fxml/entity/SessionView.fxml";
        }
    },

    CONFERENCE_UPDATE {
        @Override public String getFXMLFile() {
            return "/fxml/add/UpdateConferenceView.fxml";
        }
    },

    EDITION_UPDATE {
        @Override public String getFXMLFile() {
            return "/fxml/add/UpdateEditionView.fxml";
        }
    }, UPDATE_EDITION_MEMBERS {
        @Override public String getFXMLFile() {
            return "/fxml/add/UpdateMembersEditionView.fxml";
        }
    };

    /**
     * Effect: Returns data from application properties.
     *
     * @param key: The data's key in properties file.
     * @return String value from application.properties external files.
     */
    @SuppressWarnings("all")
    protected static String getDataFromBundle(final String key) {
        return ResourceBundle.getBundle("application").getString(key);
    }
}
