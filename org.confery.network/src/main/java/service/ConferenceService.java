package service;

import domain.ConferenceEntity;
import transferable.Conference;
import transferable.Edition;

import java.rmi.RemoteException;

public interface ConferenceService extends Service<Conference, Integer> {
    Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException;
}
