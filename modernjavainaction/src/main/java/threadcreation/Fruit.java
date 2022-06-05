package threadcreation;

import lombok.*;

@Getter
@Setter
@ToString
public class Fruit {
    Color color;
    Double weight;

    public Fruit(Color color, Double weight) {
        this.color = color;
        this.weight = weight;
    }

    Fruit(Double weight) {
        this.weight = weight;
    }
}
