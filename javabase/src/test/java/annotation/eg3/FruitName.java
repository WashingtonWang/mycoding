package annotation.eg3;

import java.lang.annotation.*;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/7 13:41
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
