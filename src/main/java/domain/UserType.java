package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Name:            UserType
 * Effect:          Enum class for all user types.
 * Date:            4/2/2017
 * Tested:          True
 *
 * @author          Stanusoiu Mihai-Teodor
 * @version         1.0
 */

public enum UserType implements UserTypeInterface {

    USER {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.add(Permission.VIEW_CONFERENCE);
            return permissions;
        }
    },

    CONFERENCE_MEMBER {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.USER.getPermissions());
            return permissions;
        }
    },

    SECTION_MEMBER {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.CONFERENCE_MEMBER.getPermissions());
            permissions.add(Permission.PAY);
            return permissions;
        }
    },

    SECTION_CHAIR {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.SECTION_MEMBER.getPermissions());
            permissions.add(Permission.CREATE_SECTION);
            permissions.add(Permission.DELETE_SECTION);
            permissions.add(Permission.UPDATE_SECTION);
            return permissions;
        }
    },

    AUTHOR {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.SECTION_MEMBER.getPermissions());
            permissions.add(Permission.CREATE_SUBMISSION);
            permissions.add(Permission.UPLOAD_SUBMISSION);
            return permissions;
        }
    },

    REVIEWER {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.PC_MEMBER.getPermissions());
            permissions.add(Permission.REVIEW);
            return permissions;
        }
    },

    LISTENER {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.SECTION_MEMBER.getPermissions());
            permissions.add(Permission.CHOOSE_SECTION);
            return permissions;
        }
    },

    SPEAKER {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.AUTHOR.getPermissions());
            return permissions;
        }
    },

    CHAIR {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.CO_CHAIR.getPermissions());
            permissions.add(Permission.CREATE_CONFERENCE);
            permissions.add(Permission.DELETE_CONFERENCE);
            return permissions;
        }
    },

    CO_CHAIR {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.PC_MEMBER.getPermissions());
            permissions.add(Permission.UPDATE_CONFERENCE);
            permissions.add(Permission.GIVE_PERMISSIONS);
            return permissions;
        }
    },

    PC_MEMBER {
        @Override
        public ArrayList<Permission> getPermissions() {
            ArrayList<Permission> permissions = new ArrayList<>();
            permissions.addAll(UserType.SECTION_MEMBER.getPermissions());
            permissions.add(Permission.BID);
            return permissions;
        }
    }

}