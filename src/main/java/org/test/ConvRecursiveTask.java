package org.test;

import java.util.concurrent.RecursiveTask;

public class ConvRecursiveTask extends RecursiveTask<Byte> {
    private byte[][] mask;
    private byte[][] image;
    private int i0;
    private int j0;
    private int w;
    private int h;

    public ConvRecursiveTask(byte[][] mask, byte[][] image, int i0, int j0) {
        this.mask = mask;
        this.image = image;
        this.i0 = i0;
        this.j0 = j0;
        this.w = mask[0].length;
        this.h = mask.length;
    }

    @Override
    protected Byte compute() {
        int sum = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                byte m = mask[i][j];
                byte p = image[i + i0][j + j0];
                sum += m * p;
            }
        }
        return (byte)Math.min(Math.max(sum, 0), 255);
    }
}
