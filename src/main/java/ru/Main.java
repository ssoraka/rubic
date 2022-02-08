package ru;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Rubic rubic = new Rubic();
        Arrays.asList(args).stream().forEach(c -> useCommand(rubic, c.trim()));

        System.out.println(rubic.toString());

        Algorithm algorithm = new Algorithm(rubic);
        algorithm.orientationWhiteCross();
        algorithm.insertCornersInFirstLayer();
        algorithm.secondLayer();
        algorithm.thirdLayer();

        printLog(algorithm.getLogs());

        System.out.println(rubic.toString());
    }

    public static void useCommand(Rubic rubic, String command) {
        switch (command) {
            case "" : break;
            case "D" : rubic.d(); break;
            case "U" : rubic.u(); break;
            case "L" : rubic.l(); break;
            case "R" : rubic.r(); break;
            case "F" : rubic.f(); break;
            case "B" : rubic.b(); break;
            case "2D" : rubic.d(); rubic.d(); break;
            case "2U" : rubic.u(); rubic.u(); break;
            case "2L" : rubic.l(); rubic.l(); break;
            case "2R" : rubic.r(); rubic.r(); break;
            case "2F" : rubic.f(); rubic.f(); break;
            case "2B" : rubic.b(); rubic.b(); break;
            case "D'" : rubic.dRev(); break;
            case "U'" : rubic.uRev(); break;
            case "L'" : rubic.lRev(); break;
            case "R'" : rubic.rRev(); break;
            case "F'" : rubic.fRev(); break;
            case "B'" : rubic.bRev(); break;
            default:
                throw new RuntimeException("Invalid command " + command);
        }
    }

    public static void printLog(List<String> list) {
        Deque<String> queue = new LinkedList<>(list);
        int size = 0;
        while (queue.size() > 0) {
            int count = 1;
            String command = queue.pop();
            for (int i = 0; i < 3; i++) {
                if (queue.size() > 0 && command.equals(queue.peekFirst())) {
                    queue.pop();
                    count++;
                }
            }
            if (count == 4) continue;
            if (count == 3) System.out.print(command.concat("'").replace("''", "") + " ");
            if (count == 2) System.out.print("2" + command.replace("''", "") + " ");
            if (count == 1) System.out.print(command + " ");
            size++;
        }
        System.out.println(size);
    }
}
