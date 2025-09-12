package org.test;

public class ImageConvProcessor {
    private byte[][] mask;

    public ImageConvProcessor(byte[][] mask) {
        this.mask = mask;
    }

    public byte[][] process(byte[][] image) {
        int w = image[0].length;
        int h = image.length;
        int mW = mask[0].length;
        int mH = mask.length;
        byte[][] result = new byte[h - mH + 1][w - mW + 1];

        for (int i = 0; i < h - mH + 1; i++) {
            for (int j = 0; j < w - mW + 1; j++) {
                int sum = 0;
                for (int mi = 0; mi < mH; mi++) {
                    for (int mj = 0; mj < mW; mj++) {
                        byte m = mask[mi][mj];
                        byte p = image[mi + i][mj + j];
                        sum += m * p;
                    }
                }
                result[i][j] = (byte)Math.min(Math.max(sum, 0), 255);
            }
        }
        return result;
    }
}
