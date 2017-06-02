package service;

import domain.EditionEntity;
import transfarable.*;

import java.rmi.RemoteException;
import java.util.List;

public interface EditionService extends Service<Edition, Integer, EditionEntity> {

    List<Session> getAllSessionsOf(Edition edition) throws RemoteException;

    List<User> getAllMembersOf(Edition edition) throws RemoteException;

    List<User> getPcMembersOf(Edition edition) throws RemoteException;

    List<User> getCoChairsOf(Edition edition) throws RemoteException;

    User getChair(Edition edition) throws RemoteException;

    Edition addMemberToEdition(Edition edition, User user, MemberRoleTransferable memberRole) throws RemoteException;

    Edition deleteMemberOfEdition(Edition edition, User user) throws RemoteException;

    Edition addSessionToEdition(Edition edition, Session session) throws RemoteException;

    Edition deleteSessionOfEdition(Edition edition, Session session) throws RemoteException;

    Edition addSubmissionToEdition(Edition edition, Submission submission) throws RemoteException;

    Edition deleteSubmissionOfEdition(Edition edition, Submission submission) throws RemoteException;

}
