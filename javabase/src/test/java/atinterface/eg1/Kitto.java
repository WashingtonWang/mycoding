package atinterface.eg1;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Kitto {
    String value() default "kitto";
}
