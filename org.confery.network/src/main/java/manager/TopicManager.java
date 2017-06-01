package manager;

import checker.TopicPermissionChecker;
import domain.TopicEntity;
import protocol.TopicProtocol;
import service.TopicService;
import transfarable.Submission;
import transfarable.Topic;
import translator.SubmissionTranslator;
import translator.TopicTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TopicManager extends GenericManager<Topic, Integer, TopicEntity> implements TopicService {

    private final SubmissionTranslator submissionTranslator;

    public TopicManager(TopicProtocol model) throws RemoteException {
        super(model);
        super.translator = new TopicTranslator();
        super.checker = new TopicPermissionChecker();
        submissionTranslator = new SubmissionTranslator();
    }

    @Override
    public List<Submission> getSubmissionsFromTopic(Topic topic) throws RemoteException {
        return runFunction(model::getElementById, topic.getId()).orThrow(thrower)
                .getSubmissions().stream()
                .map(submissionTranslator::translate)
                .collect(Collectors.toList());
    }
}
