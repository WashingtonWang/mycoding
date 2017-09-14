package atinterface.eg2;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FruitProvider {
    /**
     * 供应商编号
     * @return int
     */
    int id() default -1;

    /**
     * 供应商名称
     * @return String
     */
    String name() default "";

    /**
     * 供应商地址
     * @return String
     */
    String address() default "";
}
