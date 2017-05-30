package checker;

import exception.ModelException;
import exception.SystemException;

import java.util.List;

import static utils.Conditional.basedOn;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UniqueChecker<T> implements Checker<T> {

    private List<T> elements;

    public UniqueChecker(List<T> elements) {
        this.elements = elements;
    }


    /**
     * {@inheritDoc}
     *
     * @throws SystemException if the element is not unique in list
     */
    @Override
    public Boolean check(T element) throws SystemException {
        return basedOn(elements.stream().noneMatch(item -> item.equals(element)))
                .orThrow(new ModelException("Error " + element + " is already in our system."));
    }
}
