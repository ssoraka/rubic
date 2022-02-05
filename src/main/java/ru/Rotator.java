package ru;

public class Rotator {
    Rubic rubic;
    Cube[][][] cube;
    Logger logger = new Logger();

    Face front;
    Face back;
    Face up;
    Face down;
    Face left;
    Face right;

    public Rotator(Rubic rubic) {
        this.rubic = rubic;
        this.cube = rubic.getCube();

        front = new Face((i, j) -> cube[i][0][j],
                (i, j, c) -> cube[i][0][j] = c,
                Cube::rotFront,
                Cube::rotFrontRev,
                Cube::getFront);

        back = new Face((i, j) -> cube[i][2][j],
                (i, j, c) -> cube[i][2][j] = c,
                Cube::rotFront,
                Cube::rotFrontRev,
                Cube::getBack);

        up = new Face((i, j) -> cube[2][i][j],
                (i, j, c) -> cube[2][i][j] = c,
                Cube::rotUp,
                Cube::rotUpRev,
                Cube::getUp);

        down = new Face((i, j) -> cube[0][i][j],
                (i, j, c) -> cube[0][i][j] = c,
                Cube::rotUpRev,
                Cube::rotUp,
                Cube::getDown);

        left = new Face((i, j) -> cube[i][j][2],
                (i, j, c) -> cube[i][j][2] = c,
                Cube::rotRightRev,
                Cube::rotRight,
                Cube::getLeft);

        right = new Face((i, j) -> cube[i][j][0],
                (i, j, c) -> cube[i][j][0] = c,
                Cube::rotRightRev,
                Cube::rotRight,
                Cube::getRight);
    }



    public void f() {
        log(front.getColor(), false);
        front.rot();
    }

    public void fRev() {
        log(front.getColor(), true);
        front.rotRev();
    }

    public void b() {
        log(back.getColor(), false);
        back.rotRev();
    }

    public void bRev() {
        log(back.getColor(), true);
        back.rot();
    }

    public void u() {
        log(up.getColor(), false);
        up.rot();
    }

    public void u2() {
        log(up.getColor(), false);
        log(up.getColor(), false);
        up.rot();
        up.rot();
    }

    public void uRev() {
        log(up.getColor(), true);
        up.rotRev();
    }

    public void d() {
        log(down.getColor(), false);
        down.rot();
    }

    public void dRev() {
        log(down.getColor(), true);
        down.rotRev();
    }

    public void l() {
        log(right.getColor(), false);
        left.rot();
    }

    public void lRev() {
        log(left.getColor(), true);
        left.rotRev();
    }

    public void r() {
        log(right.getColor(), false);
        right.rotRev();
    }

    public void r2() {
        log(right.getColor(), false);
        log(right.getColor(), false);
        right.rotRev();
        right.rotRev();
    }

    public void rRev() {
        log(right.getColor(), true);
        right.rot();
    }

    private void log(Colors color, boolean rev) {
        if (!rev) {
            logger.log(color.command);
        } else {
            logger.log(color.command + "'");
        }
    }

    public void init() {
        logger.init();
    }

    public void printLog() {
        logger.printLog();
    }

    public Cube getCubeDF() {
        return cube[0][0][1];
    }

    public Cube getCubeDB() {
        return cube[0][2][1];
    }

    public Cube getCubeDL() {
        return cube[0][1][2];
    }

    public Cube getCubeDR() {
        return cube[0][1][0];
    }

    public Cube getCubeUF() {
        return cube[2][0][1];
    }

    public Cube getCubeUB() {
        return cube[2][2][1];
    }

    public Cube getCubeUL() {
        return cube[2][1][2];
    }

    public Cube getCubeUR() {
        return cube[2][1][0];
    }

    public Cube getCubeRF() {
        return cube[1][0][0];
    }

    public Cube getCubeRB() {
        return cube[1][2][2];
    }

