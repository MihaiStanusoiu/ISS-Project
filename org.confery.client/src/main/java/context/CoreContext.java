package context;

import method.SimpleMethod;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Component;
import utils.Conditional;
import utils.Try;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class CoreContext {

    private Reflections reflections;
    private Set<Field> fields;
    private Boolean condition;
    private ContextType type;
    private Object object;
    private Object[] parameters;
    private String methodName;
    private Class<?>[] types;

    public CoreContext() {
        reflections = new Reflections(ConfigurationBuilder.build()
                .setUrls(ClasspathHelper.forPackage("controller"))
                .setScanners(new FieldAnnotationsScanner()));
    }

    public void init() {
        condition = Boolean.TRUE;
        fields = reflections.getFieldsAnnotatedWith(Context.class);
    }

    @SuppressWarnings("unused")
    public CoreContext basedOn(Boolean condition) {
        this.condition = condition;
        return this;
    }

    public CoreContext forType(ContextType type) {
        this.type = type;
        return this;
    }

    public CoreContext in(Object object) {
        this.object = object;
        return this;
    }

    @SuppressWarnings("unused")
    public CoreContext perform(String methodName, Class<?>... paramTypes) {
        this.methodName = methodName;
        this.types = paramTypes;
        return this;
    }

    public CoreContext run(SimpleMethod<Object> method) {
        Conditional.basedOn(condition).runTrue(() -> fields.stream().filter(this::isType).filter(this::inObject)
                .forEach(field -> method.accept(getObject(field, object))));
        return this;
    }

    public CoreContext or(SimpleMethod<Object> method) {
        Conditional.basedOn(!condition).runTrue(() -> fields.stream().filter(this::isType).filter(this::inObject)
                .forEach(field -> method.accept(getObject(field, object))));
        return this;
    }

    @SuppressWarnings("unused")
    public CoreContext withParameters(Object... parameters) {
        this.parameters = parameters;
        return this;
    }

    private void handleExceptions(Exception exception) {
        System.out.print(exception.getMessage());
    }

    private Object getObject(Field field, Object object) {
        return runFunction(field::get, object).orHandle(this::handleExceptions);
    }

    private Method getMethod(Field field, String methodName, Class<?>... paramTypes) {
        return runFunction(field.getType()::getMethod, methodName, paramTypes).orHandle(this::handleExceptions);
    }

    private Boolean isType(Field field) {
        return Arrays.stream(field.getAnnotation(Context.class).value()).anyMatch(value -> value.equals(type));
    }

    private void execute(Field field, String methodName, Class<?>... types) {
        Try.runMethod(getMethod(field, methodName, types)::invoke, getObject(field, object), parameters)
                .orHandle(this::handleExceptions);
    }

    private Boolean inObject(Field field) {
        return field.getDeclaringClass().equals(object.getClass());
    }

    @SuppressWarnings("unused")
    public void run() {
        Conditional.basedOn(condition).runTrue(() -> fields.stream().filter(this::isType).filter(this::inObject)
                .forEach(field -> execute(field, methodName, types)));
    }

}
