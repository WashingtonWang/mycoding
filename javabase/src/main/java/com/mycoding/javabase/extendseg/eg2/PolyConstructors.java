package com.mycoding.javabase.extendseg.eg2;

/**
 * java继承顺序初始化问题  《java编程思想》 p.163
 * user: xiangyu.wang
 * date: 2017/11/11 10:40
 */
public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}

class Glyph {
    void draw(){
        System.out.println("Glyph.draw()");
    }
    Glyph(){
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;
    RoundGlyph(int r){
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph().radius = " + radius);
    }
    void draw(){
        System.out.println("RoundGlyph.draw().radius = " + radius);
    }
}
