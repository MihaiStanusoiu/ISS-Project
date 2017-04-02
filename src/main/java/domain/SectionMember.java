package domain;

import java.util.ArrayList;

/**
 * Name:         SectionMember
 * Effect:         Class for SectionMember user
 * Date:           4/2/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */
public class SectionMember extends ConferenceMember {

    //  Fields

    private Integer idSection;


    //  Constructors

    public SectionMember(
            Integer id, String username, String password, String email, String name, String website, String bio,
            String location, Integer idConference, Integer idSection
    ) {
        super(id, username, password, email, name, website, bio, location, idConference);
        this.idSection = idSection;
    }

    public SectionMember(
            Integer id, String username, String password, String email, String name, String website, String bio,
            String location, Integer idConference
    ) {
        super(id, username, password, email, name, website, bio, location, idConference);
    }

    public SectionMember(Integer id, Integer idConference, Integer idSection) {
        super(id, idConference);
        this.idSection = idSection;
    }


    //  Getters and Setters

    /**
     * Effect: Returns UserType.SECTION_MEMBER
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.SECTION_MEMBER;
    }

    /**
     * Effect: Getter for the id of the section.
     * @return Integer : returns idSection.
     */
    public Integer getIdSection() {
        return idSection;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.SECTION_MEMBER)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.SECTION_MEMBER.getPermissions();
    }

    /**
     * Effect: Sets the idSection to the given value
     * @param idSection: new value for idSubmission
     */
    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }
}
