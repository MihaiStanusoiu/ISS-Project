package manager;

import checker.TagPermissionChecker;
import domain.TagEntity;
import protocol.LoginProtocol;
import protocol.TagProtocol;
import service.TagService;
import transfarable.Submission;
import transfarable.Tag;
import translator.SubmissionTranslator;
import translator.TagTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TagManager extends GenericManager<Tag, Integer, TagEntity> implements TagService {

    private final SubmissionTranslator submissionTranslator;

    public TagManager(TagProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        this.submissionTranslator = new SubmissionTranslator();
        this.translator = new TagTranslator();
        this.checker = new TagPermissionChecker();
    }

    @Override
    public List<Submission> getSubmissionsFromTag(Tag tag) throws RemoteException{
        return runFunction(model::getElementById, tag.getId()).orThrow(thrower)
                .getSubmissions().stream()
                .map(submissionTranslator::translate)
                .collect(Collectors.toList());
    }
}
