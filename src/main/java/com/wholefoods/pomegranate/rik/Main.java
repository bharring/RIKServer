package com.wholefoods.pomegranate.rik;

import jpos.JposException;

import static java.lang.Thread.sleep;

import java.util.Timer; 

import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws JposException {


        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();
        
        for(URL url: urls){
            System.out.println(url.getFile());
        }

        final RIK rik = new RIK();
        rik.open();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    rik.exit();
                } catch (JposException e) {
                    e.printStackTrace();
                }
            }
        });

        Server server = new Server("localhost", 8887);

        ServerTimer serverTimer = new ServerTimer();
        serverTimer.rik = rik;
        serverTimer.server = server;

        Timer timer = new Timer(); 
        timer.schedule(serverTimer, 0, 100);

        server.run();
    }
}

