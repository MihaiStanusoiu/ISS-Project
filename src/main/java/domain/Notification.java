package domain;

/**
 * Name:         Notification
 * Effect:       Notifications about payment.
 * Date:         02/04/2017
 * Tested:       True
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

public class Notification extends Idable<Integer> {

    private String text;
    private Boolean isPayment;
    private Integer idUser;

    public Notification(Integer id,String text, Boolean isPayment, Integer idUser) {
        this.id=id;
        this.text = text;
        this.isPayment = isPayment;
        this.idUser = idUser;
    }

    private Notification(String text, Boolean isPayment, Integer idUser) {
        this(0,text,isPayment,idUser);
    }

    /**
     * Effect: Return the notification's text.
     * @return [String] : returns the text.
     */
    public String getText() {
        return text;
    }

    /**
     * Effect: Return the payment status.
     * @return [Boolean] : returns the payment status.
     */
    public Boolean getPayment() {
        return isPayment;
    }

    /**
     * Effect: Return the id user.
     * @return [Integer] : returns the id user.
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * Effect: Set the  payment.
     * @param payment : [Boolean]  the value of the payment status.
     */
    public void setPayment(Boolean payment) {
        isPayment = payment;
    }

}
