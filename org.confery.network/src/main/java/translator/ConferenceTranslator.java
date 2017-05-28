package translator;

import domain.ConferenceEntity;
import transfarable.Conference;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConferenceTranslator {

    public static ConferenceEntity translate(Conference conference) {
        return new ConferenceEntity(conference.getId(), conference.getName(), conference.getAcronym());
    }

    public static Conference translate(ConferenceEntity conference) {
        return new Conference(conference.getId(), conference.getName(), conference.getAcronym());
    }

}
