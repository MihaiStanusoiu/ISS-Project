package checker

import domain.UserEntity

/**
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

class Convector {

    fun checkGuest(user: UserEntity?): Boolean {
        return user == null
    }

}