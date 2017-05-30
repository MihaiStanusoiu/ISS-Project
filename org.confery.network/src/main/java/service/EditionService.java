package service;

import domain.EditionEntity;
import domain.MemberRole;
import transferable.Edition;
import transferable.Session;
import transferable.Submission;
import transferable.User;

import java.rmi.RemoteException;

public interface EditionService extends Service<Edition, Integer> {

    Edition addMemberToEdition(Edition edition, User user, MemberRole memberRole) throws RemoteException;

    Edition deleteMemberOfEdition(Edition edition, User user) throws RemoteException;

    Edition addSessionToEdition(Edition edition, Session session) throws RemoteException;

    Edition deleteSessionOfEdition(Edition edition, Session session) throws RemoteException;

    Edition addSubmissionToEdition(Edition edition, Submission submission) throws RemoteException;

    Edition deleteSubmissionOfEdition(Edition edition, Submission submission) throws RemoteException;

}
