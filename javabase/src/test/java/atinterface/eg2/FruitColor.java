package atinterface.eg2;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FruitColor {
    /**
     * 颜色枚举
     */
    enum Color{
        BULE, RED, GREEN
    }

    Color fruitColor() default Color.GREEN;

}
