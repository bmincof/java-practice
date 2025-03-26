package ch6;

import ch5.Dish;
import provider.DishProvider;

import java.util.IntSummaryStatistics;
import java.util.List;
import static java.util.stream.Collectors.*;

public class ReducingExample {
    public static void main(String[] args) {
        List<Dish> menu = DishProvider.getDishList();

        // count() == collect(Collectors.counting())
        long howManyDishes = menu.stream().count();

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        menu.stream().collect(reducing(
            (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
    }
}
