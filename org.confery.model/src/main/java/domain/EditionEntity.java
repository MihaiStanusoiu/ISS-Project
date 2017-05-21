
package domain;

import exception.ModelException;
import exception.SystemException;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 *
 * @author Simion George-Vlad & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "EDITION")
@SuppressWarnings("unused")
public class EditionEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_EDITION")
    private Integer id;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "BIO")
    private String bio;

    @Column(name = "ABSTRACT_DEADLINE")
    private Date abstractDeadline;

    @Column(name = "PAPER_DEADLINE")
    private Date paperDeadline;

    @Column(name = "EVALUATION_DEADLINE")
    private Date evaluationDeadline;

    @Column(name = "BIDDING_DEADLINE")
    private Date biddingDeadline;

    @ManyToOne(targetEntity = ConferenceEntity.class)
    @JoinColumn(name = "ID_CONFERENCE")
    private ConferenceEntity conference;

    @OneToMany(targetEntity = EditionMemberEntity.class, fetch = FetchType.EAGER,
            mappedBy = "edition", cascade = CascadeType.ALL)
    private Set<EditionMemberEntity> members;

    @OneToMany(targetEntity = SessionEntity.class, fetch = FetchType.EAGER,
            mappedBy = "edition", cascade = CascadeType.ALL)
    private Set<SessionEntity> sessions;

    @OneToMany(targetEntity = SubmissionEntity.class, fetch = FetchType.EAGER,
            mappedBy = "edition", cascade = CascadeType.ALL)
    private Set<SubmissionEntity> submissions;

    private static final Integer DEFAULT_ID = 0;
    private static final Date DEFAULT_DATE = new Date();
    private static final String DEFAULT_LOCATION = "Unknown";
    private static final String DEFAULT_BIO = "";

    public EditionEntity() {
        this(DEFAULT_ID, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_LOCATION,
                DEFAULT_BIO, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE);
    }


    /**
     * @param id                 The object's id
     * @param startDate          The stating date
     * @param endDate            The ending date
     * @param location           The edition's location
     * @param bio                The edition's bio
     * @param abstractDeadline   The abstract deadline [optional]
     * @param paperDeadline      The paper deadline [optional]
     * @param evaluationDeadline The evaluation deadline [optional]
     * @param biddingDeadline    The bidding deadline [optional]
     */
    public EditionEntity(Integer id,
                         Date startDate,
                         Date endDate,
                         String location,
                         String bio,
                         Date abstractDeadline,
                         Date paperDeadline,
                         Date evaluationDeadline,
                         Date biddingDeadline) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.abstractDeadline = abstractDeadline;
        this.paperDeadline = paperDeadline;
        this.evaluationDeadline = evaluationDeadline;
        this.biddingDeadline = biddingDeadline;
        this.members = new HashSet<>();
        this.submissions = new HashSet<>();
        this.sessions = new HashSet<>();
    }

    /**
     * @param startDate          The stating date
     * @param endDate            The ending date
     * @param location           The edition's location
     * @param bio                The edition's bio
     * @param abstractDeadline   The abstract deadline [optional]
     * @param paperDeadline      The paper deadline [optional]
     * @param evaluationDeadline The evaluation deadline [optional]
     * @param biddingDeadline    The bidding deadline [optional]
     */
    public EditionEntity(Date startDate,
                         Date endDate,
                         String location,
                         String bio,
                         Date abstractDeadline,
                         Date paperDeadline,
                         Date evaluationDeadline,
                         Date biddingDeadline) {
        this(DEFAULT_ID, startDate, endDate, location, bio, abstractDeadline,
                paperDeadline, evaluationDeadline, biddingDeadline);
    }

    /**
     * @param startDate The stating date
     * @param endDate   The ending date
     * @param location  The edition's location
     * @param bio       The edition's bio
     */
    public EditionEntity(Date startDate, Date endDate, String location, String bio) {
        this(DEFAULT_ID, startDate, endDate, location, bio,
                DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE);
    }

    /**
     * @param startDate The stating date
     * @param endDate   The ending date
     * @param location  The edition's location
     */
    public EditionEntity(Date startDate, Date endDate, String location) {
        this(DEFAULT_ID, startDate, endDate, location, DEFAULT_BIO,
                DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE);
    }

    /**
     * @param location The edition's location
     */
    public EditionEntity(String location) {
        this(DEFAULT_ID, DEFAULT_DATE, DEFAULT_DATE, location, DEFAULT_BIO,
                DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE);
    }

    /**
     * Effect: Return the id of this conference.
     *
     * @return [Integer]: returns the id of conference.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a conference.
     *
     * @param id: new value for conference id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the start date of this conference.
     *
     * @return [Date]: returns the startDate of conference.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Effect: Sets the start date of a conference.
     *
     * @param startDate: new value for conference startDate.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Effect: Return the end date of this conference.
     *
     * @return [Date]: returns the endDate of conference.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Effect: Sets the end date of a conference.
     *
     * @param endDate: new value for conference endDate.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Effect: Return the location of this conference.
     *
     * @return [String]: returns the location of conference.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Sets the location of a conference.
     *
     * @param location: new value for conference location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Effect: Return the bio of this conference.
     *
     * @return [String]: returns the bio of conference.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Effect: Sets the bio of a conference.
     *
     * @param bio: new value for conference bio.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Effect: Return the abstract deadline of this conference.
     *
     * @return [Date]: returns the abstractDeadline of conference.
     */
    public Date getAbstractDeadline() {
        return abstractDeadline;
    }

    /**
     * Effect: Sets the abstract deadline of a conference.
     *
     * @param abstractDeadline: new value for conference abstractDeadline.
     */
    public void setAbstractDeadline(Date abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
    }

    /**
     * Effect: Return the paper deadline of this conference.
     *
     * @return [Date]: returns the paperDeadline of conference.
     */
    public Date getPaperDeadline() {
        return paperDeadline;
    }

    /**
     * Effect: Sets the paper deadline of a conference.
     *
     * @param paperDeadline: new value for conference paperDeadline.
     */
    public void setPaperDeadline(Date paperDeadline) {
        this.paperDeadline = paperDeadline;
    }

    /**
     * Effect: Return the evaluation deadline of this conference.
     *
     * @return [Date]: returns the evaluationDeadline of conference.
     */
    public Date getEvaluationDeadline() {
        return evaluationDeadline;
    }

    /**
     * Effect: Sets the evaluation deadline of a conference.
     *
     * @param evaluationDeadline: new value for conference evaluationDeadline.
     */
    public void setEvaluationDeadline(Date evaluationDeadline) {
        this.evaluationDeadline = evaluationDeadline;
    }

    /**
     * Effect: Return the bidding deadline of this conference.
     *
     * @return [Date]: returns the biddingDeadline of conference.
     */
    public Date getBiddingDeadline() {
        return biddingDeadline;
    }

    /**
     * Effect: Sets the bidding deadline of a conference.
     *
     * @param biddingDeadline: new value for conference biddingDeadline.
     */
    public void setBiddingDeadline(Date biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
    }

    /**
     * Effect: Return the conference of a Edition.
     *
     * @return [ConferenceEntity]: returns the conference of an Edition.
     */
    public ConferenceEntity getConference() {
        return conference;
    }

    /**
     * Effect: Sets the conference of an Edition.
     *
     * @param conference: new value for conference
     */
    public void setConference(ConferenceEntity conference) {
        this.conference = conference;
    }

    /**
     * Effect: Return the members of an Edition.
     *
     * @return [Set<EditionMemberEntity>]: returns the members of an Edition.
     */
    public Set<EditionMemberEntity> getMembers() {
        return members;
    }

    /**
     * Effect: Sets the members of an Edition.
     *
     * @param members: new value for members.
     */
    public void setMembers(Set<EditionMemberEntity> members) {
        this.members = members;
    }

    /**
     * Effect: Returns the sessions of an Edition.
     *
     * @return [Set<SessionEntity>]: returns the sessions of an Edition.
     */
    public Set<SessionEntity> getSessions() {
        return sessions;
    }

    /**
     * Effect: Returns the submissions of an Edition.
     *
     * @return [Set<SubmissionEntity>]: returns the submissions of an Edition.
     */
    public Set<SubmissionEntity> getSubmissions() {
        return submissions;
    }

    /**
     * Effect: Sets the sessions of an Edition.
     *
     * @param sessions: new value for sessions.
     */
    public void setSessions(Set<SessionEntity> sessions) {
        this.sessions = sessions;
    }

    /**
     * Effect: Sets the submissions of an Edition.
     *
     * @param submissions: new value for submissions.
     */
    public void setSubmissions(Set<SubmissionEntity> submissions) {
        this.submissions = submissions;
    }

    /**
     * Returns all the members as UserEntity.
     *
     * @return All the members.
     */
    public List<UserEntity> getUsers() {
        return members.stream()
                .map(EditionMemberEntity::getUser)
                .collect(Collectors.toList());
    }

    /**
     * Returns all the pc-members of the edition.
     *
     * @return All the pc-members of the edition
     */
    public List<UserEntity> getPcMembers() {
        return members.stream()
                .filter(member -> member.getConfigurationEditionMember().getPcMember().equals(Boolean.TRUE))
                .map(EditionMemberEntity::getUser)
                .collect(Collectors.toList());
    }

    /**
     * Returns all the co-chairs of the edition.
     *
     * @return All the co-chairs of the edition
     */
    public List<UserEntity> getCoChairs() {
        return members.stream()
                .filter(member -> member.getConfigurationEditionMember().getCoChair().equals(Boolean.TRUE))
                .map(EditionMemberEntity::getUser)
                .collect(Collectors.toList());
    }

    /**
     * Returns the chair of the edition.
     *
     * @return The chair of the edition.
     * <p>You can't have a edition without a chair to act as an admin!</p>
     * @throws SystemException If the chair is not set.
     */
    public UserEntity getChair() throws SystemException {
        return members.stream().findFirst()
                .filter(member -> member.getConfigurationEditionMember().getChair().equals(Boolean.TRUE))
                .map(EditionMemberEntity::getUser).orElseThrow(() -> new ModelException("The chair is not set!"));
    }

    /**
     * Returns the conference's name.
     *
     * @return The conference's name
     */
    public String getName() {
        return conference.getName();
    }

    /**
     * Returns the conference's acronym.
     *
     * @return The conference's acronym
     */
    public String getAcronym() {
        return conference.getAcronym();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditionEntity that = (EditionEntity) o;
        return id.equals(that.id) && (startDate != null ? startDate.equals(that.startDate) : that.startDate == null) &&
                (endDate != null ? endDate.equals(that.endDate) : that.endDate == null) &&
                (location != null ? location.equals(that.location) : that.location == null) &&
                (bio != null ? bio.equals(that.bio) : that.bio == null) &&
                (abstractDeadline != null ? abstractDeadline.equals(that.abstractDeadline) :
                        that.abstractDeadline == null) &&
                (paperDeadline != null ? paperDeadline.equals(that.paperDeadline) : that.paperDeadline == null) &&
                (evaluationDeadline != null ? evaluationDeadline.equals(that.evaluationDeadline) :
                        that.evaluationDeadline == null) &&
                (biddingDeadline != null ? biddingDeadline.equals(that.biddingDeadline) :
                        that.biddingDeadline == null) &&
                (conference != null ? conference.equals(that.conference) : that.conference == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (abstractDeadline != null ? abstractDeadline.hashCode() : 0);
        result = 31 * result + (paperDeadline != null ? paperDeadline.hashCode() : 0);
        result = 31 * result + (evaluationDeadline != null ? evaluationDeadline.hashCode() : 0);
        result = 31 * result + (biddingDeadline != null ? biddingDeadline.hashCode() : 0);
        result = 31 * result + (conference != null ? conference.hashCode() : 0);
        return result;
    }
}
