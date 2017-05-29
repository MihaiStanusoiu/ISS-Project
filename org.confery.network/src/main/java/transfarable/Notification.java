package transfarable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Notification {

    private Integer id;
    private String text;
    private Boolean paymentType;

    public Notification(Integer id, String text, Boolean paymentType) {
        this.id = id;
        this.text = text;
        this.paymentType = paymentType;
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for property 'text'.
     *
     * @return Value for property 'text'.
     */
    public String getText() {
        return text;
    }

    /**
     * Getter for property 'paymentType'.
     *
     * @return Value for property 'paymentType'.
     */
    public Boolean getPaymentType() {
        return paymentType;
    }
}
