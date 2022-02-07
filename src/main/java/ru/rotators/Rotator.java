package ru.rotators;

import ru.Cube;

import java.util.function.UnaryOperator;

public class Rotator {
    private Getter get;
    private Setter set;
    private UnaryOperator<Cube> rotFunc;
    private UnaryOperator<Cube> rotRevFunc;

    public Rotator(Getter get, Setter set, UnaryOperator<Cube> rotFunc, UnaryOperator<Cube> rotRevFunc) {
        this.get = get;
        this.set = set;
        this.rotFunc = rotFunc;
        this.rotRevFunc = rotRevFunc;
    }

    public void rot() {
        Cube tmp = rotFunc.apply(get.get(0, 0));
        set.set(0, 0, rotFunc.apply(get.get(2, 0)));
        set.set(2, 0, rotFunc.apply(get.get(2, 2)));
        set.set(2, 2, rotFunc.apply(get.get(0, 2)));
        set.set(0, 2, tmp);
        tmp = rotFunc.apply(get.get(1, 0));
        set.set(1, 0, rotFunc.apply(get.get(2, 1)));
        set.set(2, 1, rotFunc.apply(get.get(1, 2)));
        set.set(1, 2, rotFunc.apply(get.get(0, 1)));
        set.set(0, 1, tmp);
    }

    public void rotRev() {
        Cube tmp = rotRevFunc.apply(get.get(0, 0));
        set.set(0, 0, rotRevFunc.apply(get.get(0, 2)));
        set.set(0, 2, rotRevFunc.apply(get.get(2, 2)));
        set.set(2, 2, rotRevFunc.apply(get.get(2, 0)));
        set.set(2, 0, tmp);
        tmp = rotRevFunc.apply(get.get(1, 0));
        set.set(1, 0, rotRevFunc.apply(get.get(0, 1)));
        set.set(0, 1, rotRevFunc.apply(get.get(1, 2)));
        set.set(1, 2, rotRevFunc.apply(get.get(2, 1)));
        set.set(2, 1, tmp);
    }
}
