package service;


import domain.TopicEntity;
import transfarable.Submission;
import transfarable.Topic;

import java.rmi.RemoteException;
import java.util.List;

public interface TopicService extends Service<Topic, Integer, TopicEntity> {
    List<Submission> getSubmissionsFromTopic(Topic topic) throws RemoteException;
}
