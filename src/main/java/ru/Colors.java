package ru;

public enum Colors {
    BLACK(0, "X", "X"),
    WHITE(1, "\033[47;30mW\033[00m", "D"),
    YELLOW(2, "\033[43;30mY\033[00m", "U"),
    GREEN(4, "\033[42;30mG\033[00m", "B"),
    BLUE(8, "\033[44;30mB\033[00m", "F"),
    ORANGE(16, "\033[45;30mO\033[00m", "L"),
    RED(32, "\033[41;30mR\033[00m", "R");

    int code;
    String letter;
    String command;

    Colors(int code, String letter, String command) {
        this.code = code;
        this.letter = letter;
        this.command = command;
    }
}
