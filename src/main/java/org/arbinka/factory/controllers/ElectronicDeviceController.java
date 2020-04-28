package org.arbinka.factory.controllers;


import org.arbinka.factory.dto.ElectronicDeviceTransfer;
import org.arbinka.factory.models.ElectronicDevice;
import org.arbinka.factory.repositories.ElectronicDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest controller отвечающий за логику взаимодействия с таблицей ElectronicDevice
 */
@RequestMapping("electronicDevice")
@RestController
public class ElectronicDeviceController {

    @Autowired
    private ElectronicDeviceRepository electronicDeviceRepository;

    /**
     * Получение списка электронных устройств
     *
     * @return список электронных устрйоств
     */
    @GetMapping(value = "/getAllDevices", produces = MediaType.APPLICATION_JSON)
    public List<ElectronicDevice> getAllDevices() {
        return electronicDeviceRepository.findAll();
    }

    /**
     * Добавления нового устройства
     *
     * @param electronicDeviceTransfer - DTO для добавления.
     * @return информацию о результате добавления нового устройства
     */
    @PostMapping(value = "/addDevice", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Map<String, Object>> addDevice(@RequestBody ElectronicDeviceTransfer electronicDeviceTransfer) {
        Map<String, Object> result = new HashMap<>();
        String message = "Чтобы добавить новое электронное устройство необходимо ввести: ";
        boolean isSuccess = true;

        if (electronicDeviceTransfer.getDeviceId() == null) {
            message += "его индефикатор(deviceId)";
            isSuccess = false;
        } else {
            if (electronicDeviceRepository.findByDeviceId(electronicDeviceTransfer.getDeviceId()) != null) {
                result.put("message", "Устройство с таким уже индефикатором существует.");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
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
            result.put("message", message);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Удаление устройства
     *
     * @param deviceId - индефикатор удаляемого устройства
     * @return информация о результате удаления (успешно/нет)
     */
    @DeleteMapping(value = "/deleteDevice/{deviceId}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Map<String, Object>> deleteDevice(@PathVariable("deviceId") String deviceId) {
        Map<String, Object> result = new HashMap<>();

        ElectronicDevice electronicDevice = electronicDeviceRepository.findByDeviceId(deviceId);

        if (electronicDevice != null) {
            electronicDeviceRepository
                    .delete(electronicDevice);

            result.put("message", "Устройство успешно удалено.");

            return new ResponseEntity<>(result, HttpStatus.OK);

        }

        result.put("message", "Устройства с таким индефикатором(deviceId) не существует.");
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);


    }
}
