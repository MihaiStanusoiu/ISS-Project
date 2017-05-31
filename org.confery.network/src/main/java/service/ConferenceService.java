package service;


import domain.ConferenceEntity;
import transfarable.Conference;
import transfarable.Edition;

import java.rmi.RemoteException;

public interface ConferenceService extends Service<Conference, Integer, ConferenceEntity> {

    Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException;

}
