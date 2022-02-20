package ru;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class MainTest {
    private static Random random = new Random();
    private static String[] commands = {
            "U", "D", "L", "R", "F", "B",
            "U'", "D'", "L'", "R'", "F'", "B'",
            "2U", "2D", "2L", "2R", "2F", "2B"};

    @Test
    void useCommandTestCase() {
        int count = 0;
        int n = 1000;
        for (int i = 0; i < n; i++) {
            count += test();
        }
        System.out.println("Операций в среднем " + (count / n));
    }

    private static int test() {
        int count = 0;
        List<String> strings = generateCommands(random.nextInt(30));
        System.out.println(strings.stream().collect(Collectors.joining(" ")));
        List<String> commandsForRubik = Main.getCommandsForRubik(strings);
        count = commandsForRubik.size();
        System.out.println(commandsForRubik.stream().collect(Collectors.joining(" ")));
        System.out.println(commandsForRubik.size());

        strings.addAll(commandsForRubik);
        commandsForRubik = Main.getCommandsForRubik(strings);

        Assertions.assertTrue(commandsForRubik.isEmpty());
        return count;
    }

    private static void test2() {
        List<String> strings = Arrays.asList("L' R D L' 2F F 2D".split(" "));
        System.out.println(strings.stream().collect(Collectors.joining(" ")));
        List<String> commandsForRubik = Main.getCommandsForRubik(strings);
        System.out.println(commandsForRubik.stream().collect(Collectors.joining(" ")));

        ArrayList<String> strings1 = new ArrayList<>(strings);
        strings1.addAll(commandsForRubik);
        System.out.println(strings1.stream().collect(Collectors.joining(" ")));
        commandsForRubik = Main.getCommandsForRubik(strings1);

//        List<String> strings2 = Arrays.asList("D R R' U B F' R' 2U B' 2B 2R 2R R F' U F U 2F 2F 2B D D D D D B B D R D D D F D D D D R U U R' B F F U' L L U U B B U U U U F U F' U' F U F' U' F U F' U' U L U L' U' L U L' U' L U L' U' U U U U R U R' U' U R U R' U' R U R' U' R U R' U' U U U U B U B' U' B U B' U' B U B' U' B U B' U' U U B U B' U'".split(" "));
//        System.out.println(strings2.stream().collect(Collectors.joining(" ")));
//        List<String> commandsForRubik2 = Main.getCommandsForRubik(strings2);
//        System.out.println(commandsForRubik2.stream().collect(Collectors.joining(" ")));
    }


    private static List<String> generateCommands(int count) {
        List<String> list = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            list.add(commands[random.nextInt(commands.length)]);
        }
        return list;
    }
}