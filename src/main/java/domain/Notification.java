package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Name:         Notification
 * Effect:       Corresponding class for the notification table in the database.
 * Date:         08.04.2017
 * Tested:       False
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */


@Entity
@Table(name = "Notification")
@SuppressWarnings("unused")
public class Notification implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_notification")
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "payment_type")
    private Boolean paymentType;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Notification() {}

    /**
     * Effect: Return the id of a notification.
     * @return [Integer] : returns the id of a notification.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a notification.
     * @param id [Integer] id: new value for id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the text of a notification.
     * @return [String] : returns the text of a notification.
     */
    public String getText() {
        return text;
    }

    /**
     * Effect: Sets the text of a notification.
     * @param text [String] id: new value for text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Effect: Return the payment type of a notification.
     * @return [Boolean]: returns the payment type of a notification.
     */
    public Boolean getPaymentType() {
        return paymentType;
    }

    /**
     * Effect: Sets the payment type of a notification.
     * @param paymentType [Boolean]: new value for payment type
     */
    public void setPaymentType(Boolean paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Effect: Return the user of a notification.
     * @return [User]: returns the user of a notification.
     */
    public User getUser() {
        return user;
    }

    /**
     * Effect: Sets the user of a notification.
     * @param user [User]: new value for user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
