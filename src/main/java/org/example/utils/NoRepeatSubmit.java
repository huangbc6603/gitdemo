package org.example.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Derek.huang on 2023/4/11 14:49.
 */
@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface NoRepeatSubmit {

    //名称，如果不给就是要默认的
    String name() default "name";


}
