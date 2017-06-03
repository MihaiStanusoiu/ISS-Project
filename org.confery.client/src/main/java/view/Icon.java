package view;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum Icon implements ResourceGetter {
    CLOSE {
        @Override
        public String getValue() {
            return "/png_icons/close_icon.png";
        }
    }
}
