package ch5;

import java.util.stream.Stream;

public class InfiniteStream {
    public static void main(String[] args) {
        Stream.iterate(new int[] {0, 1}, prev -> new int[]{prev[1], prev[0] + prev[1]})
            .limit(20)
            .forEach(seq -> System.out.println(seq[1]));
    }
}
