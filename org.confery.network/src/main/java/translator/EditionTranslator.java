package translator;

import domain.EditionEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.Edition;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class EditionTranslator implements GenericTranslator<EditionEntity, Edition>{

    @Override
    public EditionEntity translate(@NotNull Edition edition) {
        return new EditionEntity(edition.getId(), edition.getStartDate(),
                edition.getEndDate(), edition.getLocation(), edition.getBio(),
                edition.getAbstractDeadline(), edition.getPaperDeadline(),
                edition.getEvaluationDeadline(), edition.getBiddingDeadline());
    }

    @Override
    public Edition translate(@NotNull EditionEntity edition) {
        return new Edition(edition.getId(), edition.getStartDate(),
                edition.getEndDate(), edition.getLocation(), edition.getBio(),
                edition.getAbstractDeadline(), edition.getPaperDeadline(),
                edition.getEvaluationDeadline(), edition.getBiddingDeadline());
    }

}
