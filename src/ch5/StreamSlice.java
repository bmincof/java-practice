package ch5;

import java.util.Arrays;
import java.util.List;

public class StreamSlice {
    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
            new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER)
        );

        System.out.println(lowerThan320Calories(specialMenu));
        System.out.println(takeWhileLowerThan320Calories(specialMenu));
        System.out.println(dropWhileLowerThan320Calories(specialMenu));
        System.out.println(getFirst3Dish(specialMenu));
        System.out.println(dropFirst3Dish(specialMenu));
    }

    private static List<Dish> lowerThan320Calories(List<Dish> menus) {
        return menus.stream()
            .filter(dish -> dish.getCalories() < 320)
            .toList();
    }

    // 320 보다 큰 요소가 나오면 그 뒤는 모두 버림
    // 칼로리 순으로 정렬되어 있으면 위의 filter와 같은 결과
    private static List<Dish> takeWhileLowerThan320Calories(List<Dish> menus) {
        return menus.stream()
            .takeWhile(dish -> dish.getCalories() < 320)
            .toList();
    }

    // 320 보다 큰 요소가 나올 때 까지 모두 버림
    private static List<Dish> dropWhileLowerThan320Calories(List<Dish> menus) {
        return menus.stream()
            .dropWhile(dish -> dish.getCalories() < 320)
            .toList();
    }

    // 앞의 3개 요소 제외 모두 버림
    private static List<Dish> getFirst3Dish(List<Dish> menus) {
        return menus.stream()
            .limit(3)
            .toList();
    }

    // 앞의 3개 요소 버림
    private static List<Dish> dropFirst3Dish(List<Dish> menus) {
        return menus.stream()
            .skip(3)
            .toList();
    }
}