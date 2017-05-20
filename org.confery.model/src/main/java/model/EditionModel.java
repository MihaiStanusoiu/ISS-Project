package model;

import database.DatabaseLoaderInterface;
import domain.EditionEntity;
import protocol.EditionProtocol;

/**
 * Tested: True
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionModel
        extends Model<EditionEntity, Integer>
        implements EditionProtocol {

    public EditionModel(DatabaseLoaderInterface loader) {
        super(EditionEntity.class, loader);
    }

}
