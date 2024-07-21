package ch5;

import java.util.Arrays;
import java.util.List;

public class MapStream {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hello", "World", "!");

        List<Integer> wordLengths = words.stream()
            .map(String::length)
            .toList();
        System.out.println(wordLengths);

        List<String> distinctChars = words.stream()
            .map(word -> word.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .toList();
        System.out.println(distinctChars);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(
            numbers.stream()
                .map(number -> number * number)
                .toList()
        );

        List<Integer> x = Arrays.asList(1, 2, 3);
        List<Integer> y = Arrays.asList(3, 4);

        List<Pair> pairs = getAllPairs(x, y);
        System.out.println(pairs);
        System.out.println(
            pairs.stream()
                .filter(pair -> (pair.x + pair.y) % 3 == 0)
                .toList()
        );
        System.out.println(getAllPairsOfMod3(x, y));
    }

    private static List<Pair> getAllPairs(List<Integer> xValues, List<Integer> yValues) {
        return xValues.stream()
            .flatMap(x -> yValues.stream().map(y -> new Pair(x, y)))
            .toList();
    }

    private static List<Pair> getAllPairsOfMod3(List<Integer> xValues, List<Integer> yValues) {
        return xValues.stream()
            .flatMap(x -> yValues.stream()
                .filter(y -> (x + y) % 3 == 0)
                .map(y -> new Pair(x, y)))
            .toList();
    }

    private static class Pair {
        final int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

}
