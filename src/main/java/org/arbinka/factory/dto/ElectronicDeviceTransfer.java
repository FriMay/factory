package org.arbinka.factory.dto;

import java.util.Date;

public class ElectronicDeviceTransfer {
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
