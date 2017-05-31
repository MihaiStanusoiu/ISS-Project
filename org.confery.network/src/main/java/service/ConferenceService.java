package service;


import domain.ConferenceEntity;
import transfarable.Conference;
import transfarable.Edition;

import java.rmi.RemoteException;
import java.util.List;

public interface ConferenceService extends Service<Conference, Integer, ConferenceEntity> {

    Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException;

    List<Edition> getEditionsOf(Conference conference) throws RemoteException;

}
