package net.bean.java.tsp.algorithm.selection.roulette;

import java.util.Objects;

public class Range implements Comparable<Range> {

    private final int from;
    private final int to;

    public Range(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return Objects.equals(from, range.from) &&
                Objects.equals(to, range.to);
    }

    @Override
    public int hashCode() {

        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public int compareTo(Range o) {
        if(this.from < o.from) return -1;
        if(this.from > o.from) return 1;
        return 0;
    }
}
