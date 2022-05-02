package com.vm.rest.controllers;

import com.vm.database.service.ServiceDatabaseUniversal;
import com.vm.database.table.TableModelUniversal;
import com.vm.modbus.entity.ModbusBodyDateRange;
import com.vm.rest.json.JsonBodyListForTableModelUniversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/database")
public class ControllerDatabaseUniversal {

    private final ServiceDatabaseUniversal service;

    @Autowired
    public ControllerDatabaseUniversal(final ServiceDatabaseUniversal service) {
        this.service = service;
    }

    @RequestMapping(value = "/range", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public JsonBodyListForTableModelUniversal rangeOfData(@RequestBody ModbusBodyDateRange modbusBodyDateRange){
        List<TableModelUniversal> tableModelList = service.findByAddressAndOffsetAndDateTimeBetween(modbusBodyDateRange.getAddress(),
                modbusBodyDateRange.getRegister(),
                modbusBodyDateRange.getFrom(),
                modbusBodyDateRange.getTo());
        JsonBodyListForTableModelUniversal jsonBodyListForTableModel = new JsonBodyListForTableModelUniversal();
        jsonBodyListForTableModel.setTableModelList(tableModelList);
        return jsonBodyListForTableModel;
    }

    @RequestMapping(value = "/get-last-row", method = RequestMethod.GET)
    public TableModelUniversal getLastRowFromTableModel(@RequestParam(value = "address", defaultValue = "0") int address,
                                                        @RequestParam(value = "register", defaultValue = "0") int register){
        return service.findLastValueByAddressAndOffsetAndDateTime(address, register);
    }

    @RequestMapping(value = "/get-current-date-time", method = RequestMethod.GET)
    public LocalDateTime getLocalDateTime(){
        return LocalDateTime.now();
    }
}
