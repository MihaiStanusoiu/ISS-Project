package view;

import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum GradientType implements ResourceGetter {

    BLUE {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getValue() {
            return GradientType.getDataFromBundle("gradient.blue");
        }

    },

    RED {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getValue() {
            return GradientType.getDataFromBundle("gradient.red");
        }

    },

    GREEN {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getValue() {
            return GradientType.getDataFromBundle("gradient.green");
        }

    },

    LIGHT_GREEN {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getValue() {
            return GradientType.getDataFromBundle("gradient.light.green");
        }

    };

    /**
     * Effect: Returns data from gradient properties.
     *
     * @param key: The data's key in properties file.
     * @return String value from gradient.properties external files.
     */
    @SuppressWarnings("all")
    private static String getDataFromBundle(final String key) {
        return ResourceBundle.getBundle("gradient").getString(key);
    }

}
