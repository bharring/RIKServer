package com.wholefoods.pomegranate.rik;

import java.util.TimerTask;

import jpos.JposException;

import org.json.JSONObject;


class ServerTimer extends TimerTask {
    RIK rik;
    Server server;

    ServerTimer(RIK _rik, Server _server) {
        rik = _rik;
        server = _server;
    }

    public void run() {
        try {
            if (rik != null && server != null) {
                int netWeight = rik.getScaleLiveWeight();
                JSONObject json = new JSONObject();
                json.put("netWeight", netWeight);
                // System.out.println(json.toString());
                server.broadcast(json.toString());
            }
        } catch (JposException e) {
            e.printStackTrace();
        }
    }
}