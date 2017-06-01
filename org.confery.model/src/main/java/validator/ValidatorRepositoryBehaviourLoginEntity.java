package validator;

import domain.LoginEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Created by Vlad on 6/1/2017.
 */
public class ValidatorRepositoryBehaviourLoginEntity extends ValidatorRepositoryBehaviour<LoginEntity>  {
    @Override
    public List<String> check(LoginEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Logged in user is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Logged id is NULL!");
        basedOn(Objects.isNull(object.getIp()))
                .runTrue(accumulator::add, "Logged in user's ip is NULL!");
        basedOn(Objects.isNull(object.getId_user()))
                .runTrue(accumulator::add, "Logged in user's ip is NULL!");
        return accumulator;
    }
}
