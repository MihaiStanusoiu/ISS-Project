package service;


import domain.SubmissionEntity;
import transfarable.Submission;
import transfarable.Tag;
import transfarable.Topic;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.List;

public interface SubmissionService extends Service<Submission, Integer, SubmissionEntity> {
    public List<User> getReviewers(Submission edition) throws RemoteException;

    public List<User> getAuthors(Submission edition) throws RemoteException;

    public User getOwner(Submission edition) throws RemoteException;

    public List<Topic> getTopics(Submission edition) throws RemoteException;

    public List<Tag> getTags(Submission edition) throws RemoteException;

    public List<User> getBindingReviewers(Submission edition) throws RemoteException;

    public List<User> getAllowedReviewers(Submission edition) throws RemoteException;

    public List<User> getRejectedReviewers(Submission edition) throws RemoteException;

    public Submission addTagTo(Submission submission, Tag tag) throws RemoteException;

    public Submission addTopicTo(Submission submission, Topic topic) throws RemoteException;

    public Submission addAuthorTo(Submission submission, User author) throws RemoteException;

    public Submission addOwnerTo(Submission submission, User owner) throws RemoteException;

    public Submission removeAuthorFrom(Submission submission, User author) throws RemoteException;

    public Submission removeTagFrom(Submission submission, Tag tag) throws RemoteException;

    public Submission removeTopicFrom(Submission submission, Topic topic) throws RemoteException;

}
