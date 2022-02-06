package ru;


public class Cube {
    private int colors;
    private Colors left = Colors.BLACK;
    private Colors right = Colors.BLACK;
    private Colors up = Colors.BLACK;
    private Colors down = Colors.BLACK;
    private Colors front = Colors.BLACK;
    private Colors back = Colors.BLACK;

    int x;
    int y;
    int z;

    boolean isRightPlace;

    public Cube(int z, int y, int x) {
        this.x = x - 1;
        this.y = y - 1;
        this.z = z - 1;
    }

    public Cube rotFront() {
        Colors tmp = up;
        up = left;
        left = down;
        down = right;
        right = tmp;

        int pos = z;
        z = x;
        x = -z;
        return this;
    }

    public Cube rotFrontRev() {
        Colors tmp = up;
        up = right;
        right = down;
        down = left;
        left = tmp;

        int pos = z;
        z = -x;
        x = z;
        return this;
    }

    public Cube rotUpRev() {
        Colors tmp = front;
        front = left;
        left = back;
        back = right;
        right = tmp;

        int pos = y;
        y = x;
        x = -y;
        return this;
    }

    public Cube rotUp() {
        Colors tmp = front;
        front = right;
        right = back;
        back = left;
        left = tmp;

        int pos = y;
        y = -x;
        x = y;
        return this;
    }

    public Cube rotRightRev() {
        Colors tmp = up;
        up = front;
        front = down;
        down = back;
        back = tmp;

        int pos = x;
        x = z;
        z = -x;
        return this;
    }

    public Cube rotRight() {
        Colors tmp = up;
        up = back;
        back = down;
        down = front;
        front = tmp;

        int pos = x;
        x = -z;
        z = x;
        return this;
    }

    public int getColors() {
        if (colors == Colors.BLACK.code) {
            colors = left.code | right.code | up.code | down.code | front.code | back.code;
        }
        return colors;
    }

    public boolean hasColor(Colors color) {
        return (getColors() & color.code) != 0;
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
        getColors();
        StringBuilder sb = new StringBuilder();
        for (Colors c : Colors.values()) {
            if ((c.code & colors) != 0) {
                sb.append(c.letter);
            }
        }
        return sb.toString();
    }
}
