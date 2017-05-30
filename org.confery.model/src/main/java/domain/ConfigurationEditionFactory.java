
package domain;

import exception.ModelException;
import repository.RepositoryInterface;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class ConfigurationEditionFactory {

    private RepositoryInterface<ConfigurationEditionMemberEntity, Integer> configurations;

    /**
     * @param configurations The configurations's repository
     *                       -- in order to take the configuration from database.
     */
    public ConfigurationEditionFactory(RepositoryInterface<ConfigurationEditionMemberEntity, Integer> configurations) {
        this.configurations = configurations;
    }

    /**
     * Returns the configuration from database, or adds the given configuration to database and returns it.
     *
     * @param configuration The given configuration
     * @return The given configuration (from database // because we need the id of the configuration)
     */
    public ConfigurationEditionMemberEntity get(ConfigurationEditionMemberEntity configuration) {
        return configurations.getAll()
                .stream()
                .filter(item -> item.toString().equals(configuration.toString()))
                .findFirst().orElseGet(() -> {
                    // if the configuration is not already in the database, we will add it
                    runFunction(configurations::add, configuration);
                    return configuration;
                });
    }

    /**
     * Returns the configuration for a specific MemberRole :role:
     *
     * @param role The target role
     * @return The wanted configuration
     * @throws ModelException If the role is not supported.
     */
    public ConfigurationEditionMemberEntity getConfiguration(MemberRole role)
            throws ModelException {
        switch (role) {
            case EDITION_CHAIR:
                return get(new ConfigurationEditionMemberEntity(true, false, false));
            case EDITION_CO_CHAIR:
                return get(new ConfigurationEditionMemberEntity(false, true, false));
            case EDITION_PC_MEMBER:
                return get(new ConfigurationEditionMemberEntity(false, false, true));
            default:
                throw new ModelException("Unsupported Member Session Role!");
        }
    }
}
