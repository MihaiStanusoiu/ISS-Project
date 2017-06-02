package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Submission;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@SuppressWarnings("EmptyMethod")
public class ControllerAddSubmissionView
        implements ControllerInterface, ControllerItemInterface<Submission> {

    @Lazy
    @Autowired
    private StageManager manager;

    private Submission submission;

    public void initialize() { }

    @Override
    public void setElement(Submission element) {
        submission = element;
        build();
    }

    private void build() {

    }

}
