package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger lenVar3 = new AtomicInteger(0);
    public static AtomicInteger lenVar4 = new AtomicInteger(0);
    public static AtomicInteger lenVar5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        Thread thread3 = new Thread(() -> {
            for (String text : texts) {
                if (isBeautiful3(text)) {
                    lenVar3.incrementAndGet();
                }
            }
        });
        Thread thread4 = new Thread(() -> {
            for (String text : texts) {
                if (isBeautiful4(text)) {
                    lenVar4.incrementAndGet();
                }
            }
        });
        Thread thread5 = new Thread(() -> {
            for (String text : texts) {
                if (isBeautiful5(text)) {
                    lenVar5.incrementAndGet();
                }
            }
        });

        thread3.start();
        thread4.start();
        thread5.start();

        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println("Красивых слов с длиной 3: " + lenVar3);
        System.out.println("Красивых слов с длиной 4: " + lenVar4);
        System.out.println("Красивых слов с длиной 5: " + lenVar5);

    }
    public static boolean isBeautiful3(String text) {
        return text.equals(new StringBuilder(text).reverse().toString());
    }

    public static boolean isBeautiful4(String text) {
        return text.chars().distinct().count() == 1;
    }

    public static boolean isBeautiful5(String text) {
        char currentChar = 'a';
        for (char c : text.toCharArray()) {
            if (c != currentChar) {
                return false;
            }
            currentChar++;
        }
        return true;
    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}

