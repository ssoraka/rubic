package ru;


public class Cube {
    private int colorsHash;
    private Colors left = Colors.BLACK;
    private Colors right = Colors.BLACK;
    private Colors up = Colors.BLACK;
    private Colors down = Colors.BLACK;
    private Colors front = Colors.BLACK;
    private Colors back = Colors.BLACK;

    boolean isRightPlace;

    public Cube() {
    }

    public Cube rotFront() {
        Colors tmp = up;
        up = left;
        left = down;
        down = right;
        right = tmp;
        return this;
    }

    public Cube rotFrontRev() {
        Colors tmp = up;
        up = right;
        right = down;
        down = left;
        left = tmp;
        return this;
    }

    public Cube rotUpRev() {
        Colors tmp = front;
        front = left;
        left = back;
        back = right;
        right = tmp;
        return this;
    }

    public Cube rotUp() {
        Colors tmp = front;
        front = right;
        right = back;
        back = left;
        left = tmp;
        return this;
    }

    public Cube rotRightRev() {
        Colors tmp = up;
        up = front;
        front = down;
        down = back;
        back = tmp;
        return this;
    }

    public Cube rotRight() {
        Colors tmp = up;
        up = back;
        back = down;
        down = front;
        front = tmp;
        return this;
    }

    private int getColorsHash() {
        if (colorsHash == Colors.BLACK.code) {
            colorsHash = left.code | right.code | up.code | down.code | front.code | back.code;
        }
        return colorsHash;
    }

    public boolean hasColor(Colors color) {
        return (getColorsHash() & color.code) != 0;
    }

    public Colors getLeft() {
        return left;
    }

    public void setLeft(Colors left) {
        this.left = left;
    }

    public Colors getRight() {
        return right;
    }

    public void setRight(Colors right) {
        this.right = right;
    }

    public Colors getUp() {
        return up;
    }

    public void setUp(Colors up) {
        this.up = up;
    }

    public Colors getDown() {
        return down;
    }

    public void setDown(Colors down) {
        this.down = down;
    }

    public Colors getFront() {
        return front;
    }

    public void setFront(Colors front) {
        this.front = front;
    }

    public Colors getBack() {
        return back;
    }

    public void setBack(Colors back) {
        this.back = back;
    }

    public boolean isRightPlace() {
        return isRightPlace;
    }

    public void setRightPlace(boolean rightPlace) {
        isRightPlace = rightPlace;
    }

    @Override
    public String toString() {
        getColorsHash();
        StringBuilder sb = new StringBuilder();
        for (Colors c : Colors.values()) {
            if ((c.code & colorsHash) != 0) {
                sb.append(c.letter);
            }
        }
        return sb.toString();
    }
}
