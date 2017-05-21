
package domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 *
 * @author Stanusoiu Mihai-Teodor & Alexandru Stoica
 * @version 1.0
 */

@Entity
@Table(name = "EDITION_MEMBER")
@SuppressWarnings("unused")
public class EditionMemberEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_EDITION_MEMBER")
    private Integer id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "ID_USER")
    private UserEntity user;

    @ManyToOne(targetEntity = EditionEntity.class)
    @JoinColumn(name = "ID_EDITION")
    private EditionEntity edition;

    @ManyToOne(targetEntity = ConfigurationEditionMemberEntity.class)
    @JoinColumn(name = "ID_CONFIGURATION_EDITION_MEMBER")
    private ConfigurationEditionMemberEntity configuration;

    @OneToMany(targetEntity = ReviewerEntity.class, mappedBy = "member",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ReviewerEntity> reviewers;

    private static final Integer DEFAULT_ID = 0;

    /**
     * @implNote Don't use this constructor [it's for testing only]
     */
    @Deprecated
    public EditionMemberEntity() {
        this(DEFAULT_ID, null, null, null);
    }

    public EditionMemberEntity(Integer id, UserEntity user, EditionEntity edition,
                               ConfigurationEditionMemberEntity configuration) {
        this.id = id;
        this.user = user;
        this.edition = edition;
        this.configuration = configuration;
    }

    public EditionMemberEntity(UserEntity user, EditionEntity edition,
                               ConfigurationEditionMemberEntity configuration) {
        this(DEFAULT_ID, user, edition, configuration);
    }

    public EditionMemberEntity(UserEntity user,
                               ConfigurationEditionMemberEntity configuration) {
        this(DEFAULT_ID, user, null, configuration);
    }

    public EditionMemberEntity(UserEntity user) {
        this(DEFAULT_ID, user, null, null);
    }

    public EditionMemberEntity(Integer id, UserEntity user) {
        this(id, user, null, null);
    }

    public EditionMemberEntity(Integer id, UserEntity user,
                               ConfigurationEditionMemberEntity configuration) {
        this(id, user, null, configuration);
    }

    /**
     * Effect: Returns the reviewers of the EditionMemberEntity.
     *
     * @return [Set<ReviewerEntity>]: reviewers of an EditionMemberEntity.
     */
    public Set<ReviewerEntity> getReviewers() {
        return reviewers;
    }

    /**
     * Effect: Sets the reviewers of the EditionMemberEntity.
     *
     * @param reviewers: new value for the reviewers.
     */
    public void setReviewers(Set<ReviewerEntity> reviewers) {
        this.reviewers = reviewers;
    }

    /**
     * Effect: Returns the composite primary key id of the conference member
     *
     * @return [Integer]: id of the conference member
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the primary key id of the conference member.
     *
     * @param id [Integer] : the primary key
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Returns the user of the conference member
     *
     * @return [UserEntity]: user that is the conference member
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Effect: Sets the user to the given value
     *
     * @param user [UserEntity]: new value for the user
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Effect: Returns the conference of the conference member
     *
     * @return [EditionEntity]: conference of the conference member
     */
    public EditionEntity getEdition() {
        return edition;
    }

    /**
     * Effect: Sets the conference to the given value
     *
     * @param editionTable [EditionEntity]: new value for the password
     */
    public void setEdition(EditionEntity editionTable) {
        this.edition = editionTable;
    }

    /**
     * Effect: Returns the configuration of the conference member
     *
     * @return [ConfigurationEditionMember]: configuration of the conference member
     */
    public ConfigurationEditionMemberEntity getConfigurationEditionMember() {
        return configuration;
    }

    /**
     * Effect: Sets the configuration to the given value
     *
     * @param configurationConferenceMember [ConfigurationEditionMember]: new value for the configuration
     */
    public void setConfigurationEditionMember(
            ConfigurationEditionMemberEntity configurationConferenceMember) {
        this.configuration = configurationConferenceMember;
    }

    /**
     * Returns the member's submissions.
     *
     * @return All the submissions.
     */
    public List<SubmissionEntity> getSubmissions() {
        return reviewers.stream()
                .map(ReviewerEntity::getSubmission)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditionMemberEntity that = (EditionMemberEntity) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
