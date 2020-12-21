package com.wholefoods.pomegranate.rik;

import jpos.JposException;
import jpos.Scale;
import jpos.util.JposPropertiesConst;

public class RIK {
    Scale scale;

    public void open() throws JposException {
        System.setProperty(JposPropertiesConst.JPOS_POPULATOR_FILE_PROP_NAME, "/opt/bizerba/posscale/jpos.xml");

        scale = new jpos.Scale();
        scale.open("BizerbaScale1");
        scale.claim(2);
        scale.setDeviceEnabled(true);
    }

    public int getScaleLiveWeight() throws JposException {
        return scale.getScaleLiveWeight();
    }

    public void exit() throws JposException {
        scale.setDeviceEnabled(false);
        scale.close();
    }

}

