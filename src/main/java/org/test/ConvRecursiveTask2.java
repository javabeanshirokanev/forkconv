package org.test;

import java.util.concurrent.RecursiveAction;

public class ConvRecursiveTask2 extends RecursiveAction {
    private static final int THRESHOLD = 50; // размер блока строк
    private byte[][] mask;
    private byte[][] image;
    private int startRow;
    private int endRow;
    private byte[][] result;

    public ConvRecursiveTask2(byte[][] mask, byte[][] image, int startRow, int endRow, byte[][] result) {
        this.mask = mask;
        this.image = image;
        this.startRow = startRow;
        this.endRow = endRow;
        this.result = result;
    }

    @Override
    protected void compute() {
        int mH = mask.length;
        int mW = mask[0].length;
        int w = image[0].length;

        if (endRow - startRow <= THRESHOLD) {
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < w - mW + 1; j++) {
                    result[i][j] = convolveAt(i, j);
                }
            }
        } else {
            int mid = (startRow + endRow) / 2;
            invokeAll(
                    new ConvRecursiveTask2(mask, image, startRow, mid, result),
                    new ConvRecursiveTask2(mask, image, mid, endRow, result)
            );
        }
    }

    private byte convolveAt(int row, int col) {
        int sum = 0;
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[0].length; j++) {
                sum += mask[i][j] * image[row + i][col + j];
            }
        }
        return (byte) Math.min(Math.max(sum, 0), 255);
    }
}
