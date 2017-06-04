package view;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum Icon implements ResourceGetter {

    EMPTY {
        @Override public String getValue() {
            return "/png_icons/empty_icon.png";
        }
    },

    CLOSE {
        @Override
        public String getValue() {
            return "/png_icons/close_icon.png";
        }
    }
}
