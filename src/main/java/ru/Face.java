package ru;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Face {
    private FaceGetter get;
    private FaceSetter set;
    private UnaryOperator<Cube> rotFunc;
    private UnaryOperator<Cube> rotRevFunc;
    private Function<Cube, Colors> getColors;

    public Face(FaceGetter get, FaceSetter set, UnaryOperator<Cube> rotFunc, UnaryOperator<Cube> rotRevFunc, Function<Cube, Colors> getColor) {
        this.get = get;
        this.set = set;
        this.rotFunc = rotFunc;
        this.rotRevFunc = rotRevFunc;
        this.getColors = getColor;
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

    public Colors getColor() {
        return getColors.apply(get.get(1, 1));
    }
}
