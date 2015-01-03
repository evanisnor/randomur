package com.ewisnor.randomur.util;

/**
 * Generic Pair to allow a function to return two values.
 *
 * Created by evan on 2015-01-03.
 */
public class Pair<First, Second> {
    private First first;
    private Second second;

    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public First getFirst() {
        return first;
    }

    public Second getSecond() {
        return second;
    }
}
