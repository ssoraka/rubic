package ru;

public class Main {
    public static void main(String[] args) {
        Rubic rubic = new Rubic();


        Rotator rotator = new Rotator(rubic);
        rotator.dRev();
        rotator.dRev();
        rotator.r();
        rotator.l();
        rotator.u();
        rotator.d();
        rotator.r();
        rotator.b();
        rotator.u();
        rotator.d();
        rotator.f();
        rotator.l();
        rotator.b();
//        rotator.r();
//        rotator.rRev();
//        rotator.rRev();

        System.out.println(rubic.toString());

        rotator.init();
        rotator.orientationWhiteCross();
        rotator.insertCornersInFirstLayer();
        rotator.secondLayer();
        rotator.thirdLayer();
        rotator.printLog();

        System.out.println(rubic.toString());
    }
}
