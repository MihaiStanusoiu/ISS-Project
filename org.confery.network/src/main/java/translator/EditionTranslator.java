package translator;

import domain.EditionEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class EditionTranslator {

    public static EditionEntity translate(Edition edition) {
        return new EditionEntity(edition.getId(), edition.getStartDate(),
                edition.getEndDate(), edition.getLocation(), edition.getBio(),
                edition.getAbstractDeadline(), edition.getPaperDeadline(),
                edition.getEvaluationDeadline(), edition.getBiddingDeadline());
    }

    public static Edition translate(EditionEntity edition) {
        return new Edition(edition.getId(), edition.getStartDate(),
                edition.getEndDate(), edition.getLocation(), edition.getBio(),
                edition.getAbstractDeadline(), edition.getPaperDeadline(),
                edition.getEvaluationDeadline(), edition.getBiddingDeadline());
    }

}
