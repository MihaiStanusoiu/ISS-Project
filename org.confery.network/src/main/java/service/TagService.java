package service;


import domain.TagEntity;
import transfarable.Submission;
import transfarable.Tag;

import java.rmi.RemoteException;
import java.util.List;

public interface TagService extends Service<Tag, Integer, TagEntity> {
    List<Submission> getSubmissionsFromTag(Tag tag) throws RemoteException;
}
