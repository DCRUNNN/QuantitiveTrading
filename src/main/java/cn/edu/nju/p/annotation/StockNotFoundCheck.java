package cn.edu.nju.p.annotation;

import java.lang.annotation.*;

/**
 * methods with this annotation are implied to stock not found exception check
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StockNotFoundCheck {

    /**
     * to be completed
     */
}
