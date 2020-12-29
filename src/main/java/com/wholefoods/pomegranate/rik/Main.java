package com.wholefoods.pomegranate.rik;

import jpos.JposException;

import static java.lang.Thread.sleep;

import java.util.Timer;

public class Main {

    public static void main(String[] args) throws JposException {
        
        // start WebSocket thread
        Server server = new Server("localhost", 8887);
        Thread serverThread = new Thread(server);
        serverThread.start();

        // start RIK thread
        final RIK rik = new RIK();
        Thread rikThread = new Thread(rik);
        rikThread.start();

        // start weight polling thread
        Timer timer = new Timer();
        ServerTimer serverTimer = new ServerTimer(rik, server);
        timer.schedule(serverTimer, 1000, 1000);
    }
}

