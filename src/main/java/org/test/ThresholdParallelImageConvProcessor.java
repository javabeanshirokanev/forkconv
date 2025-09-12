package org.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ThresholdParallelImageConvProcessor {
    private byte[][] mask;

    public ThresholdParallelImageConvProcessor(byte[][] mask) {
        this.mask = mask;
    }

    public byte[][] process(byte[][] image) {
        byte[][] result = new byte[image.length - mask.length + 1][image[0].length - mask[0].length + 1];
        ForkJoinPool pool = ForkJoinPool.commonPool(); // общий пул, не создаём новый каждый раз
        ConvRecursiveTask2 task = new ConvRecursiveTask2(mask, image, 0, result.length, result);
        pool.invoke(task);
        return result;
    }
}
