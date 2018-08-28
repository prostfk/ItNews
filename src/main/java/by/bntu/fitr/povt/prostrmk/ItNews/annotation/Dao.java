package by.bntu.fitr.povt.prostrmk.ItNews.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Dao {

    @AliasFor(annotation = Component.class)
    String value() default "";

}
