package domaintest;

import javax.persistence.*;

/**
 * Name:         NotificationEntity
 * Effect:       Ignore this entity (for testing only)
 * Date:         4/17/2017
 * Tested:       False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Entity
@Table(name = "NOTIFICATION")
@SuppressWarnings("unused")
public class NotificationEntity
    implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTIFICATION_ID")
    private Integer notificationId;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "PAYMENT_TYPE", nullable = false)
    private Boolean paymentType;

    public NotificationEntity(UserEntity user ) {
        this(user, "", Boolean.FALSE);
    }

    @SuppressWarnings("all")
    public NotificationEntity(UserEntity user, String text, Boolean paymentType) {
        this.user = user;
        this.text = text;
        this.paymentType = paymentType;
    }

    @Override
    public Integer getId() {
        return notificationId;
    }

    @Override
    public void setId(Integer id) {
        this.notificationId = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Boolean paymentType) {
        this.paymentType = paymentType;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return this.notificationId + " " +
                this.text + " " +
                this.paymentType + " " +
                this.user;
    }
}
