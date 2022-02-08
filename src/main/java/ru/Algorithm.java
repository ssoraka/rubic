package ru;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    Rubik rubik;
    List<String> logs = new ArrayList<>();

    public Algorithm(Rubik rubik) {
        this.rubik = rubik;
    }

    public List<String> getLogs() {
        return logs;
    }

    private void f() {
        log(frontColor(), false);
        rubik.f();
    }

    private void fRev() {
        log(frontColor(), true);
        rubik.fRev();
    }

    private void b() {
        log(backColor(), false);
        rubik.b();
    }

    private void bRev() {
        log(backColor(), true);
        rubik.bRev();
    }

    private void u() {
        log(upColor(), false);
        rubik.u();
    }

    private void u2() {
        u();
        u();
    }

    private void uRev() {
        log(upColor(), true);
        rubik.uRev();

    }

    private void d() {
        log(downColor(), false);
        rubik.d();
    }

    private void d(int n) {
        for (int i = 0; i < n; i++) {
            d();
        }
    }

    private void dRev() {
        log(downColor(), true);
        rubik.dRev();
    }

    private void l() {
        log(leftColor(), false);
        rubik.l();
    }

    private void lRev() {
        log(leftColor(), true);
        rubik.lRev();
    }

    private void r() {
        log(rightColor(), false);
        rubik.r();
    }

    private void r2() {
        r();
        r();
    }

    private void rRev() {
        log(rightColor(), true);
        rubik.rRev();
    }

    private Cube getDF() {
        return rubik.getDF();
    }

    private Cube getDB() {
        return rubik.getDB();
    }

    private Cube getDL() {
        return rubik.getDL();
    }

    private Cube getDR() {
        return rubik.getDR();
    }

    private Cube getUF() {
        return rubik.getUF();
    }

    private Cube getUB() {
        return rubik.getUB();
    }

    private Cube getUL() {
        return rubik.getUL();
    }

    private Cube getUR() {
        return rubik.getUR();
    }

    private Cube getRF() {
        return rubik.getRF();
    }

    private Cube getRB() {
        return rubik.getRB();
    }

    private Cube getLF() {
        return rubik.getLF();
    }

    private Cube getLB() {
        return rubik.getLB();
    }

    private Cube getURF() {
        return rubik.getURF();
    }

    private Cube getURB() {
        return rubik.getURB();
    }

    private Cube getULF() {
        return rubik.getULF();
    }

    private Cube getULB() {
        return rubik.getULB();
    }

    private Cube getDRF() {
        return rubik.getDRF();
    }

    private Cube getDRB() {
        return rubik.getDRB();
    }

    private Cube getDLF() {
        return rubik.getDLF();
    }

    private Cube getDLB() {
        return rubik.getDLB();
    }

    private Colors rightColor() {
        return rubik.getRightColor();
    }

    private Colors leftColor() {
        return rubik.getLeftColor();
    }

    private Colors upColor() {
        return rubik.getUpColor();
    }

    private Colors downColor() {
        return rubik.getDownColor();
    }

    private Colors frontColor() {
        return rubik.getFrontColor();
    }
    private Colors backColor() {
        return rubik.getBackColor();
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
                if (getDR().getDown() == Colors.WHITE && getDR().getRight() == rightColor()) rightPos++;
                if (getDL().getDown() == Colors.WHITE && getDL().getLeft() == leftColor()) rightPos++;
                if (getDF().getDown() == Colors.WHITE && getDF().getFront() == frontColor()) rightPos++;
                if (getDB().getDown() == Colors.WHITE && getDB().getBack() == backColor()) rightPos++;
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
                if (getDR().getDown() == Colors.WHITE && getDR().getRight() == rightColor()) {
                    getDR().setRightPlace(true);
                }
                rubik.x();
            }
            if (max == 4) return;
        }

        //пробуем поставить правильным местом
        for (int i = 0; i < 8; i++) {
            if (getDR().isRightPlace()) {
                rubik.x();
                continue;
            }
            if (getRF().getFront() == Colors.WHITE && getRF().getRight() == rightColor()) {
                rotYellow();
                rRev();
            }
            if (getRB().getBack() == Colors.WHITE && getRB().getRight() == rightColor()) {
                rotYellow();
                r();
            }
            if (getUR().getUp() == Colors.WHITE && getUR().getRight() == rightColor()) r2();
            if (getDR().getDown() == Colors.WHITE && getDR().getRight() == rightColor()) {
                getDR().setRightPlace(true);
            }
            rubik.x();
        }

        //поднимаем все наверх
        for (int i = 0; i < 8; i++) {
            whiteToUp();
            rubik.x();
        }

        //борьба с неправильно расположенными детальками
        for (int i = 0; i < 8; i++) {
            if (getUR().getRight() != Colors.WHITE && getDR().getRight() != Colors.WHITE) {
                rubik.x();
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
            rubik.x();
            whiteToUp();
            rubik.xRev();
            rubik.xRev();
            whiteToUp();
            rubik.x();
            d(4 - num);
            rubik.x();
        }

        for (int i = 0; i < 8; i++) {
            whiteToUp();
            rubik.x();
        }

        //опускаем вниз
        for (int i = 0; i < 4; i++) {
            if (getDR().isRightPlace()) {
                rubik.x();
                continue;
            }
            whiteToDown();
            rubik.x();
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
                rubik.x();
            }

            for (int i = 0; i < 4; i++) {
                if (!getDRF().isRightPlace()) {
                    rightHand();
                    break ;
                }
                rubik.x();
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
                rubik.x();
            }

            for (int i = 0; i < 4; i++) {
                if (!getRF().isRightPlace()) {
                    rightHand();
                    leftHand();
                    break ;
                }
                rubik.x();
            }
        }
    }

    public void thirdLayer() {
        yellowCross();
        placeThirdCorners();
        rotateYellowCorners();
        finalStep();
        if (!rubik.isComplete()) {
            throw new RuntimeException("кубик не собрался");
        }
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
            rubik.x();
        }
        if (getUR().getUp() == Colors.YELLOW && getUL().getUp() == Colors.YELLOW) {
            f();
            rightHand();
            fRev();
        } else {
            while (getUB().getUp() != Colors.YELLOW || getUL().getUp() != Colors.YELLOW) {
                rubik.x();
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
            if (getULF().hasColor(frontColor()) && getULF().hasColor(upColor()) && getULF().hasColor(leftColor()) &&
                    getULB().hasColor(backColor()) && getULB().hasColor(upColor()) && getULB().hasColor(leftColor())) {
                break ;
            }
            rubik.x();
        }

        //TODO bug
        if (!getULF().hasColor(frontColor()) || !getULF().hasColor(upColor()) || !getULF().hasColor(leftColor()))  {
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
        rubik.y();
        rubik.y();
        for (int i = 0; i < 4 && getDRF().getDown() == Colors.YELLOW; i++) {
            rubik.x();
        }
        for (int i = 0; i < 8; i++) {
            while (getDRF().getDown() != Colors.YELLOW) {
                rightHand(2);
            }
            d();
        }
        rubik.y();
        rubik.y();

        if (getURF().getUp() != Colors.YELLOW || getURB().getUp() != Colors.YELLOW
                || getULF().getUp() != Colors.YELLOW || getULB().getUp() != Colors.YELLOW ) {
            throw new RuntimeException("не собрался верх");
        }
    }

    private void finalStep() {
        for (int i = 0; i < 100 && (!haveCompleteLine() || !isComplete()); i++) {
            rightHand();
            rubik.x();
            leftHand();
            rubik.xRev();
            rightHand(5);
            rubik.x();
            leftHand(5);
            rubik.xRev();
        }
        if (!isComplete()) {
            throw new RuntimeException("java.util.Date итоге что-то не собралось...");
        }
    }

    private boolean haveCompleteLine() {
        for (int i = 0; i < 4; i++) {
            Colors color = frontColor();
            if (getURF().getFront() == color && getUF().getFront() == color && getULF().getFront() == color) {
                return true;
            }
            rubik.x();
        }
        return false;
    }

    private boolean isComplete() {
        if (getURF().getFront() != frontColor() || getUF().getFront() != frontColor()
                || getULF().getFront() != frontColor()) return false;
        if (getURB().getBack() != backColor() || getUB().getBack() != backColor()
                || getULB().getBack() != backColor()) return false;
        if (getURF().getRight() != rightColor() || getUR().getRight() != rightColor()
                || getURB().getRight() != rightColor()) return false;
        if (getULF().getLeft() != leftColor() || getUL().getLeft() != leftColor()
                || getULB().getLeft() != leftColor()) return false;
        return true;
    }

    private int getRightThirdCornerCount() {
        int count = 0;
        if (getURF().hasColor(frontColor()) && getURF().hasColor(upColor())
                && getURF().hasColor(rightColor())) count++;
        if (getURB().hasColor(backColor()) && getURB().hasColor(upColor())
                && getURB().hasColor(rightColor())) count++;
        if (getULF().hasColor(frontColor()) && getULF().hasColor(upColor())
                && getULF().hasColor(leftColor())) count++;
        if (getULB().hasColor(backColor()) && getULB().hasColor(upColor())
                && getULB().hasColor(leftColor())) count++;
        return count;
    }

    private void rightHand() {
        r();
        u();
        rRev();
        uRev();
    }

    private void rightHand(int n) {
        for (int i = 0; i < n; i++) {
            rightHand();
        }
    }

    private void leftHand() {
        fRev();
        uRev();
        f();
        u();
    }

    private void leftHand(int n) {
        for (int i = 0; i < n; i++) {
            leftHand();
        }
    }

    private void placeSecondFromUp() {
        if (getUF().hasColor(frontColor()) && getUF().hasColor(rightColor())) {
            ;
        } else if (getUB().hasColor(frontColor()) && getUB().hasColor(rightColor())) {
            u2();
        } else if (getUR().hasColor(frontColor()) && getUR().hasColor(rightColor())) {
            u();
        } else if (getUL().hasColor(frontColor()) && getUL().hasColor(rightColor())) {
            uRev();
        } else {
            return;
        }

        if (getUF().getFront() == frontColor()) {
            u();
            rightHand();
            leftHand();
        } else {
            u2();
            leftHand();
            rightHand();
        }

        getRF().setRightPlace(true);
        if (getRF().getRight() != rightColor() || getRF().getFront() != frontColor()) {
            throw new RuntimeException("косяк в детальки второго уровня");
        }
    }

    private void placeCornerFromUp() {
        int i = 0;
        for (i = 0; i < 4; i++) {
            if (getURF().hasColor(rightColor())
                    && getURF().hasColor(frontColor())
                    && getURF().hasColor(downColor())) {
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
        if (getDRF().getRight() != rightColor()
                || getDRF().getFront() != frontColor()
                || getDRF().getDown() != downColor()) {
            throw new RuntimeException("косяк в проставлении угла");
        }
    }

    private void checkCorners() {
        if (getDRF().getRight() == rightColor()
                && getDRF().getFront() == frontColor()
                && getDRF().getDown() == downColor()) {
            getDRF().setRightPlace(true);
        }
        if (getDLF().getLeft() == leftColor()
                && getDLF().getFront() == frontColor()
                && getDLF().getDown() == downColor()) {
            getDLF().setRightPlace(true);
        }
        if (getDRB().getRight() == rightColor()
                && getDRB().getBack() == backColor()
                && getDRB().getDown() == downColor()) {
            getDRB().setRightPlace(true);
        }
        if (getDLB().getLeft() == leftColor()
                && getDLB().getBack() == backColor()
                && getDLB().getDown() == downColor()) {
            getDLB().setRightPlace(true);
        }
    }

    private void checkSeconds() {
        if (getRF().getRight() == rightColor()
                && getRF().getFront() == frontColor()) {
            getRF().setRightPlace(true);
        }
        if (getLF().getLeft() == leftColor()
                && getLF().getFront() == frontColor()) {
            getLF().setRightPlace(true);
        }
        if (getRB().getRight() == rightColor()
                && getRB().getBack() == backColor()) {
            getRB().setRightPlace(true);
        }
        if (getLB().getLeft() == leftColor()
                && getLB().getBack() == backColor()) {
            getLB().setRightPlace(true);
        }
    }

    private void whiteToDown() {
        if (getUF().getUp() == Colors.WHITE && getUF().getFront() == rightColor()) {
            uRev();
        }
        if (getUB().getUp() == Colors.WHITE && getUB().getBack() == rightColor()) {
            u();
        }
        if (getUL().getUp() == Colors.WHITE && getUL().getLeft() == rightColor()) {
            u2();
        }
        if (getUR().getUp() == Colors.WHITE && getUR().getRight() == rightColor()) {
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
            if (getRB().isRightPlace()) {
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

    private void print() {
        System.out.println(rubik.toString());
    }

    private void log(Colors color, boolean isRev) {
        if (!isRev) {
            logs.add(color.command);
        } else {
            logs.add(color.command + "'");
        }
    }
}
