package ru;

public enum Colors {
    BLACK(0, "X", "X"),
    WHITE(1, "W", "D"),
    YELLOW(2, "Y", "U"),
    GREEN(4, "G", "B"),
    BLUE(8, "B", "F"),
    ORANGE(16, "O", "L"),
    RED(32, "R", "R");

    int code;
    String letter;
    String command;

    Colors(int code, String letter, String command) {
        this.code = code;
        this.letter = letter;
        this.command = command;
    }
}
