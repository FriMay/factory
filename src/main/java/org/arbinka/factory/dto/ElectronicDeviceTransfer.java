package org.arbinka.factory.dto;

import java.io.Serializable;
import java.util.Date;

public class ElectronicDeviceTransfer implements Serializable {
    private String deviceId;
    private Date date;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
