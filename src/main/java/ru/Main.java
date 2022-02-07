package ru;

public class Main {
    public static void main(String[] args) {
        Rubic rubic = new Rubic();
        rubic.dRev();
        rubic.dRev();
        rubic.r();
        rubic.l();
        rubic.u();
        rubic.d();
        rubic.r();
        rubic.b();
        rubic.u();
        rubic.d();
        rubic.f();
        rubic.l();
        rubic.b();
//        rubic.r();
//        rubic.rRev();
//        rubic.rRev();

        System.out.println(rubic.toString());

        Algorithm algorithm = new Algorithm(rubic);
        algorithm.orientationWhiteCross();
        algorithm.insertCornersInFirstLayer();
        algorithm.secondLayer();
        algorithm.thirdLayer();
        algorithm.printLog();

        System.out.println(rubic.toString());
    }
}
