package translator;

import domain.MemberRole;
import org.jetbrains.annotations.NotNull;
import transfarable.MemberRoleTransferable;

/**
 * Tested: False
 *
 * @author Tanasie Luiza
 * @version 1.0
 */
public class MemberRoleTranslator {

    public MemberRoleTransferable translate(@NotNull MemberRole memberRole) {
        switch(memberRole){
            case EDITION_CHAIR:
                return MemberRoleTransferable.EDITION_CHAIR;
            case EDITION_CO_CHAIR:
                return MemberRoleTransferable.EDITION_CO_CHAIR;
            case EDITION_PC_MEMBER:
                return MemberRoleTransferable.EDITION_PC_MEMBER;
            case SESSION_CHAIR:
                return MemberRoleTransferable.SESSION_CHAIR;
            case SESSION_LISTENER:
                return MemberRoleTransferable.SESSION_LISTENER;
            case SESSION_SPEAKER:
                return MemberRoleTransferable.SESSION_SPEAKER;
            default:
                return null;
        }
    }


    public MemberRole translate(@NotNull MemberRoleTransferable memberRole) {
        switch(memberRole){
            case EDITION_CHAIR:
                return MemberRole.EDITION_CHAIR;
            case EDITION_CO_CHAIR:
                return MemberRole.EDITION_CO_CHAIR;
            case EDITION_PC_MEMBER:
                return MemberRole.EDITION_PC_MEMBER;
            case SESSION_CHAIR:
                return MemberRole.SESSION_CHAIR;
            case SESSION_LISTENER:
                return MemberRole.SESSION_LISTENER;
            case SESSION_SPEAKER:
                return MemberRole.SESSION_SPEAKER;
            default:
                return null;
        }
    }
}
