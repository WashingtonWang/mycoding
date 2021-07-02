package com.mycoding.javabase.annotation.eg1;

import java.lang.reflect.*;

public class RunTests {

    public static void main(String[] args) throws Exception {
        int tess = 0;
        int passed = 0;
        Class testClass = Class.forName("com.mycoding.javabase.annotation.eg1.Sample2");
        for (Method m : testClass.getDeclaredMethods()){
            if (m.isAnnotationPresent(ExceptionTest.class)){
                tess++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable wrappedExc){
                    Throwable exc = wrappedExc.getCause();
                    Class<? extends Exception>[] excTypes = m.getAnnotation(ExceptionTest.class).value();
                    int oldPassed = passed;
                    for (Class<? extends Exception> excType : excTypes){
                        if (excType.isInstance(exc)){
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed)
                        System.out.printf("Test %s failed: excepted %s %n",m, exc);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n",passed, tess - passed);
    }
}
