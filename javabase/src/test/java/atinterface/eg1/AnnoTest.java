package atinterface.eg1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnoTest {

    public static void main(String[] args) throws ClassNotFoundException{
        Class c = Class.forName("atinterface.eg1.Anno");
        Method[] methods = c.getMethods();
        boolean flag = c.isAnnotationPresent(FirstAnnotation.class);
        if (flag){
            FirstAnnotation firstAnnotation = (FirstAnnotation) c.getAnnotation(FirstAnnotation.class);
            System.out.println("First annotation: "+ firstAnnotation.value());
        }

        List<Method> list = new ArrayList<>();
        for (int i = 0; i < methods.length; i++){
            list.add(methods[i]);
        }

        for (Method m : list){
            SecondAnnotation secondAnnotation = m.getAnnotation(SecondAnnotation.class);
            if (secondAnnotation == null){
                continue;
            }
            System.out.println("second annotation's\nname:\t" + secondAnnotation.name()
                    + "\nurl:\t" + secondAnnotation.url());
        }

        List<Field> fieldList = new ArrayList<>();
        for (Field f : c.getDeclaredFields()){
            Kitto k = f.getAnnotation(Kitto.class);
            System.out.println("----kitto anno: " + k.value());
        }
    }
}
