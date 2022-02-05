package ru;

public class Main {
    public static void main(String[] args) {
        Rubic rubic = new Rubic();


        Rotator rotator = new Rotator(rubic);

        rotator.f();
        rotator.f();
        rotator.fRev();
        rotator.fRev();

        rotator.b();
        rotator.b();
        rotator.bRev();
        rotator.bRev();

        rotator.u();
        rotator.u();
        rotator.uRev();
        rotator.uRev();

        rotator.d();
        rotator.d();
        rotator.dRev();
        rotator.dRev();

        rotator.l();
        rotator.l();
        rotator.lRev();
        rotator.lRev();

        rotator.r();
        rotator.r();
        rotator.rRev();
        rotator.rRev();

        rubic.y();


//        rotator.r();
//        rotator.r();
//        rotator.rRev();
//        rotator.rRev();

        rotator.orientationWhiteCross();

        System.out.println(rubic.toString());
    }
}
