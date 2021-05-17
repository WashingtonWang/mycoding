package com.mycoding.javabase.cloneable.eg1;

/**
 * user: xiangyu.wang
 * date: 2019/4/15 17:18
 */
public class ShallowCopyEg1 implements Cloneable {

    private String path;
    private InfoShallow infoShallow;

    public ShallowCopyEg1(String path, InfoShallow infoShallow) {
        this.path = path;
        this.infoShallow = infoShallow;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ShallowCopyEg1 temp = (ShallowCopyEg1) obj;

        if (path == null) {
            if (temp.path != null) {
                return false;
            }
        } else if (!path.equals(temp.path)) {
            return false;
        }

        if (infoShallow == null) {
            if (temp.infoShallow != null) {
                return false;
            }
        } else if (!infoShallow.equals(temp.infoShallow)) {
            return false;
        }

        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException{
        InfoShallow infoShallow = new InfoShallow(1L, "hello world");
        ShallowCopyEg1 sce1 = new ShallowCopyEg1("c:", infoShallow);
        ShallowCopyEg1 sce1Clone = (ShallowCopyEg1) sce1.clone();
        System.out.println(sce1.getClass() == sce1Clone.getClass());
        System.out.println(sce1 == sce1Clone);
        System.out.println(sce1.equals(sce1Clone));
        System.out.println(sce1.infoShallow.getClass() == sce1Clone.infoShallow.getClass());
        System.out.println(sce1.infoShallow == sce1Clone.infoShallow);
        System.out.println(sce1.infoShallow.equals(sce1Clone.infoShallow));
    }
}

class InfoShallow {
    private Long id;
    private String name;

    public InfoShallow(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

