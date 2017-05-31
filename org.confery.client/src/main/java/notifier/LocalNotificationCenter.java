package notifier;

import java.util.Observable;

/**
 * We use this class to notify the controllers about update events. [like login]
 * <p>
 *     Although we could use a basic Observable extension for the singleton notification center
 *     from Spring Configuration, this class will allow us to extend it's functions later on.
 *     -- in case we need to do something more specific when we notify the client's controllers.
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class LocalNotificationCenter extends Observable { }
