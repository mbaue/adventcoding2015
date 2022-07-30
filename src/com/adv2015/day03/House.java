package com.adv2015.day03;

import java.util.Objects;

public class House {
    private final int x;
    private final int y;

    public House(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return x == house.x && y == house.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
