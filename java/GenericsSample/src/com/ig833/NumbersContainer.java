package com.ig833;


import java.util.List;

public class NumbersContainer<Num extends Number, Container extends List<Num>> {
    private Container container;

    public NumbersContainer(Container container) {
        this.container = container;
    }

    public void add(Num num) {
        container.add(num);
    }

    public double sum() {
        double sum = 0;
        for (Num num : container) {
            sum += num.doubleValue();
        }
        return sum;
    }
}
