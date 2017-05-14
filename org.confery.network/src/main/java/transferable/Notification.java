package transferable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("All")
public class Notification extends Idable<Integer> {

    /**
     * The notification's text. [String] -- not null
     */
    private String text;

    /**
     * Design to test if a notification provides a payment request.
     */
    private Boolean isPayment;

    /**
     * By default the notification is not requesting a payment from the user.
     * @param text The notification's text. [String] -- not null
     */
    public Notification(String text) {
        this(0, text, Boolean.FALSE);
    }

    /**
     * @param text The notification's text. [String] -- not null
     * @param isPayment Design to test if a notification provides a payment request.
     */
    public Notification(String text, Boolean isPayment) {
        this(0, text, isPayment);
    }

    /**
     * @param id The notification's id [unique] -- not null
     * @param text The notification's text. [String] -- not null
     * @param isPayment Design to test if a notification provides a payment request.
     */
    public Notification(Integer id, String text, Boolean isPayment) {
        this.id = id;
        this.text = text;
        this.isPayment = isPayment;
    }

    /**
     * @return The notification's text. [String] -- not null
     */
    public String getText() {
        return text;
    }

    /**
     * Tests if a notification provides a payment request.
     */
    public Boolean getPayment() {
        return isPayment;
    }
}
