package com.mycoding.javabase.annotation.eg2;

import java.util.HashSet;
import java.util.Set;

public class Bigram {

    private final char first;
    private final char sencond;

    public Bigram(char first, char sencond){
        this.first = first;
        this.sencond = sencond;
    }
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Bigram))
            return false;
        Bigram b = (Bigram)o;
        return b.first == first && b.sencond == sencond;
    }

    public int hashCode(){
        return 31 * first +sencond;
    }

    public static void main(String[] args){
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++)
            for (char ch = 'a'; ch <= 'z'; ch++)
                s.add(new Bigram(ch,ch));
        System.out.println(s.size());
    }
}
