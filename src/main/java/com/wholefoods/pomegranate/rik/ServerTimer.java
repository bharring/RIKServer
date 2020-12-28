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
      JSONObject json = new JSONObject();
      json.put("netWeight", netWeight);
      // System.out.println(json.toString());
      server.broadcast(json.toString());
    } catch (JposException e) {
      e.printStackTrace();
    }
  } 
}