package translator;

import domain.ConferenceEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.Conference;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class ConferenceTranslator implements GenericTranslator<ConferenceEntity, Conference> {

    @Override
    public ConferenceEntity translate(@NotNull Conference conference) {
        return new ConferenceEntity(conference.getId(), conference.getName(), conference.getAcronym());
    }

    @Override
    public Conference translate(@NotNull ConferenceEntity conference) {
        return new Conference(conference.getId(), conference.getName(), conference.getAcronym());
    }

}
