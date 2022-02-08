package ru;

import ru.rotators.Rotator;
import ru.rotators.RotatorFabric;

public class Rubik {
    private Cube[][][] cube;
    private Rotator front;
    private Rotator back;
    private Rotator up;
    private Rotator down;
    private Rotator left;
    private Rotator right;

    public Rubik() {
        cube = new Cube[3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = new Cube();
                }
            }
        }
        initFace();

        front = RotatorFabric.createFrontRotator(cube);
        back = RotatorFabric.createBackRotator(cube);
        up = RotatorFabric.createUpRotator(cube);
        down = RotatorFabric.createDownRotator(cube);
        left = RotatorFabric.createLeftRotator(cube);
        right = RotatorFabric.createRightRotator(cube);
    }

    public void f() {
        front.rot();
    }

    public void fRev() {
        front.rotRev();
    }

    public void b() {
        back.rotRev();
    }

    public void bRev() {
        back.rot();
    }

    public void u() {
        up.rot();
    }

    public void uRev() {
        up.rotRev();
    }

    public void d() {
        down.rot();
    }

    public void dRev() {
        down.rotRev();
    }

    public void l() {
        left.rot();
    }

    public void lRev() {
        left.rotRev();
    }

    public void r() {
        right.rotRev();
    }

    public void rRev() {
        right.rot();
    }

    public Cube getDF() {
        return cube[0][0][1];
    }

    public Cube getDB() {
        return cube[0][2][1];
    }

    public Cube getDL() {
        return cube[0][1][2];
    }

    public Cube getDR() {
        return cube[0][1][0];
    }

    public Cube getUF() {
        return cube[2][0][1];
    }

    public Cube getUB() {
        return cube[2][2][1];
    }

    public Cube getUL() {
        return cube[2][1][2];
    }

    public Cube getUR() {
        return cube[2][1][0];
    }

    public Cube getRF() {
        return cube[1][0][0];
    }

    public Cube getRB() {
        return cube[1][2][0];
    }

    public Cube getLF() {
        return cube[1][0][2];
    }

    public Cube getLB() {
        return cube[1][2][2];
    }

    public Cube getURF() {
        return cube[2][0][0];
    }

    public Cube getURB() {
        return cube[2][2][0];
    }

    public Cube getULF() {
        return cube[2][0][2];
    }

    public Cube getULB() {
        return cube[2][2][2];
    }

    public Cube getDRF() {
        return cube[0][0][0];
    }

    public Cube getDRB() {
        return cube[0][2][0];
    }

    public Cube getDLF() {
        return cube[0][0][2];
    }

    public Cube getDLB() {
        return cube[0][2][2];
    }

    public Colors getRightColor() {
        return cube[1][1][0].getRight();
    }

    public Colors getLeftColor() {
        return cube[1][1][2].getLeft();
    }

    public Colors getUpColor() {
        return cube[2][1][1].getUp();
    }

    public Colors getDownColor() {
        return cube[0][1][1].getDown();
    }

    public Colors getFrontColor() {
        return cube[1][0][1].getFront();
    }

    public Colors getBackColor() {
        return cube[1][2][1].getBack();
    }

    private void initFace() {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[0][i][j].setDown(Colors.WHITE);
                cube[2][i][j].setUp(Colors.YELLOW);
                cube[i][0][j].setFront(Colors.BLUE);
                cube[i][2][j].setBack(Colors.GREEN);
                cube[i][j][0].setRight(Colors.RED);
                cube[i][j][2].setLeft(Colors.ORANGE);
            }
        }
    }

    public boolean isComplete() {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cube[0][i][j].getDown() != Colors.WHITE
                    || cube[2][i][j].getUp() != Colors.YELLOW
                    || cube[i][0][j].getFront() != getFrontColor()
                    || cube[i][2][j].getBack() != getBackColor()
                    || cube[i][j][0].getRight() != getRightColor()
                    || cube[i][j][2].getLeft() != getLeftColor()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void x() {
        for (int i = 0; i < 3; i++) {
            Cube tmp = cube[i][0][0].rotUpRev();
            cube[i][0][0] = cube[i][0][2].rotUpRev();
            cube[i][0][2] = cube[i][2][2].rotUpRev();
            cube[i][2][2] = cube[i][2][0].rotUpRev();
            cube[i][2][0] = tmp;
            tmp = cube[i][1][0].rotUpRev();
            cube[i][1][0] = cube[i][0][1].rotUpRev();
            cube[i][0][1] = cube[i][1][2].rotUpRev();
            cube[i][1][2] = cube[i][2][1].rotUpRev();
            cube[i][2][1] = tmp;
        }
    }

    public void xRev() {
        for (int i = 0; i < 3; i++) {
            Cube tmp = cube[i][0][0].rotUp();
            cube[i][0][0] = cube[i][2][0].rotUp();
            cube[i][2][0] = cube[i][2][2].rotUp();
            cube[i][2][2] = cube[i][0][2].rotUp();
            cube[i][0][2] = tmp;
            tmp = cube[i][1][0].rotUp();
            cube[i][1][0] = cube[i][2][1].rotUp();
            cube[i][2][1] = cube[i][1][2].rotUp();
            cube[i][1][2] = cube[i][0][1].rotUp();
            cube[i][0][1] = tmp;
        }
    }

    public void y() {
        for (int i = 0; i < 3; i++) {
            Cube tmp = cube[0][0][i].rotRight();
            cube[0][0][i] = cube[2][0][i].rotRight();
            cube[2][0][i] = cube[2][2][i].rotRight();
            cube[2][2][i] = cube[0][2][i].rotRight();
            cube[0][2][i] = tmp;
            tmp = cube[1][0][i].rotRight();
            cube[1][0][i] = cube[2][1][i].rotRight();
            cube[2][1][i] = cube[1][2][i].rotRight();
            cube[1][2][i] = cube[0][1][i].rotRight();
            cube[0][1][i] = tmp;
        }
    }

    public void yRev() {
        for (int i = 0; i < 3; i++) {
            Cube tmp = cube[0][0][i].rotRightRev();
            cube[0][0][i] = cube[0][2][i].rotRightRev();
            cube[0][2][i] = cube[2][2][i].rotRightRev();
            cube[2][2][i] = cube[2][0][i].rotRightRev();
            cube[2][0][i] = tmp;
            tmp = cube[1][0][i].rotRightRev();
            cube[1][0][i] = cube[0][1][i].rotRightRev();
            cube[0][1][i] = cube[1][2][i].rotRightRev();
            cube[1][2][i] = cube[2][1][i].rotRightRev();
            cube[2][1][i] = tmp;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            sb.append("    ");
            sb.append(cube[2][2 - i][2].getUp().letter);
            sb.append(cube[2][2 - i][1].getUp().letter);
            sb.append(cube[2][2 - i][0].getUp().letter);
            sb.append("\n");
        }

        for (int i = 0; i < 3; i++) {
            sb.append(cube[2 - i][2][2].getLeft().letter);
            sb.append(cube[2 - i][1][2].getLeft().letter);
            sb.append(cube[2 - i][0][2].getLeft().letter);
            sb.append(" ");
            sb.append(cube[2 - i][0][2].getFront().letter);
            sb.append(cube[2 - i][0][1].getFront().letter);
            sb.append(cube[2 - i][0][0].getFront().letter);
            sb.append(" ");
            sb.append(cube[2 - i][0][0].getRight().letter);
            sb.append(cube[2 - i][1][0].getRight().letter);
            sb.append(cube[2 - i][2][0].getRight().letter);
            sb.append(" ");
            sb.append(cube[2 - i][2][0].getBack().letter);
            sb.append(cube[2 - i][2][1].getBack().letter);
            sb.append(cube[2 - i][2][2].getBack().letter);
            sb.append("\n");
        }

        for (int i = 0; i < 3; i++) {
            sb.append("    ");
            sb.append(cube[0][i][2].getDown().letter);
            sb.append(cube[0][i][1].getDown().letter);
            sb.append(cube[0][i][0].getDown().letter);
            sb.append("\n");
        }
        return sb.toString();
    }
}
