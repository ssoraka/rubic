package ru;

public class Rubic {

    private Cube[][][] cube;

    public Rubic() {
        cube = new Cube[3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = new Cube(i, j ,k);
                }
            }
        }
        initFace();
        //cube[0][0][0] = new Cube();
    }

    public Cube[][][] getCube() {
        return cube;
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

    public boolean hasWhiteCross() {
        if (cube[0][1][1].getDown() != Colors.WHITE) {
            throw new RuntimeException();
        }
        return cube[0][0][1].getDown() == Colors.WHITE
                && cube[0][2][1].getDown() == Colors.WHITE
                && cube[0][1][0].getDown() == Colors.WHITE
                && cube[0][2][0].getDown() == Colors.WHITE;
    }

    public boolean isValidWhiteCross() {

        return false;
    }

    public void x() {
        for (int i = 0; i < 3; i++) {
            Cube tmp = cube[i][0][0].rotUp();
            cube[i][0][0] = cube[i][0][2].rotUp();
            cube[i][0][2] = cube[i][2][2].rotUp();
            cube[i][2][2] = cube[i][2][0].rotUp();
            cube[i][2][0] = tmp;
            tmp = cube[i][1][0].rotUp();
            cube[i][1][0] = cube[i][0][1].rotUp();
            cube[i][0][1] = cube[i][1][2].rotUp();
            cube[i][1][2] = cube[i][2][1].rotUp();
            cube[i][2][1] = tmp;
        }
    }

    public void xRev() {
        for (int i = 0; i < 3; i++) {
            Cube tmp = cube[i][0][0].rotUpRev();
            cube[i][0][0] = cube[i][2][0].rotUpRev();
            cube[i][2][0] = cube[i][2][2].rotUpRev();
            cube[i][2][2] = cube[i][0][2].rotUpRev();
            cube[i][0][2] = tmp;
            tmp = cube[i][1][0].rotUpRev();
            cube[i][1][0] = cube[i][2][1].rotUpRev();
            cube[i][2][1] = cube[i][1][2].rotUpRev();
            cube[i][1][2] = cube[i][0][1].rotUpRev();
            cube[i][0][1] = tmp;
        }
    }

    public void y() {
        for (int i = 0; i < 3; i++) {
            Cube tmp = cube[0][0][i].rotRightRev();
            cube[0][0][i] = cube[2][0][i].rotRightRev();
            cube[2][0][i] = cube[2][2][i].rotRightRev();
            cube[2][2][i] = cube[0][2][i].rotRightRev();
            cube[0][2][i] = tmp;
            tmp = cube[1][0][i].rotRightRev();
            cube[1][0][i] = cube[2][1][i].rotRightRev();
            cube[2][1][i] = cube[1][2][i].rotRightRev();
            cube[1][2][i] = cube[0][1][i].rotRightRev();
            cube[0][1][i] = tmp;
        }
    }

    public void yRev() {
        for (int i = 0; i < 3; i++) {
            Cube tmp = cube[0][0][i].rotRight();
            cube[0][0][i] = cube[0][2][i].rotRight();
            cube[0][2][i] = cube[2][2][i].rotRight();
            cube[2][2][i] = cube[2][0][i].rotRight();
            cube[2][0][i] = tmp;
            tmp = cube[1][0][i].rotRight();
            cube[1][0][i] = cube[0][1][i].rotRight();
            cube[0][1][i] = cube[1][2][i].rotRight();
            cube[1][2][i] = cube[2][1][i].rotRight();
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
