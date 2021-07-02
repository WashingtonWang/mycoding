package com.mycoding.javabase.cloneable.eg1;

/**
 * user: xiangyu.wang
 * date: 2019/4/15 17:40
 */
public class DeepCopyEg1 implements Cloneable {
    private String path;
    private InfoDeep infoDeep;

    public DeepCopyEg1(String path, InfoDeep infoDeep) {
        this.path = path;
        this.infoDeep = infoDeep;
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
        DeepCopyEg1 temp = (DeepCopyEg1) obj;
        if (path == null) {
            if (temp.path != null) {
                return false;
            }
        } else if (!path.equals(temp.path)) {
            return false;
        }

        if (infoDeep == null) {
            if (temp.infoDeep != null) {
                return false;
            }
        } else if (!infoDeep.equals(temp.infoDeep)) {
            return false;
        }

        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepCopyEg1 dce1 = (DeepCopyEg1) super.clone();
        dce1.infoDeep = (InfoDeep) dce1.infoDeep.clone();
        return dce1;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        InfoDeep id = new InfoDeep(1L, "Hello, world");
        DeepCopyEg1 dce1 = new DeepCopyEg1("c:", id);
        DeepCopyEg1 dce1Clone = (DeepCopyEg1) dce1.clone();

        System.out.println(dce1.getClass() == dce1Clone.getClass());
        System.out.println(dce1 == dce1Clone);
        System.out.println(dce1.equals(dce1Clone));
        System.out.println(dce1.infoDeep.getClass() == dce1Clone.infoDeep.getClass());
        System.out.println(dce1.infoDeep == dce1Clone.infoDeep);
        System.out.println(dce1.infoDeep.equals(dce1Clone.infoDeep));
    }
}

class InfoDeep implements Cloneable {
    private Long id;
    private String name;

    public InfoDeep(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
