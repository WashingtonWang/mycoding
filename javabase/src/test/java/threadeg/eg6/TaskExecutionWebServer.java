package threadeg.eg6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Description: 基于线程池的web服务器  《java并发编程实战》 P.97
 * @Auther: wangxiangyu
 * @Date: 2017/11/8 21:53
 */
public class TaskExecutionWebServer {
    private static final int NTHERADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHERADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true){
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    //这里是处理流程
                    //handleRequest(connection);
                }
            };

            //用lambda形式如下：
            Runnable task1 = () -> {
                //这里是处理流程
                //handleRequest(connection);
            };
            exec.execute(task);
        }
    }
}
