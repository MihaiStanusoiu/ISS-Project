package service;

import domain.ConferenceEntity;
import transferable.Conference;

public interface ConferenceService extends ServiceInterface {
    void addConference(Conference conference);
}
