
package domain;

import exception.ModelException;
import repository.RepositoryInterface;

import static utils.Try.runFunction;

/**
 * Tested: True
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConfigurationSessionFactory {

    private RepositoryInterface<ConfigurationSessionMemberEntity, Integer> configurations;

    /**
     * @param configurations The configurations's repository -- to get from database the correct configuration.
     */
    public ConfigurationSessionFactory(RepositoryInterface<ConfigurationSessionMemberEntity, Integer> configurations) {
        this.configurations = configurations;
    }

    /**
     * @param configuration The given configuration
     * @return The given configuration from database
     */
    public ConfigurationSessionMemberEntity get(ConfigurationSessionMemberEntity configuration) {
        return configurations.getAll()
                .stream()
                .filter(item -> item.toString().equals(configuration.toString()))
                .findFirst().orElseGet(() -> {
            runFunction(configurations::add, configuration);
            return configuration;
        });
    }

    /**
     * Returns a configuration based on a :MemberRole: role
     * @param role The target role
     * @return The wanted configuration
     * @throws ModelException If the role is not supported
     */
    public ConfigurationSessionMemberEntity getConfiguration(MemberRole role)
            throws ModelException {
        switch (role) {
            case SESSION_CHAIR:
                return get(new ConfigurationSessionMemberEntity(true, false, false));
            case SESSION_SPEAKER:
                return get(new ConfigurationSessionMemberEntity(false, true, false));
            case SESSION_LISTENER:
                return get(new ConfigurationSessionMemberEntity(false, false, true));
            default:
                throw new ModelException("Unsupported Member Session Role!");
        }
    }
}
