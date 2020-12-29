package com.wholefoods.pomegranate.rik;

import jpos.JposException;
import jpos.Scale;
import jpos.util.JposPropertiesConst;

public class RIK implements Runnable {
    Scale scale;

    public void run() {
        System.setProperty(JposPropertiesConst.JPOS_POPULATOR_FILE_PROP_NAME, "/opt/bizerba/posscale/jpos.xml");

        try {
            scale = new jpos.Scale();
            scale.open("BizerbaScale1");
            scale.claim(1);
            scale.setDeviceEnabled(true);

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        scale.setDeviceEnabled(false);
                        scale.close();
                    } catch (JposException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JposException e) {
            e.printStackTrace();
        }
    }

    public int getScaleLiveWeight() throws JposException {
        return scale.getScaleLiveWeight();
    }
}

