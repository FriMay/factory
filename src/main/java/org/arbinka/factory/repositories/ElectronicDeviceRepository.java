package org.arbinka.factory.repositories;

import org.arbinka.factory.models.ElectronicDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Репозиторий для взаимодействия с таблицой ElectronicDevice базы данный
 */
public interface ElectronicDeviceRepository extends JpaRepository<ElectronicDevice, Integer> {

    /**
     * Запрос на получение устройства по его уникальному индефикатору
     *
     * @param deviceId - индефикатор устройства
     * @return модель ElectronicDevice
     */
    @Query("SELECT e FROM ElectronicDevice e WHERE e.deviceId = ?1")
    ElectronicDevice findByDeviceId(String deviceId);

}
