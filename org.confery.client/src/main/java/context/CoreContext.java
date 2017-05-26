package context;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class CoreContext {

    private Reflections reflections;

    public CoreContext() {
        //reflections = new Reflections();
    }

    public void start() {
//        Set<Class<?>> classes =  reflections.getTypesAnnotatedWith(ContextClass.class);
//        Set<Field> fields = reflections.getFieldsAnnotatedWith(Context.class);
//        fields.forEach(System.out::println);

//        Field[] fields = controller.getClass().getFields();
//
//
//        Arrays.stream(fields).filter(field -> field.getAnnotation(Context.class) != null)
//                .forEach(field -> {
//                    System.out.print("Mere");
//                    Object object = runFunction(field::get, controller).orHandle(System.out::print);
//                    Method method = runFunction(((String name, Class<?> type) -> field.getType()
//                            .getMethod(name, type)), "setVisible", boolean.class).orHandle(System.out::print);
//                    runFunction(((ThrowBiFunction<Object, Boolean, Object, ReflectiveOperationException>) method::invoke),
//                            object, Boolean.FALSE).orHandle(System.out::print);
//                });
    }
}
