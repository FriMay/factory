package org.arbinka.factory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Модель "Электронное устройство" для предметной области "Завод"
 */
@Entity
public class ElectronicDevice {

    /**
     * Индефикатор, генерируемый базой данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Обязательное поле "Индефикатор устройства"
     */
    @NotNull
    @Column(name = "device_id")
    private String deviceId;

    /**
     * Обязательное поле "Дата изготовления" устрйоства.
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
