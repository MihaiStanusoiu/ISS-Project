package service;

import domain.EditionEntity;
import domain.MemberRole;
import transfarable.Edition;
import transfarable.Session;
import transfarable.Submission;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.List;

public interface EditionService extends Service<Edition, Integer, EditionEntity> {

    List<User> getAllMembersOf(Edition edition) throws RemoteException;

    List<User> getPcMembersOf(Edition edition) throws RemoteException;

    List<User> getCoChairsOf(Edition edition) throws RemoteException;

    User getChair(Edition edition) throws RemoteException;

    Edition addMemberToEdition(Edition edition, User user, MemberRole memberRole) throws RemoteException;

    Edition deleteMemberOfEdition(Edition edition, User user) throws RemoteException;

    Edition addSessionToEdition(Edition edition, Session session) throws RemoteException;

    Edition deleteSessionOfEdition(Edition edition, Session session) throws RemoteException;

    Edition addSubmissionToEdition(Edition edition, Submission submission) throws RemoteException;

    Edition deleteSubmissionOfEdition(Edition edition, Submission submission) throws RemoteException;

}
