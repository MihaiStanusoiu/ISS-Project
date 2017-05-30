package service;


import transfarable.Conference;
import transfarable.Edition;

import java.rmi.RemoteException;

public interface ConferenceService extends Service<Conference, Integer> {

    Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException;

}
