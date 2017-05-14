package view;

/**
 * Name:        GradientGenerator
 *
 * Effect:      Generates a gradient color for view's based on FXML CSS.
 *
 *              The allowed gradients are defined by the gradient.prop,
 *              you can't modify them by adding one more gradient in your instance.
 *
 *              All the available gradients are designed to look good
 *              on the system's UI objects, don't modify them without permission.
 *
 * Date:        12/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class GradientGenerator {

    /**
     * Available gradients in our system.
     */
    private final GradientType[] gradients = {
        GradientType.BLUE,
        GradientType.RED,
        GradientType.GREEN,
        GradientType.LIGHT_GREEN
    };

    public GradientGenerator() { }

    /**
     * Returns a random generated gradient from a set of predefined gradients.
     * @return The gradient object that defines the wanted color.
     */
    public GradientType getGradient() {
        Double index = Math.random() * (gradients.length);
        return gradients[index.intValue()];
    }

}
