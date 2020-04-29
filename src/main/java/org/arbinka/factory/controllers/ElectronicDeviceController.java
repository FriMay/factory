package org.arbinka.factory.controllers;


import org.arbinka.factory.dto.ElectronicDeviceTransfer;
import org.arbinka.factory.exceptions.BadRequestException;
import org.arbinka.factory.models.ElectronicDevice;
import org.arbinka.factory.repositories.ElectronicDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ElectronicDevice> getAllDevices() {
        return electronicDeviceRepository.findAll();
    }

    /**
     * Добавления нового устройства
     *
     * @param electronicDeviceTransfer - DTO для добавления.
     * @return информацию о результате добавления нового устройства
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> addDevice(@RequestBody ElectronicDeviceTransfer electronicDeviceTransfer) {
        Map<String, Object> result = new HashMap<>();
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
            result.put("message", "Устройство успешно добавлено.");
            return new ResponseEntity<>(result, HttpStatus.OK);
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
    @DeleteMapping(value = "/{deviceId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> deleteDevice(@PathVariable("deviceId") String deviceId) {
        Map<String, Object> result = new HashMap<>();

        Optional<ElectronicDevice> electronicDevice = electronicDeviceRepository.findById(deviceId);

        if (electronicDevice.isPresent()) {
            electronicDeviceRepository
                    .delete(electronicDevice.get());

            result.put("message", "Устройство успешно удалено.");

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        throw new BadRequestException("Устройства с таким индефикатором(deviceId) не существует.");

    }
}
