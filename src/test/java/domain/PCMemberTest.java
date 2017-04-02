package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PCMemberTest {
    private PCMember pcMember;

    @Before
    public void setUp() throws Exception {
        pcMember = new PCMember(1, 1, 1);
    }

    @Test
    public void getType() throws Exception {
        assertTrue(pcMember.getType().equals(UserType.PC_MEMBER));
    }

    @Test
    public void getPermissions() throws Exception {
        assertTrue(pcMember.getPermissions().contains(Permission.BID));
    }

}