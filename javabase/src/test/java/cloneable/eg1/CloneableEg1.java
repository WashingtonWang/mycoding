package cloneable.eg1;

/**
 * 实现Cloneable
 * user: xiangyu.wang
 * date: 2019/4/15 16:59
 */
public class CloneableEg1 implements Cloneable {

    private int id;
    private String text;

    public CloneableEg1(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (obj == null){
            return false;
        }

        if (obj.getClass() != getClass()){
            return false;
        }

        CloneableEg1 temp = (CloneableEg1) obj;
        if (id != temp.id){
            return false;
        }

        if (text == null){
            if (temp.text != null){
                return false;
            }
        } else if (!text.equals(temp.text)) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneableEg1 ce1t = new CloneableEg1(1, "I am person");
        CloneableEg1 ce1tClone = (CloneableEg1) ce1t.clone();

        System.out.println(ce1t.getClass() == ce1tClone.getClass());
        System.out.println(ce1t == ce1tClone);
        System.out.println(ce1t.equals(ce1tClone));

    }
}
