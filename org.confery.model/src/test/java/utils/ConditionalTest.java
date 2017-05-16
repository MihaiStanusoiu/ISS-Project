package utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.stream.IntStream;

import static utils.Conditional.basedOn;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConditionalTest {

    @Test
    public void isRunningEmptyMethodBasedOnCondition() throws Exception {
        basedOn(Objects.equals(1, 1))
                .runTrue(() -> System.out.print("Test True"))
                .runFalse(() -> System.out.print("Test False"));
        basedOn(Objects.equals(1, 2))
                .runTrue(() -> System.out.print("Test True"))
                .runFalse(() -> System.out.print("Test False"));
    }

    @Test
    public void isRunningMethodBasedOnCondition() throws Exception {
        basedOn(Objects.equals(1, 1))
                .runTrue(System.out::print, "Test True")
                .runFalse(System.out::print, "Test False");
        basedOn(Objects.equals(1, 2))
                .runTrue(System.out::print, "Test True")
                .runFalse(System.out::print, "Test False");
    }

    @Test
    public void isRunningBiMethodBasedOnCondition() throws Exception {
        basedOn(Objects.equals(1, 1))
                .runTrue((message, item) -> Assert.assertTrue("TestTrue1".equals(message + item)), "TestTrue", 1)
                .runFalse((message, item) -> Assert.assertTrue("TestFalse2".equals(message + item)), "TestFalse", 1);
        basedOn(Objects.equals(1, 2))
                .runTrue((message, item) -> Assert.assertTrue("TestTrue1".equals(message + item)), "TestTrue", 1)
                .runFalse((message, item) -> Assert.assertTrue("TestFalse2".equals(message + item)), "TestFalse", 2);
    }

    @Test
    public void isRunningWithStream() throws Exception {
        IntStream.range(1, 100).forEach(item -> basedOn(item % 2 == 0)
                .runTrue(System.out::print, item)
                .runFalse(element -> System.out.print("."), item));
    }
}