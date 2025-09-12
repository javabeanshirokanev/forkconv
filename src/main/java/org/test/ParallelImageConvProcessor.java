package org.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ParallelImageConvProcessor {
    private byte[][] mask;

    public ParallelImageConvProcessor(byte[][] mask) {
        this.mask = mask;
    }

    public byte[][] process(byte[][] image) {
        int w = image[0].length;
        int h = image.length;
        int mW = mask[0].length;
        int mH = mask.length;
        byte[][] result = new byte[h - mH + 1][w - mW + 1];
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Byte>[][] futures = new ForkJoinTask[result.length][result[0].length];
        for (int i = 0; i < h - mH + 1; i++) {
            for (int j = 0; j < w - mW + 1; j++) {
                ConvRecursiveTask task = new ConvRecursiveTask(mask, image, i, j);
                futures[i][j] = pool.submit(task);
            }
        }

        for (int i = 0; i < h - mH + 1; i++) {
            for (int j = 0; j < w - mW + 1; j++) {
                var byteResult = futures[i][j].join();
                result[i][j] = byteResult;
            }
        }
        return result;
    }
}
