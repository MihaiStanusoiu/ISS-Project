package databasedomain;

import javax.persistence.*;

/**
 * Name:         NotificationTable
 * Effect:       Corresponding class for the notification table in the database.
 * Date:         08.04.2017
 * Tested:       False
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */


@Entity
@Table(name = "Notification")
@SuppressWarnings("unused")
public class NotificationTable {

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
    private UserTable user;

    public NotificationTable() {}

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
     * @return [UserTable]: returns the user of a notification.
     */
    public UserTable getUser() {
        return user;
    }

    /**
     * Effect: Sets the user of a notification.
     * @param user [UserTable]: new value for user
     */
    public void setUser(UserTable user) {
        this.user = user;
    }
}
