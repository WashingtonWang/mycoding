package extendseg.eg2;

/**
 * 用继承进行设计 《java编程思想》 P.165
 * user: xiangyu.wang
 * date: 2017/11/11 13:31
 */
public class Transmogrify {
    public static void main(String[] args) {
        Stage stage = new Stage();
        stage.performPlay();
        stage.change();
        stage.performPlay();
    }
}

class Actor{
    public void act(){ }
}

class HappyActor extends Actor{
    public void act(){
        System.out.println("HappyActor");
    }
}

class SadActor extends Actor{
    public void act(){
        System.out.println("SadActor");
    }
}

class Stage{
    private Actor actor = new HappyActor();
    public void change(){
        actor = new SadActor();
    }
    public void performPlay(){
        actor.act();
    }
}




