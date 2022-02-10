package ru;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String USAGE = "" +
            "java Main [-v] U D ... some Rubik command ... R F B\n" +
            "visualization flag -v" +
            "command example: U D L R F B U' D' L' R' F' B' 2U 2D 2L 2R 2F 2B";

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println(USAGE);
            return;
        }

        boolean vis = false;
        List<String> input = Arrays.asList(args);
        if (!input.isEmpty() && input.get(0).trim().equals("-v")) {
            input.remove(0);
            vis = true;
        }
        List<String> output = getCommandsForRubik(input);

        if (vis) {
            visualization(input, output);
        }

        String line = output.stream().collect(Collectors.joining(" "));
        if (!line.isEmpty()) {
            System.out.println(line);
        }
    }

    private static void visualization(List<String> input, List<String> output) {
        Rubik rubik = new Rubik();
        input.stream().forEach(c -> useCommand(rubik, c.trim(), true));
        System.out.println("start position");
        output.stream().forEach(c -> useCommand(rubik, c.trim(), true));
    }

    public static List<String> getCommandsForRubik(List<String> args) {
        Rubik rubik = new Rubik();
        args.stream().forEach(c -> useCommand(rubik, c.trim(), false));

        Algorithm algorithm = new Algorithm(rubik);
        algorithm.orientationWhiteCross();
        algorithm.insertCornersInFirstLayer();
        algorithm.secondLayer();
        algorithm.thirdLayer();

        return logsOptimization(algorithm.getLogs());
    }

    public static void useCommand(Rubik rubik, String command, boolean vis) {
        switch (command) {
            case "" : break;
            case "D" : rubik.d(); break;
            case "U" : rubik.u(); break;
            case "L" : rubik.l(); break;
            case "R" : rubik.r(); break;
            case "F" : rubik.f(); break;
            case "B" : rubik.b(); break;
            case "2D" : rubik.d(); rubik.d(); break;
            case "2U" : rubik.u(); rubik.u(); break;
            case "2L" : rubik.l(); rubik.l(); break;
            case "2R" : rubik.r(); rubik.r(); break;
            case "2F" : rubik.f(); rubik.f(); break;
            case "2B" : rubik.b(); rubik.b(); break;
            case "D'" : rubik.dRev(); break;
            case "U'" : rubik.uRev(); break;
            case "L'" : rubik.lRev(); break;
            case "R'" : rubik.rRev(); break;
            case "F'" : rubik.fRev(); break;
            case "B'" : rubik.bRev(); break;
            default:
                throw new RuntimeException("Invalid command " + command);
        }
        if (vis) {
            System.out.println(rubik.toString());
        }
    }

    public static List<String> logsOptimization(List<String> list) {
        List<String> answer = new ArrayList<>();

        Deque<String> queue = new LinkedList<>(list);
        while (queue.size() > 0) {
            int count = 1;
            String command = queue.pop();
            for (int i = 0; i < 3; i++) {
                if (queue.size() > 0 && command.equals(queue.peekFirst())) {
                    queue.pop();
                    count++;
                } else {
                    break ;
                }
            }
            if (count == 4) continue;
            if (count == 3) answer.add(command.concat("'").replace("''", ""));
            if (count == 2) answer.add("2" + command.replaceAll("'", ""));
            if (count == 1) answer.add(command);
        }
        return answer;
    }


}
