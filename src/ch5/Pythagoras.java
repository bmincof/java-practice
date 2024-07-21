package ch5;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Pythagoras {

    // 피타고라스 수 스트림 만들기
    public static void main(String[] args) {
        // a, b 가 주어진다면 -> sqrt(a^2 + b^2)가 정수이면 피타고라스 수
        IntStream.rangeClosed(1, 100).boxed()
            .flatMap(a -> IntStream.rangeClosed(a, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
            .forEach(triplet -> System.out.println(Arrays.toString(triplet)));
    }
}
