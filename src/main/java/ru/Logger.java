package ru;

import java.util.*;

public class Logger {
    List<String> list = new ArrayList<>();

    public void init() {
        list = new ArrayList<>();
    }

    public void log(String log) {
        list.add(log);
    }

    public void printLog() {
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
            if (count == 3) System.out.println(command + "'");
            if (count == 2) System.out.println(2 + command);
            if (count == 1) System.out.println(command);
            size++;
        }
        System.out.println(size);
    }
}
