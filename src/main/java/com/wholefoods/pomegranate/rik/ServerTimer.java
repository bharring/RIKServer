package com.wholefoods.pomegranate.rik;

import java.util.TimerTask; 

import jpos.JposException;

import org.json.JSONObject;


class ServerTimer extends TimerTask {
  RIK rik;
  Server server;

  public void run()
  { 
    try {

    int netWeight = rik.getScaleLiveWeight();
    System.out.println(netWeight);
    JSONObject json = new JSONObject();
    json.put("netWeight", netWeight);
    // server.broadcast(json.toString());
    } catch (JposException e) {
      e.printStackTrace();
    }
  } 
}