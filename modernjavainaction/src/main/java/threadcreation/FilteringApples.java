package threadcreation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static threadcreation.Color.*;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(GREEN, 80.0),
                new Apple(GREEN, 155.0),
                new Apple(RED, 120.0)
        );

        List<Apple> heavyApples = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());

        System.out.println(heavyApples);
        System.out.println(greenApples);
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        return inventory.stream().filter(p::test).collect(Collectors.toList());
    }

    public static <T extends Fruit> List<T> filter(List<T> list, Predicate<T> p) {
        return list.stream().filter(p::test).collect(Collectors.toList());
    }
}