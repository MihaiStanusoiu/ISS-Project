package domain;

import javax.persistence.*;

/**
 * Name:        EditionMember
 * Effect:      Corresponding class for the EditionMember table in the database.
 * Date:        4/8/2017
 * Tested:      False
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */

@Entity
@Table(name="EditionMember")
@SuppressWarnings("unused")
public class EditionMember {

    @EmbeddedId
    private EditionMemberId pkId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_conference")
    private Edition editionTable;

    @OneToOne
    @JoinColumn(name = "id_configuration")
    private ConfigurationEditionMember configurationConferenceMember;

    public EditionMember() { }

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
     * @return [User]: user that is the conference member
     */
    public User getUser() {
        return user;
    }

    /**
     * Effect: Sets the user to the given value
     * @param user [User]: new value for the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Effect: Returns the conference of the conference member
     * @return [Edition]: conference of the conference member
     */
    public Edition getEditionTable() {
        return editionTable;
    }

    /**
     * Effect: Sets the conference to the given value
     * @param editionTable [Edition]: new value for the password
     */
    public void setEditionTable(Edition editionTable) {
        this.editionTable = editionTable;
    }

    /**
     * Effect: Returns the configuration of the conference member
     * @return [ConfigurationEditionMember]: configuration of the conference member
     */
    public ConfigurationEditionMember getConfigurationConferenceMember() {
        return configurationConferenceMember;
    }
    /**
     * Effect: Sets the configuration to the given value
     * @param configurationConferenceMember [ConfigurationEditionMember]: new value for the configuration
     */
    public void setConfigurationConferenceMember(
        ConfigurationEditionMember configurationConferenceMember) {
        this.configurationConferenceMember = configurationConferenceMember;
    }
}
