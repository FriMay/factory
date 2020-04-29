package org.arbinka.factory.controllers;


import org.arbinka.factory.dto.AnswerTransfer;
import org.arbinka.factory.dto.ElectronicDeviceTransfer;
import org.arbinka.factory.exceptions.BadRequestException;
import org.arbinka.factory.models.ElectronicDevice;
import org.arbinka.factory.repositories.ElectronicDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Rest controller отвечающий за логику взаимодействия с таблицей ElectronicDevice
 */
@RequestMapping("devices")
@RestController
public class ElectronicDeviceController {

    @Autowired
    private ElectronicDeviceRepository electronicDeviceRepository;

    /**
     * Получение списка электронных устройств
     *
     * @return список электронных устрйоств
     */
    @GetMapping
    public AnswerTransfer getAllDevices() {
        return new AnswerTransfer(electronicDeviceRepository.findAll());
    }

    /**
     * Добавления нового устройства
     *
     * @param electronicDeviceTransfer - DTO для добавления.
     * @return информацию о результате добавления нового устройства
     */
    @PostMapping
    public AnswerTransfer addDevice(@RequestBody ElectronicDeviceTransfer electronicDeviceTransfer) {
        String message = "Чтобы добавить новое электронное устройство необходимо ввести: ";
        boolean isSuccess = true;

        if (electronicDeviceTransfer.getDeviceId() == null) {
            message += "его индефикатор(deviceId)";
            isSuccess = false;
        } else {
            if (electronicDeviceRepository.findById(electronicDeviceTransfer.getDeviceId()).isPresent()) {
                throw new BadRequestException("Устройство с таким индефикатором уже существует.");
            }
        }

        if (electronicDeviceTransfer.getDate() == null) {
            if (isSuccess) {
                message += "дату изготовления";
                isSuccess = false;
            } else {
                message += ", дату изготовления";
            }
            message += "(date)";
        }

        if (isSuccess) {
            ElectronicDevice electronicDevice =
                    new ElectronicDevice(
                            electronicDeviceTransfer.getDeviceId(),
                            electronicDeviceTransfer.getDate()
                    );
            electronicDeviceRepository.save(electronicDevice);
            return new AnswerTransfer("Устройство успешно добавлено.");
        } else {
            message += ".";
            throw new BadRequestException(message);
        }

    }

    /**
     * Удаление устройства
     *
     * @param deviceId - индефикатор удаляемого устройства
     * @return информация о результате удаления (успешно/нет)
     */
    @DeleteMapping(value = "/{deviceId}")
    public AnswerTransfer deleteDevice(@PathVariable("deviceId") String deviceId) {
        Optional<ElectronicDevice> electronicDevice = electronicDeviceRepository.findById(deviceId);

        if (electronicDevice.isPresent()) {
            electronicDeviceRepository
                    .delete(electronicDevice.get());

            return new AnswerTransfer("Устройство успешно удалено.");
        }

        throw new BadRequestException("Устройства с таким индефикатором(deviceId) не существует.");

    }
}
