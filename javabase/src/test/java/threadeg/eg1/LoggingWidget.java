package threadeg.eg1;

public class LoggingWidget extends Widget{
    public synchronized void doSomething(){
        System.out.println("LoggingWidget: i am son.");
        super.doSomething();
    }

    public static void main(String[] args){
        for (int i = 0; i < 1000; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LoggingWidget lw = new LoggingWidget();
                    lw.doSomething();
                    System.out.println(Thread.currentThread().getName());
                }
            }).start();
        }

    }
}
