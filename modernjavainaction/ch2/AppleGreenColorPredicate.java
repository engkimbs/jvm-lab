package modernjavainaction.ch2;

import static modernjavainaction.ch2.Color.GREEN;

public class AppleGreenColorPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }
}
