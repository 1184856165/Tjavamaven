package com.Tjise.utils;

public class MyUtils {
    /**
     * 获取指定Class的实例对象
     *
     * @param clazz Class
     * @return Class的实例对象
     */
    public static <T> T getNewInstance(Class<T> clazz) {
        // 默认会返回值
        T t = null;
        try {
            // 使用反射创建这个Class的实例对象
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
