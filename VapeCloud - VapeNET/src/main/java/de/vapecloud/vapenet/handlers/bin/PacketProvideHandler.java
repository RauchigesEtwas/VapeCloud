package de.vapecloud.vapenet.handlers.bin;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/12:01
 * Created by Robin B. (RauchigesEtwas)
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PacketProvideHandler {
    int priority() default 50;
}
