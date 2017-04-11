package databasedomain;

import javax.persistence.*;

/**
 * Name:        ConferenceMemberTable
 * Effect:      Corresponding class for the ConferenceMember table in the database.
 * Date:        4/8/2017
 * Tested:      False
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */

@Entity
@Table(name="ConferenceMember")
@SuppressWarnings("unused")
public class ConferenceMemberTable {

    @EmbeddedId
    private ConferenceMemberTableId pkId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserTable user;

    @ManyToOne
    @JoinColumn(name = "id_conference")
    private ConferenceTable conferenceTable;

    @OneToOne
    @JoinColumn(name = "id_configuration")
    private ConfigurationConferenceMemberTable configurationConferenceMember;

    public ConferenceMemberTable() { }

    /**
     * Effect: Returns the composite primary key id of the conference member
     * @return [ConferenceMemberTableId]: composite id of the conference member
     */
    public ConferenceMemberTableId getPkId() {
        return pkId;
    }

    /**
     * Effect: Sets the primary key id of the conference member.
     * @param pkId [ConferenceMemberTableId]: the primary key
     */
    public void setPkId(ConferenceMemberTableId pkId) {
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
     * @return [ConferenceTable]: conference of the conference member
     */
    public ConferenceTable getConferenceTable() {
        return conferenceTable;
    }

    /**
     * Effect: Sets the conference to the given value
     * @param conferenceTable [ConferenceTable]: new value for the password
     */
    public void setConferenceTable(ConferenceTable conferenceTable) {
        this.conferenceTable = conferenceTable;
    }

    /**
     * Effect: Returns the configuration of the conference member
     * @return [ConfigurationConferenceMemberTable]: configuration of the conference member
     */
    public ConfigurationConferenceMemberTable getConfigurationConferenceMember() {
        return configurationConferenceMember;
    }
    /**
     * Effect: Sets the configuration to the given value
     * @param configurationConferenceMember [ConfigurationConferenceMemberTable]: new value for the configuration
     */
    public void setConfigurationConferenceMember(ConfigurationConferenceMemberTable configurationConferenceMember) {
        this.configurationConferenceMember = configurationConferenceMember;
    }
}
