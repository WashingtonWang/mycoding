package com.mycoding.javabase.threadeg.eg6;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: 使用CompletionService，使页面元素在下载完成后立即显示出来 《java并发编程实战》 P.107
 * @Auther: wangxiangyu
 * @Date: 2017/11/14 21:58
 */
public class Renderer {
    private final ExecutorService executor;

    public Renderer(ExecutorService executor) {
        this.executor = executor;
    }


    void renderPage(CharSequence source){
        List<FutureRenderer.ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);
        for (final FutureRenderer.ImageInfo imageInfo : info){
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    //下载图片
                    return imageInfo.downloadImage();
                }
            });
            //lambda形式
            completionService.submit(() -> imageInfo.downloadImage());
            //加载文本
            //renderText(source);
            try {
                for (int t = 0, n = info.size(); t < n; t++){
                    Future<ImageData> f = completionService.take();
                    ImageData imageData = f.get();
                    //加载图片
                    //renderImage(imageData);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }catch (ExecutionException e){
                throw new RuntimeException(e);
            }
        }
    }

    private List<FutureRenderer.ImageInfo> scanForImageInfo(CharSequence source){
        return new ArrayList<>();
    }
}
