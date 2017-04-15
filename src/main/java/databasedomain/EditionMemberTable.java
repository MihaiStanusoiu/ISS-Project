package databasedomain;

import javax.persistence.*;

/**
 * Name:        EditionMemberTable
 * Effect:      Corresponding class for the EditionMember table in the database.
 * Date:        4/8/2017
 * Tested:      False
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */

@Entity
@Table(name="EditionMember")
@SuppressWarnings("unused")
public class EditionMemberTable {

    @EmbeddedId
    private EditionMemberId pkId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserTable user;

    @ManyToOne
    @JoinColumn(name = "id_conference")
    private EditionTable editionTable;

    @OneToOne
    @JoinColumn(name = "id_configuration")
    private ConfigurationEditionMemberTable configurationConferenceMember;

    public EditionMemberTable() { }

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
     * @return [UserTable]: user that is the conference member
     */
    public UserTable getUser() {
        return user;
    }

    /**
     * Effect: Sets the user to the given value
     * @param user [UserTable]: new value for the user
     */
    public void setUser(UserTable user) {
        this.user = user;
    }

    /**
     * Effect: Returns the conference of the conference member
     * @return [EditionTable]: conference of the conference member
     */
    public EditionTable getEditionTable() {
        return editionTable;
    }

    /**
     * Effect: Sets the conference to the given value
     * @param editionTable [EditionTable]: new value for the password
     */
    public void setEditionTable(EditionTable editionTable) {
        this.editionTable = editionTable;
    }

    /**
     * Effect: Returns the configuration of the conference member
     * @return [ConfigurationEditionMemberTable]: configuration of the conference member
     */
    public ConfigurationEditionMemberTable getConfigurationConferenceMember() {
        return configurationConferenceMember;
    }
    /**
     * Effect: Sets the configuration to the given value
     * @param configurationConferenceMember [ConfigurationEditionMemberTable]: new value for the configuration
     */
    public void setConfigurationConferenceMember(
        ConfigurationEditionMemberTable configurationConferenceMember) {
        this.configurationConferenceMember = configurationConferenceMember;
    }
}
