package org.arbinka.factory.repositories;

import org.arbinka.factory.models.ElectronicDevice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для взаимодействия с таблицой ElectronicDevice базы данный
 */
public interface ElectronicDeviceRepository extends JpaRepository<ElectronicDevice, String> {

}
