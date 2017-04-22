package domain;

import javax.persistence.*;

/**
 * Name:        EditionMemberEntity
 * Effect:      Corresponding class for the EditionMemberEntity table in the database.
 * Date:        4/8/2017
 * Tested:      False
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */

@Entity
@Table(name="EDITION_MEMBER")
@SuppressWarnings("unused")
public class EditionMemberEntity  {

    @EmbeddedId
    private EditionMemberId pkId;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ID_CONFERENCE")
    private EditionEntity edition;

    @ManyToOne
    @JoinColumn(name = "ID_CONFIGURATION_EDITION_MEMBER")
    private ConfigurationEditionMemberEntity idConfiguration;

    public EditionMemberEntity() { }

    /**
     * Effect: Returns the composite primary key id of the conference member
     * @return [EditionMemberId]: composite id of the conference member
     */
    public EditionMemberId getPkId() {
        return pkId;
    }

    /**
     * Effect: Sets the primary key id of the conference member.
     * @param pkId [EditionMemberId]: the primary key
     */
    public void setPkId(EditionMemberId pkId) {
        this.pkId = pkId;
    }

    /**
     * Effect: Returns the user of the conference member
     * @return [UserEntity]: user that is the conference member
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Effect: Sets the user to the given value
     * @param user [UserEntity]: new value for the user
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Effect: Returns the conference of the conference member
     * @return [EditionEntity]: conference of the conference member
     */
    public EditionEntity getEditionTable() {
        return edition;
    }

    /**
     * Effect: Sets the conference to the given value
     * @param editionTable [EditionEntity]: new value for the password
     */
    public void setEditionTable(EditionEntity editionTable) {
        this.edition = editionTable;
    }

    /**
     * Effect: Returns the configuration of the conference member
     * @return [ConfigurationEditionMember]: configuration of the conference member
     */
    public ConfigurationEditionMemberEntity getConfigurationConferenceMember() {
        return idConfiguration;
    }
    /**
     * Effect: Sets the configuration to the given value
     * @param configurationConferenceMember [ConfigurationEditionMember]: new value for the configuration
     */
    public void setConfigurationConferenceMember(
        ConfigurationEditionMemberEntity configurationConferenceMember) {
        this.idConfiguration = configurationConferenceMember;
    }
}
