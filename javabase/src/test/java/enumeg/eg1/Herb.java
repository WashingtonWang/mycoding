package enumeg.eg1;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Herb {

    public enum Type{ANNUAL, PERENNIAL, BIENNIAL}

    private final String name;
    private final Type type;

    Herb(String name, Type type){
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString(){
        return name;
    }

    public static void main(String[] args){
        Herb h1 = new Herb("月季花",Type.BIENNIAL);
        Herb h2 = new Herb("玫瑰花",Type.BIENNIAL);
        Herb h3 = new Herb("夜来香花",Type.ANNUAL);
        Herb h4 = new Herb("韭菜花",Type.ANNUAL);
        Herb h5 = new Herb("白菜花",Type.PERENNIAL);
        Herb h6 = new Herb("美丽花",Type.PERENNIAL);
        Herb[] garden = {h1,h2,h3,h4,h5,h6};
        Map<Type, Set<Herb>> herbByType = new EnumMap<Type, Set<Herb>>(Herb.Type.class);
        for (Herb.Type t : Herb.Type.values()){
            herbByType.put(t, new HashSet<>());
        }
        for (Herb h : garden){
            herbByType.get(h.type).add(h);
        }
        System.out.println(herbByType);
    }

}
