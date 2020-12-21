package com.wholefoods.pomegranate.rik;

import jpos.JposException;

import static java.lang.Thread.sleep;

import org.json.JSONObject;


public class Main {
    public static void main(String[] args) throws JposException, InterruptedException {

        final RIK rik = new RIK();
        rik.open();

        // Server server = new Server("localhost", 8887);
        // server.run();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    rik.exit();
                } catch (JposException e) {
                    e.printStackTrace();
                }
            }
        });

        while (true) {
            sleep(50);
            int weight = rik.getScaleLiveWeight();
            System.out.println(weight);
            JSONObject json = new JSONObject();
            json.put("weight", weight);
            // server.broadcast(json.toString());
        }
    }
}

