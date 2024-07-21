package ch5;

import java.util.Arrays;
import java.util.List;

public class SearchAndMatchStream {
    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
            new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER)
        );

        if (specialMenu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("채식 메뉴 있음");
        }

        if (specialMenu.stream().allMatch(dish -> dish.getCalories() < 500)) {
            System.out.println("모두 500 미만");
        } else if (specialMenu.stream().allMatch(dish -> dish.getCalories() < 600)) {
            System.out.println("모두 600 미만");
        }
    }
}
