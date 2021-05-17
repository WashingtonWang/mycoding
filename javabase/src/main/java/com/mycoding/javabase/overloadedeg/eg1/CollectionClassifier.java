package com.mycoding.javabase.overloadedeg.eg1;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.math.BigInteger;
import java.util.*;

public class CollectionClassifier {

    public static String classifier(Set<?> s){
        return "Set";
    }

    public static String classifier(List<?> lst){
        return "List";
    }

    public static String classifier(Collection<?> c){
        return "Unknown Collection";
    }

    public static void main(String[] args){
        Collection<?>[] collections = {new HashSet<StringList>(), new ArrayList<BigInteger>(), new HashMap<String, String>().values()};
        for (Collection<?> c : collections){
            System.out.println(classifier(c));
        }
    }
}
