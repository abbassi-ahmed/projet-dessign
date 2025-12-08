package com.polytech.game.patterns.singleton;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//  game logger singleton class
public class GameLogger {
    private static GameLogger instance;
    private PrintWriter fileWriter;
    private final DateTimeFormatter timeFormatter;
    private static final String LOG_FILE = "logs/game.log";

    private GameLogger() {
        timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            fileWriter = new PrintWriter(new FileWriter(LOG_FILE, true), true);
            log("INFO", "GameLogger initialized");
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
        }
    }

 
    public static synchronized GameLogger getInstance() {
        if (instance == null) {
            instance = new GameLogger();
        }
        return instance;
    }

    
    public void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(timeFormatter);
        String logEntry = String.format("[%s] [%s] %s", timestamp, level, message);

        System.out.println(logEntry);
        if (fileWriter != null) {
            fileWriter.println(logEntry);
            fileWriter.flush();
        }
    }

  
    public void logStateChange(String entity, String fromState, String toState) {
        log("STATE", String.format("%s: %s -> %s", entity, fromState, toState));
    }

 
    public void logDecorator(String decoratorType, String targetEntity) {
        log("DECORATOR", String.format("%s applied to %s", decoratorType, targetEntity));
    }

 
    public void logDecoratorRemoval(String decoratorType, String targetEntity) {
        log("DECORATOR", String.format("%s removed from %s", decoratorType, targetEntity));
    }

  
    public void info(String message) {
        log("INFO", message);
    }
 
    public void error(String message) {
        log("ERROR", message);
    }

 
    public void close() {
        if (fileWriter != null) {
            log("INFO", "GameLogger shutting down");
            fileWriter.close();
        }
    }
}
