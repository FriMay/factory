package org.arbinka.factory.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Модель "Электронное устройство" для предметной области "Завод"
 */
@Entity
public class ElectronicDevice {

    /**
     * Обязательное поле "Индефикатор устройства"
     */
    @Id
    @NotNull
    private String deviceId;

    /**
     * Обязательное поле "Дата изготовления" устрйоства.
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    public ElectronicDevice() {
    }

    public ElectronicDevice(String deviceId, Date date) {
        this.deviceId = deviceId;
        this.date = date;
    }

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
