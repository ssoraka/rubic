package ru;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLPortType;
import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class Test {
    private static Random random = new Random();
    private static String[] commands = {
            "U", "D", "L", "R", "F", "B",
            "U'", "D'", "L'", "R'", "F'", "B'",
            "2U", "2D", "2L", "2R", "2F", "2B"};

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        List<String> strings = generateCommands(random.nextInt(30));
        System.out.println(strings.stream().collect(Collectors.joining(" ")));
        List<String> commandsForRubik = Main.getCommandsForRubik(strings);
        System.out.println(commandsForRubik.stream().collect(Collectors.joining(" ")));
        System.out.println(commandsForRubik.size());

        strings.addAll(commandsForRubik);
        commandsForRubik = Main.getCommandsForRubik(strings);
//        Assert.that(commandsForRubik.isEmpty(), "bad");
    }

    private static void test2() {
        List<String> strings = Arrays.asList("U 2F F R' U 2F F F' L' L U' F' L U F' L L 2B 2U R' F' 2U L' 2R R".split(" "));
        System.out.println(strings.stream().collect(Collectors.joining(" ")));
        List<String> commandsForRubik = Main.getCommandsForRubik(strings);
        System.out.println(commandsForRubik.stream().collect(Collectors.joining(" ")));
    }

    private static List<String> generateCommands(int count) {
        List<String> list = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            list.add(commands[random.nextInt(commands.length)]);
        }
        return list;
    }
}