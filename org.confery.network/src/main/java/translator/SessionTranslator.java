package translator;

import domain.SessionEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.Session;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionTranslator implements GenericTranslator<SessionEntity, Session>{

    @Override
    public Session translate(@NotNull SessionEntity session) {
        return new Session(session.getId(), session.getName(), session.getStartDate(),
                session.getEndDate(), session.getLocation(), session.getBio(), session.getSeats());
    }

    @Override
    public SessionEntity translate(@NotNull Session session) {
        return new SessionEntity(session.getId(), session.getName(), session.getStartDate(),
                session.getEndDate(), session.getLocation(), session.getBio(), session.getSeats());
    }

}
