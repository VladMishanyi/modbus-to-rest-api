package com.vm.rest.controllers;

import com.vm.modbus.device.DeviceModelTRM251;
import com.vm.database.service.ServiceTRM251;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/modbus/trm251")
public class ControllerModbusForTRM251 {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final ServiceTRM251 service;

    private final DeviceModelTRM251 deviceModel;

    @Autowired
    public ControllerModbusForTRM251(final ServiceTRM251 service, final DeviceModelTRM251 deviceModel) {
        this.service = service;
        this.deviceModel = deviceModel;
    }

    @RequestMapping(value = "/read-all",method = RequestMethod.GET)
    public DeviceModelTRM251 readRegisterAll(){
        return deviceModel;
    }

}
