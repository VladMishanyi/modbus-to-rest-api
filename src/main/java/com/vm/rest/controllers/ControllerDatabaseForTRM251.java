package com.vm.rest.controllers;

import com.vm.rest.json.JsonBodyListForTableModelTRM251;
import com.vm.modbus.entity.ModbusBodyDateRange;
import com.vm.database.table.TableModelTRM251;
import com.vm.database.service.ServiceTRM251;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/database/trm251")
public class ControllerDatabaseForTRM251 {

    private final ServiceTRM251 service;

    @Autowired
    public ControllerDatabaseForTRM251(final ServiceTRM251 service) {
        this.service = service;
    }

    @RequestMapping(value = "/range", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public JsonBodyListForTableModelTRM251 rangeOfData(@RequestBody ModbusBodyDateRange modbusBodyDateRange){
        List<TableModelTRM251> tableModelList = service.findByDateBetween(modbusBodyDateRange.getFrom(), modbusBodyDateRange.getTo());
        JsonBodyListForTableModelTRM251 jsonBodyListForTableModel = new JsonBodyListForTableModelTRM251();
        jsonBodyListForTableModel.setTableModelList(tableModelList);
        return jsonBodyListForTableModel;
    }

    @RequestMapping(value = "/get-last-row", method = RequestMethod.GET)
    public TableModelTRM251 getLastRowFromTableModel(){
        return service.findLastValueByDate();
    }

    @RequestMapping(value = "/get-current-date-time", method = RequestMethod.GET)
    public LocalDateTime getLocalDateTime(){
        return LocalDateTime.now();
    }
}
