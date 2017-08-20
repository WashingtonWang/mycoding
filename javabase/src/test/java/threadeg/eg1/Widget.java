package threadeg.eg1;

public class Widget {
    public synchronized void doSomething(){
        System.out.println("Widget: i am parent.");
    }
}
