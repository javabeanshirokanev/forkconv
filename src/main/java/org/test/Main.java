package org.test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static byte[][] generate(int N, int M) {
        Random rnd = new Random();
        byte[][] result = new byte[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[i][j] = (byte)rnd.nextInt(200);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        long start = System.nanoTime();
        var mask = new byte[][] {
                { 0, -1, 0 },
                { -1, 4, -1 },
                { 0, -1, 0 }
        };
        ImageConvProcessor processor1 = new ImageConvProcessor(mask);
        var image = generate(2000, 2000);
//        var image = new byte[][] {
//                { 1, 2, 5, 6, 1 },
//                { 3, 5, 1, 2, 3 },
//                { 5, 3, 5, 5, 6 },
//                { 1, 2, 5, 3, 2 }
//        };
        var result1 = processor1.process(image);
//
        long end = System.nanoTime();
        System.out.println("Время выполнения = " + (end - start) / 1_000_000 + " мс");

        long start2 = System.nanoTime();
        ParallelImageConvProcessor processor = new ParallelImageConvProcessor(mask);
        var result = processor.process(image);
        long end2 = System.nanoTime();
        System.out.println("Время выполнения 2 = " + (end2 - start2) / 1_000_000 + " мс");

        long start3 = System.nanoTime();
        ThresholdParallelImageConvProcessor processor3 = new ThresholdParallelImageConvProcessor(mask);
        byte[][] result3 = processor3.process(image);
        long end3 = System.nanoTime();
        System.out.println("Время выполнения 3 = " + (end3 - start3) / 1_000_000 + " мс");
    }
}