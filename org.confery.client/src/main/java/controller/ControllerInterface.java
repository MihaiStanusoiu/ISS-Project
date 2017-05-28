package controller;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ControllerInterface extends Serializable {

    /**
     * Effect: Initialises the view by fetching data & setting up UI constraints.
     *
     * @implNote status: We implement constraints where only
     * if the necessary constraints are not available in fxml [file/scene-builder].
     */
    void initialize() throws RemoteException;

}
