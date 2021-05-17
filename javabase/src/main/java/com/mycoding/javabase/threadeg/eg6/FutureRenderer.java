package com.mycoding.javabase.threadeg.eg6;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: 使用Future等待图像下载 《java并发编程实战》   P.105
 * @Auther: wangxiangyu
 * @Date: 2017/11/9 23:06
 */
public class FutureRenderer {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    void renderPage(CharSequence source){
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos){
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };

        Future<List<ImageData>> future = executor.submit(task);

        try{
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData){
                //
                //rederImage(data);
            }
        }catch (InterruptedException e){
            //重新设置线程的中断状态
            Thread.currentThread().interrupt();
            //由于不需要结果，因此取消任务
            future.cancel(true);
        }catch (ExecutionException ex){
            throw new RuntimeException(ex.getCause());
        }

    }

    static List<ImageInfo> scanForImageInfo(CharSequence source){
        return new ArrayList<>();
    }

    static class ImageInfo{

        static ImageData downloadImage(){
            ImageData imageData = new ImageData(null,null,null);
            return imageData;
        }
    }
}
