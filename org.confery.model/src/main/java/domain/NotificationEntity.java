
package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Tested True
 * @author Tanasie Luiza Maria & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "NOTIFICATION")
@SuppressWarnings("unused")
public class NotificationEntity implements Serializable,Idable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "ID_NOTIFICATION")
    private Integer id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "PAYMENT_TYPE")
    private Boolean paymentType;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "ID_USER")
    private UserEntity user;

    private static final Integer DEFAULT_ID = 0;
    private static final Boolean DEFAULT_PAYMENT_TYPE = Boolean.FALSE;

    /**
     * @apiNote Don't use this constructor [it's for testing only]
     */
    @Deprecated
    public NotificationEntity() {
        this(DEFAULT_ID, "", DEFAULT_PAYMENT_TYPE);
    }

    public NotificationEntity(String text) {
        this(DEFAULT_ID, text, DEFAULT_PAYMENT_TYPE);
    }

    public NotificationEntity(String text, Boolean paymentType) {
        this(DEFAULT_ID, text, paymentType);
    }

    public NotificationEntity(Integer id, String text, Boolean paymentType) {
        this.id = id;
        this.text = text;
        this.paymentType = paymentType;
    }

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
     * @return [UserEntity]: returns the user of a notification.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Effect: Sets the user of a notification.
     * @param user [UserEntity]: new value for user
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
        return id.equals(that.id) && (text != null ? text.equals(that.text) : that.text == null) &&
                (paymentType != null ? paymentType.equals(that.paymentType) : that.paymentType == null) &&
                (user != null ? user.equals(that.user) : that.user == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
