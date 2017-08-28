package genericeg.eg1;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;


/**
 * 本类打印的是什么结果
 */
public class Favorites {

    private Map<Class<?>, Object> favorites  = new HashMap<Class<?>,Object>();


    public <T> void putFavorites(Class<T> type,T instance){
        if (type == null)
            throw new NullPointerException("Type is null");
        favorites.put(type, type.cast(instance));
    }

    public <T> T getFavorites(Class<T> type){
        return type.cast(favorites.get(type));
    }

    static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName){
        Class<?> annotationType = null;
        try{
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception e){
            throw new IllegalArgumentException(e);
        }
        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }

}