    public void orientationWhiteCross() {
        int colors = 0;
        int rightPos = 0;
        if (getCubeDR().getDown() == Colors.WHITE) colors++;
        if (getCubeDL().getDown() == Colors.WHITE) colors++;
        if (getCubeDF().getDown() == Colors.WHITE) colors++;
        if (getCubeDB().getDown() == Colors.WHITE) colors++;

        if (colors > 1) {
            int max = 0;
            int rolCount = 0;
            for (int i = 0; i < 4; i++) {
                rightPos = 0;
                if (getCubeDR().getDown() == Colors.WHITE && getCubeDR().getRight() == right.getColor()) rightPos++;
                if (getCubeDL().getDown() == Colors.WHITE && getCubeDL().getLeft() == left.getColor()) rightPos++;
                if (getCubeDF().getDown() == Colors.WHITE && getCubeDF().getLeft() == front.getColor()) rightPos++;
                if (getCubeDB().getDown() == Colors.WHITE && getCubeDB().getLeft() == back.getColor()) rightPos++;
                if (rightPos > max) {
                    max = rightPos;
                    rolCount = i;
                }
                d();
            }
            for (int i = 0; i < rolCount; i++) {
                d();
            }
            for (int i = 0; i < 4; i++) {
                if (getCubeDR().getDown() == Colors.WHITE && getCubeDR().getRight() == right.getColor()) {
                    getCubeDR().setRightPlace(true);
                }
                rubic.x();
            }
            if (max == 4) return;
        }

        //пробуем поставить правильным местом
        for (int i = 0; i < 8; i++) {
            if (getCubeDR().isRightPlace()) {
                rubic.x();
                continue;
            }
            if (getCubeRF().getFront() == Colors.WHITE && getCubeRF().getRight() == right.getColor()) {
                rotYellow();
                rRev();
            }
            if (getCubeRB().getBack() == Colors.WHITE && getCubeRB().getRight() == right.getColor()) {
                rotYellow();
                r();
            }
            if (getCubeUR().getUp() == Colors.WHITE && getCubeUR().getRight() == right.getColor()) r2();
            if (getCubeDR().getDown() == Colors.WHITE && getCubeDR().getRight() == right.getColor()) {
                getCubeDR().setRightPlace(true);
            }
            rubic.x();
        }

        //поднимаем все наверх
        for (int i = 0; i < 8; i++) {
            if (getCubeRF().getFront() == Colors.WHITE) {
                rotYellow();
                r();
                if (getCubeDR().isRightPlace()) {
                    rotYellow();
                    rRev();
                }
            }
            if (getCubeRB().getBack() == Colors.WHITE) {
                rotYellow();
                rRev();
                if (getCubeDR().isRightPlace()) {
                    rotYellow();
                    r();
                }
            }
            if (getCubeDR().getDown() == Colors.WHITE && !getCubeDR().isRightPlace()) {
                rotYellow();
                r2();
            }
            if (i > 3) {
                if (getCubeUR().getRight() == Colors.WHITE && getCubeDR().getRight() == Colors.WHITE) {
                    r();
                }
                if (getCubeDR().getRight() == Colors.WHITE) {
                    rotYellow();
                    r();
                }
                if (getCubeUR().getRight() == Colors.WHITE) { //проверить надо бы
                    if (!getCubeDR().isRightPlace()) r();
                    else u();
                }
            }
            rubic.x();
        }


        //опускаем вниз
        for (int i = 0; i < 4; i++) {
            if (getCubeDR().isRightPlace()) continue;
            if (getCubeUF().getFront() == right.getColor()) {
                uRev();
            }
            if (getCubeUB().getBack() == right.getColor()) {
                u();
            }
            if (getCubeUL().getLeft() == right.getColor()) {
                u2();
            }
            if (getCubeUR().getRight() == right.getColor()) {
                r2();
                getCubeDR().setRightPlace(true);
            }
        }
    }

    private void rotYellow() {
        while (getCubeUR().getUp() == Colors.WHITE && getCubeUR().getRight() != right.getColor()) {
            u();
        }
    }


}
