package org.example.utils;

import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 *  浅克隆工具
 */
public class ConvertUtils {

    private ConvertUtils(){}
    /**
     *
     * @param source 源对象
     * @param target 目标对象
     * @param ignores 忽略是属性
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> T covert(S source,T target,String... ignores ){
        Objects.requireNonNull(target);
        Objects.requireNonNull(source);
        BeanUtils.copyProperties(source, target ,ignores);
        return target;
    }

    /**
     * 推荐使用
     *
     * @param source 源对象
     * @param clazz 目标对象类型
     * @param ignores 忽略是属性
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> T covert(S source,Class<T> clazz,String... ignores ){
        T target;
        try {
            target = covert(source,clazz.getDeclaredConstructor().newInstance(),ignores);
        } catch (Exception e) {
            target = null;
        }
        return target;
    }

    /**
     *
     * @param source 源对象
     * @param clazz 目标对象类型
     * @param ignores 忽略是属性
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> List<T> covert(List<S> source,Class<T> clazz,String... ignores){
        if(source == null || source.isEmpty()){
            return Collections.emptyList();
        }
        return source.stream().map(s->covert(s, clazz, ignores)).collect(Collectors.toList());
    }

    /**
     *
     * @param source 源对象
     * @param clazz 目标对象类型
     * @param ignores 忽略是属性
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> Set<T> covert(Set<S> source,Class<T> clazz,String... ignores){
        if(source == null || source.isEmpty()){
            return Collections.emptySet();
        }
        return source.stream().map(s->covert(s, clazz, ignores)).collect(Collectors.toSet());
    }


}