package service;

import domain.MemberRole;
import transferable.*;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public interface MasterService {

    Integer addConference(Conference conference, User active);

    Conference updateConference(Conference conference, Conference with, User active);

    Conference deleteConference(Conference conference, User active);

    List<Conference> getConferences(User active);

    Conference getConferenceById(Integer Id, User active);

    List<Conference> getPopularConferences(User active);

    List<Conference> getRecentConferences(User active);

    Conference addEditionToConference(Conference conference, Edition edition, User active);

    Conference deleteEditionFromConference(Conference conference, Edition edition, User active);

    List<Edition> getConferencesEditions(Conference conference, User active);

    User login(String username, String password);

    User signUp(String username, String password, String confirm, String email, String name);

    User updateUser(User user, User with, User active);

    User deleteUser(User user, User active);

    List<User> getUsers(User active);

    User getUserById(Integer id, User active);

    List<Notification> getUserNotifications(User user, User active);

    List<Notification> getUserPayNotifications(User user, User active);

    List<Submission> getUserSubmissions(User user, User active);

    List<Submission> getUserAcceptedSubmissions(User user, User active);

    List<Submission> getUserRejectedSubmissions(User user, User active);

    List<Submission> getUserInReviewSubmissions(User user, User active);

    List<Conference> getUserConferences(User user, User active);

    List<Conference> getUserActiveConferences(User user, User active);

    User logout(User user, User active);

    Integer addEdition(Edition edition, User active);

    Edition updateEdition(Edition edition, Edition with, User active);

    Edition deleteEdition(Edition edition, User active);

    List<Edition> getEditions(User active);

    Edition getEditionById(Integer id, User active);

    Edition publishEdition(Edition edition);

    List<Submission> getEditionSubmissions(Edition edition, User active);

    List<Submission> getEditionAcceptedSubmissions(Edition edition, User active);

    List<Submission> getEditionRejectedSubmissions(Edition edition, User active);

    List<Submission> getEditionInReviewSubmissions(Edition edition, User active);

    List<User> getEditionMembers(Edition edition, User active);

    List<User> getPcMembers(Edition edition, User active);

    List<User> getCoChairs(Edition edition, User active);

    User getChair(User user, User active);

    Edition addMember(Edition edition, User member, User active, MemberRole role);

    Edition deleteMember(Edition edition, User member, User active);

    Edition addSubmission(Edition edition, Submission submission, User active);

    Edition deleteSubmission(Edition edition, Submission submission, User active);

    List<Session> getEditionSessions(Edition edition);

    Integer addSubmission(Submission submission, User active);

    Submission updateSubmission(Submission submission, Submission with, User active);

    Submission deleteSubmission(Submission submission, User active);

    List<Submission> getSubmissions(User active);

    Submission getSubmissionById(Integer id, User active);

    Integer addNotification(Notification notification, User active);

    Notification updateNotification(Notification notification, Notification with, User active);

    Notification deleteNotification(Notification notification, User active);

    List<Notification> getNotifications(User active);

    Notification getNotificationById(Integer id, User active);

    Integer addSession(Session session, User active);

    Session updateSession(Session session, Session with, User active);

    Session deleteSession(Session session, User active);

    List<Session> getSessions(User active);

    Session getSessionById(Integer id, User active);

    Integer addTopic(Topic topic, User active);

    Topic updateTopic(Topic topic, Topic with, User active);

    Topic deleteTopic(Topic topic, User active);

    List<Topic> getTopics(User active);

    Topic getTopicById(Integer id, User active);

    Integer addTag(Tag tag, User active);

    Tag updateTag(Tag tag, Tag with, User active);

    Tag deleteTag(Tag tag, User active);

    List<Tag> getTags(User active);

    Tag getTagById(Integer id, User active);

}
