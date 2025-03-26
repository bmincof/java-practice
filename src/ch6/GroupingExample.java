package ch6;

import ch5.Dish;
import provider.DishProvider;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class GroupingExample {
    public static void main(String[] args) {
        List<Dish> menu = DishProvider.getDishList();

        // type을 키로 그룹화, 요소를 칼로리로 필터한 후 리스트에 담기
        // groupBy type as key, and then filter elements by calories, and then collect elements as list
        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
            .collect(groupingBy(
                Dish::getType,
                filtering(dish -> dish.getCalories() > 500, toList())
            ));

        // 필터대신 map, flat map 도 가능
        Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
            .collect(groupingBy(
                Dish::getType,
                mapping(Dish::getName, toList())
            ));

        Map<Dish.Type, Long> typesCount = menu.stream()
            .collect(groupingBy(Dish::getType, counting()));

        // Multi-Level 맵도 가능
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
            .collect(groupingBy(
                Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400)
                        return CaloricLevel.DIET;

                    if (dish.getCalories() <= 700)
                        return CaloricLevel.NORMAL;

                    return CaloricLevel.FAT;
                })
            ));
    }

    public static enum CaloricLevel {
        DIET,
        NORMAL,
        FAT,
    }
}
