package domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:        EditionMemberEntity
 * Effect:      Corresponding class for the EditionMemberEntity table in the database.
 * Date:        4/8/2017
 * Tested:      True
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */

@Entity
@Table(name="EDITION_MEMBER")
@SuppressWarnings("unused")
public class EditionMemberEntity implements Idable<Integer> {

    @Id@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_EDITION_MEMBER")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity idUser;

    @ManyToOne
    @JoinColumn(name = "ID_EDITION")
    private EditionEntity idEdition;

    @ManyToOne
    @JoinColumn(name = "ID_CONFIGURATION_EDITION_MEMBER")
    private ConfigurationEditionMemberEntity idConfiguration;

    @OneToMany(mappedBy = "idEditionMember", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ReviewerEntity> reviewers;

    public EditionMemberEntity() { }

    /**
     * Effect: Returns the reviewers of the EditionMemberEntity.
     * @return [Set<ReviewerEntity>]: reviewers of an EditionMemberEntity.
     */
    public Set<ReviewerEntity> getReviewers() {
        return reviewers;
    }

    /**
     * Effect: Sets the reviewers of the EditionMemberEntity.
     * @param reviewers: new value for the reviewers.
     */
    public void setReviewers(Set<ReviewerEntity> reviewers) {
        this.reviewers = reviewers;
    }

    /**
     * Effect: Returns the composite primary key id of the conference member
     * @return [Integer]: id of the conference member
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the primary key id of the conference member.
     * @param id [Integer] : the primary key
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Returns the idUser of the conference member
     * @return [UserEntity]: idUser that is the conference member
     */
    public UserEntity getIdUser() {
        return idUser;
    }

    /**
     * Effect: Sets the idUser to the given value
     * @param idUser [UserEntity]: new value for the idUser
     */
    public void setIdUser(UserEntity idUser) {
        this.idUser = idUser;
    }

    /**
     * Effect: Returns the conference of the conference member
     * @return [EditionEntity]: conference of the conference member
     */
    public EditionEntity getIdEdition() {
        return idEdition;
    }

    /**
     * Effect: Sets the conference to the given value
     * @param editionTable [EditionEntity]: new value for the password
     */
    public void setIdEdition(EditionEntity editionTable) {
        this.idEdition = editionTable;
    }

    /**
     * Effect: Returns the configuration of the conference member
     * @return [ConfigurationEditionMember]: configuration of the conference member
     */
    public ConfigurationEditionMemberEntity getConfigurationEditionMember() {
        return idConfiguration;
    }
    /**
     * Effect: Sets the configuration to the given value
     * @param configurationConferenceMember [ConfigurationEditionMember]: new value for the configuration
     */
    public void setConfigurationEditionMember(
        ConfigurationEditionMemberEntity configurationConferenceMember) {
        this.idConfiguration = configurationConferenceMember;
    }
}
