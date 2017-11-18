package threadeg.eg7;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Description: 通过改写interrupt方法将非标准的取消操作封装在Thread中 《java并发编程实战》 P.122
 * user: xiangyu.wang
 * date: 2017/11/18 10:09
 */
public class ReaderThread extends Thread{

    private static final int BUFSZ = 1024;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket, InputStream in) {
        this.socket = socket;
        this.in = in;
    }

    public void interrupt(){
        try {
            socket.close();
        }catch (IOException ignored){ }
        finally {
            super.interrupt();
        }
    }

    public void run(){
        try {
            byte[] buf = new byte[BUFSZ];
            while (true){
                int count = in.read(buf);
                if (count < 0){
                    break;
                }else if (count > 0){
                    //processBuffer(buf, count);
                }
            }
        }catch (IOException e){
            //允许线程退出
        }
    }
}
