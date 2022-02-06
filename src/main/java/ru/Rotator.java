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
                Cube::rotUp,
                Cube::rotUpRev,
                Cube::getDown);

        left = new Face((i, j) -> cube[i][j][2],
                (i, j, c) -> cube[i][j][2] = c,
                Cube::rotRight,
                Cube::rotRightRev,
                Cube::getLeft);

        right = new Face((i, j) -> cube[i][j][0],
                (i, j, c) -> cube[i][j][0] = c,
                Cube::rotRight,
                Cube::rotRightRev,
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
    public void d(int n) {
        for (int i = 0; i < n; i++) {
            log(down.getColor(), false);
            down.rot();
        }
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

    public void orientationWhiteCross() {
        int colors = 0;
        int rightPos = 0;
        if (getDR().getDown() == Colors.WHITE) colors++;
        if (getDL().getDown() == Colors.WHITE) colors++;
        if (getDF().getDown() == Colors.WHITE) colors++;
        if (getDB().getDown() == Colors.WHITE) colors++;

        if (colors > 1) {
            int max = 0;
            int rolCount = 0;
            for (int i = 0; i < 4; i++) {
                rightPos = 0;
                if (getDR().getDown() == Colors.WHITE && getDR().getRight() == right.getColor()) rightPos++;
                if (getDL().getDown() == Colors.WHITE && getDL().getLeft() == left.getColor()) rightPos++;
                if (getDF().getDown() == Colors.WHITE && getDF().getLeft() == front.getColor()) rightPos++;
                if (getDB().getDown() == Colors.WHITE && getDB().getLeft() == back.getColor()) rightPos++;
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
                if (getDR().getDown() == Colors.WHITE && getDR().getRight() == right.getColor()) {
                    getDR().setRightPlace(true);
                }
                rubic.x();
            }
            if (max == 4) return;
        }

        //пробуем поставить правильным местом
        for (int i = 0; i < 8; i++) {
            if (getDR().isRightPlace()) {
                rubic.x();
                continue;
            }
            if (getRF().getFront() == Colors.WHITE && getRF().getRight() == right.getColor()) {
                rotYellow();
                rRev();
            }
            if (getRB().getBack() == Colors.WHITE && getRB().getRight() == right.getColor()) {
                rotYellow();
                r();
            }
            if (getUR().getUp() == Colors.WHITE && getUR().getRight() == right.getColor()) r2();
            if (getDR().getDown() == Colors.WHITE && getDR().getRight() == right.getColor()) {
                getDR().setRightPlace(true);
            }
            rubic.x();
        }

        //поднимаем все наверх
        for (int i = 0; i < 8; i++) {
            whiteToUp();
            rubic.x();
        }

        //борьба с неправильно расположенными детальками
        for (int i = 0; i < 8; i++) {
            if (getUR().getRight() != Colors.WHITE && getDR().getRight() != Colors.WHITE) {
                rubic.x();
                continue;
            }
            int num = 0;
            if (getDR().isRightPlace()) {
                num = rotWhite();
            }
            if (getUR().getUp() == Colors.WHITE) {
                rotYellow();
            }
            r();
            rubic.x();
            whiteToUp();
            rubic.xRev();
            d(4 - num);
            rubic.x();
        }


        //опускаем вниз
        for (int i = 0; i < 4; i++) {
            if (getDR().isRightPlace()) {
                rubic.x();
                continue;
            }
            whiteToDown();
            rubic.x();
        }

        if (!getDR().isRightPlace() || !getDL().isRightPlace() || !getDF().isRightPlace() || !getDB().isRightPlace()) {
            throw new RuntimeException("крест не собирается");
        }
    }


    public void insertCornersInFirstLayer() {
        checkCorners();

        while (!getDRF().isRightPlace() || !getDRB().isRightPlace()
                || !getDLF().isRightPlace() || !getDLB().isRightPlace()) {
            for (int i = 0; i < 4; i++) {
                if (!getDRF().isRightPlace()) {
                    placeCornerFromUp();
                }
                rubic.x();
            }

            for (int i = 0; i < 4; i++) {
                if (!getDRF().isRightPlace()) {
                    rightHand();
                    break ;
                }
                rubic.x();
            }
        }
    }

    public void secondLayer() {
        checkSeconds();

        while (!getRF().isRightPlace() || !getRB().isRightPlace()
                || !getLF().isRightPlace() || !getLB().isRightPlace()) {
            for (int i = 0; i < 4; i++) {
                if (!getRF().isRightPlace()) {
                    placeSecondFromUp();
                }
                rubic.x();
            }

            for (int i = 0; i < 4; i++) {
                if (!getRF().isRightPlace()) {
                    rightHand();
                    leftHand();
                    break ;
                }
                rubic.x();
            }
        }
    }

    public void thirdLayer() {
        yellowCross();
        placeThirdCorners();
        rotateYellowCorners();
        finalStep();
    }

    private void yellowCross() {
        int count = 0;
        if (getUF().getUp() == Colors.YELLOW) count++;
        if (getUB().getUp() == Colors.YELLOW) count++;
        if (getUR().getUp() == Colors.YELLOW) count++;
        if (getUL().getUp() == Colors.YELLOW) count++;
        if (count == 4) return;
        if (count < 2) {
            f();
            rightHand();
            fRev();
            yellowCross();
            return;
        }
        if (getUF().getUp() == Colors.YELLOW && getUB().getUp() == Colors.YELLOW) {
            rubic.x();
        }
        if (getUR().getUp() == Colors.YELLOW && getUL().getUp() == Colors.YELLOW) {
            f();
            rightHand();
            fRev();
        } else {
            while (getUB().getUp() != Colors.YELLOW || getUL().getUp() != Colors.YELLOW) {
                rubic.x();
            }
            f();
            rightHand(2);
            fRev();
        }

        if (getUF().getUp() != Colors.YELLOW || getUB().getUp() != Colors.YELLOW
            || getUR().getUp() != Colors.YELLOW || getUL().getUp() != Colors.YELLOW) {
            throw new RuntimeException("желтый крест не собрался");
        }
    }

    private void placeThirdCorners() {
        for (int i = 0; i < 4 && getRightThirdCornerCount() < 2 ; i++) {
            u();
        }
        if (getRightThirdCornerCount() == 4) return;

        for (int i = 0; i < 4; i++) {
            if (getULF().hasColor(front.getColor()) && getULF().hasColor(up.getColor()) && getULF().hasColor(left.getColor()) &&
                    getULB().hasColor(back.getColor()) && getULB().hasColor(up.getColor()) && getULB().hasColor(left.getColor())) {
                break ;
            }
            rubic.x();
        }

        if (!getULF().hasColor(front.getColor()) || !getULF().hasColor(up.getColor()) || !getULF().hasColor(left.getColor()))  {
            u();
            rightHand(3);
            leftHand(3);
        }
        rightHand(3);
        leftHand(3);

        for (int i = 0; i < 4 && getRightThirdCornerCount() < 4 ; i++) {
            u();
        }

        if (getRightThirdCornerCount() != 4) {
            throw new RuntimeException("не проставились желтые углы по своим местам");
        }
    }

    private void rotateYellowCorners() {
        rubic.y();
        rubic.y();
        for (int i = 0; i < 4 && getDRF().getDown() == Colors.YELLOW; i++) {
            rubic.x();
        }
        for (int i = 0; i < 8; i++) {
            while (getDRF().getDown() != Colors.YELLOW) {
                rightHand(2);
            }
            d();
        }
        rubic.y();
        rubic.y();

        if (getURF().getUp() != Colors.YELLOW || getURB().getUp() != Colors.YELLOW
                || getULF().getUp() != Colors.YELLOW || getULB().getUp() != Colors.YELLOW ) {
            throw new RuntimeException("не собрался верх");
        }
    }

    private void finalStep() {
        for (int i = 0; i < 100 && (!haveCompleteLine() || !isComplete()); i++) {
            rightHand();
            rubic.x();
            leftHand();
            rubic.xRev();
            rightHand(5);
            rubic.x();
            leftHand(5);
            rubic.xRev();
        }
        if (!isComplete()) {
            throw new RuntimeException("java.util.Date итоге что-то не собралось...");
        }
    }

    private boolean haveCompleteLine() {
        for (int i = 0; i < 4; i++) {
            Colors color = front.getColor();
            if (getURF().getFront() == color && getUF().getFront() == color && getULF().getFront() == color) {
                return true;
            }
            rubic.x();
        }
        return false;
    }

    private boolean isComplete() {
        if (getURF().getFront() != front.getColor() || getUF().getFront() != front.getColor()
                || getULF().getFront() != front.getColor()) return false;
        if (getURB().getBack() != back.getColor() || getUB().getBack() != back.getColor()
                || getULB().getBack() != back.getColor()) return false;
        if (getURF().getRight() != right.getColor() || getUR().getRight() != right.getColor()
                || getURB().getRight() != right.getColor()) return false;
        if (getULF().getLeft() != left.getColor() || getUL().getLeft() != left.getColor()
                || getULB().getLeft() != left.getColor()) return false;
        return true;
    }

    private int getRightThirdCornerCount() {
        int count = 0;
        if (getURF().hasColor(front.getColor()) && getURF().hasColor(up.getColor())
                && getURF().hasColor(right.getColor())) count++;
        if (getURB().hasColor(back.getColor()) && getURB().hasColor(up.getColor())
                && getURB().hasColor(right.getColor())) count++;
        if (getULF().hasColor(front.getColor()) && getULF().hasColor(up.getColor())
                && getULF().hasColor(left.getColor())) count++;
        if (getULB().hasColor(back.getColor()) && getULB().hasColor(up.getColor())
                && getULB().hasColor(left.getColor())) count++;
        return count;
    }

    public void rightHand() {
        r();
        u();
        rRev();
        uRev();
    }

    public void rightHand(int n) {
        for (int i = 0; i < n; i++) {
            rightHand();
        }
    }

    public void leftHand() {
        fRev();
        uRev();
        f();
        u();
    }

    public void leftHand(int n) {
        for (int i = 0; i < n; i++) {
            leftHand();
        }
    }

    public void placeSecondFromUp() {
        if (getUF().hasColor(front.getColor()) && getUF().hasColor(right.getColor())) {
            ;
        } else if (getUB().hasColor(front.getColor()) && getUB().hasColor(right.getColor())) {
            u2();
        } else if (getUR().hasColor(front.getColor()) && getUR().hasColor(right.getColor())) {
            u();
        } else if (getUL().hasColor(front.getColor()) && getUL().hasColor(right.getColor())) {
            uRev();
        } else {
            return;
        }

        if (getUF().getFront() == front.getColor()) {
            u();
            rightHand();
            leftHand();
        } else {
            u2();
            leftHand();
            rightHand();
        }

        getRF().setRightPlace(true);
        if (getRF().getRight() != right.getColor() || getRF().getFront() != front.getColor()) {
            throw new RuntimeException("косяк в детальки второго уровня");
        }
    }

    public void placeCornerFromUp() {
        int i = 0;
        for (i = 0; i < 4; i++) {
            if (getURF().hasColor(right.getColor())
                    && getURF().hasColor(front.getColor())
                    && getURF().hasColor(down.getColor())) {
                break;
            }
            u();
        }
        if (i == 4) return ;

        if (getURF().getRight() == Colors.WHITE) {
            rightHand();
        } else if (getURF().getFront() == Colors.WHITE) {
            leftHand();
        } else {
            rightHand(3);
        }
        getDRF().setRightPlace(true);
        if (getDRF().getRight() != right.getColor()
                || getDRF().getFront() != front.getColor()
                || getDRF().getDown() != down.getColor()) {
            throw new RuntimeException("косяк в проставлении угла");
        }
    }

    public void checkCorners() {
        if (getDRF().getRight() == right.getColor()
                && getDRF().getFront() == front.getColor()
                && getDRF().getDown() == down.getColor()) {
            getDRF().setRightPlace(true);
        }
        if (getDLF().getLeft() == left.getColor()
                && getDLF().getFront() == front.getColor()
                && getDLF().getDown() == down.getColor()) {
            getDLF().setRightPlace(true);
        }
        if (getDRB().getRight() == right.getColor()
                && getDRB().getBack() == back.getColor()
                && getDRB().getDown() == down.getColor()) {
            getDRB().setRightPlace(true);
        }
        if (getDLB().getLeft() == left.getColor()
                && getDLB().getBack() == front.getColor()
                && getDLB().getDown() == down.getColor()) {
            getDLB().setRightPlace(true);
        }
    }

    public void checkSeconds() {
        if (getRF().getRight() == right.getColor()
                && getRF().getFront() == front.getColor()) {
            getRF().setRightPlace(true);
        }
        if (getLF().getLeft() == left.getColor()
                && getLF().getFront() == front.getColor()) {
            getLF().setRightPlace(true);
        }
        if (getRB().getRight() == right.getColor()
                && getRB().getBack() == back.getColor()) {
            getRB().setRightPlace(true);
        }
        if (getLB().getLeft() == left.getColor()
                && getLB().getBack() == back.getColor()) {
            getLB().setRightPlace(true);
        }
    }

    private void whiteToDown() {
        if (getUF().getUp() == Colors.WHITE && getUF().getFront() == right.getColor()) {
            uRev();
        }
        if (getUB().getUp() == Colors.WHITE && getUB().getBack() == right.getColor()) {
            u();
        }
        if (getUL().getUp() == Colors.WHITE && getUL().getLeft() == right.getColor()) {
            u2();
        }
        if (getUR().getUp() == Colors.WHITE && getUR().getRight() == right.getColor()) {
            r2();
            getDR().setRightPlace(true);
        }
    }

    private void whiteToUp() {
        if (getRF().getFront() == Colors.WHITE) {
            rotYellow();
            r();
            if (getRF().isRightPlace()) {
                rotYellow();
                rRev();
            }
        }
        if (getRB().getBack() == Colors.WHITE) {
            rotYellow();
            rRev();
            if (getRF().isRightPlace()) {
                rotYellow();
                r();
            }
        }
        if (getDR().getDown() == Colors.WHITE && !getDR().isRightPlace()) {
            rotYellow();
            r2();
        }
    }

    private void rotYellow() {
        while (getUR().getUp() == Colors.WHITE) {
            u();
        }
    }

    private int rotWhite() {
        int i = 0;
        while (getDR().isRightPlace) {
            d();
            i++;
        }
        return i;
    }



    public void print() {
        System.out.println(rubic.toString());
    }

    public void init() {
        logger.init();
    }

    public void printLog() {
        logger.printLog();
    }
}
