package translator;

import domain.SessionEntity;
import transferable.Session;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionTranslator {

    public static Session translate(SessionEntity session) {
        return new Session(session.getId(), session.getName(), session.getStartDate(),
                session.getEndDate(), session.getLocation(), session.getBio(), session.getSeats());
    }

    public static SessionEntity translate(Session session) {
        return new SessionEntity(session.getId(), session.getName(), session.getStartDate(),
                session.getEndDate(), session.getLocation(), session.getBio(), session.getSeats());
    }

}
