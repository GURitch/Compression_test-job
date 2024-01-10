package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompactSerializer {
    public static String serialize(List<Integer> numbers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : numbers) {
            String numStr = Integer.toString(num);
            stringBuilder.append(numStr.length()).append(numStr);
        }
        return stringBuilder.toString();
    }

    public static List<Integer> deserialize(String str) {
        List<Integer> numbers = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int num = Character.getNumericValue(str.charAt(i));
            i++;
            String numStr = str.substring(i, i + num);
            numbers.add(Integer.parseInt(numStr));
            i += num;
        }
        return numbers;
    }

    public static void main(String[] args) {
        // Создаем списки
        Random random = new Random();
        List<List<Integer>> lists = List.of(
                List.of(11, 58, 365),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 22, 33, 44, 55),
                List.of(111, 222, 333, 444, 555),
                new ArrayList<>(){{
                    for (int i = 0; i < 300; i++) {
                        add(i);
                    }
                }},
                new ArrayList<>(){{
                    for (int i = 0; i < 1000; i++) {
                        add(1);
                    }
                }},
                new ArrayList<>(){{
                    for (int i = 0; i < 1000; i++) {
                        add(i);
                    }
                }},
                new ArrayList<>(){{
                    for (int i = 0; i < 300; i++) {
                        add(111);
                        add(222);
                        add(333);
                    }
                }},
                new ArrayList<>(){{
                    for (int i = 0; i < 1000; i++) {
                        add(random.nextInt(999));
                    }
                }});

        for (List<Integer> list : lists) {
            String serialized = serialize(list);
            List<Integer> deserialized = deserialize(serialized);
            int originLength = list.toString().length();
            int serializedLength = serialized.length();
            //Разница в длине срок
            double compression = (double) originLength / serializedLength;
            System.out.println("Original: " + list);
            System.out.println("originLength: " + originLength);
            System.out.println("Serialized: " + serialized);
            System.out.println("serializedLength: " + serializedLength);
            System.out.println("Deserialized: " + deserialized);
            System.out.println("Compression Ratio: " + compression);
            System.out.println();
        }
    }
}
