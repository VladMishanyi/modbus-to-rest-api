package com.vm.rest.controllers;

import com.vm.modbus.cache.MetadataGenerator;
import com.vm.modbus.device.DeviceCache;
import com.vm.modbus.device.DeviceModelUniversal;
import com.vm.modbus.entity.ModbusBodyQuery;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import com.vm.modbus.entity.ModbusMasterTcpModel;
import com.vm.modbus.service.ServiceModbusUniversal;
import com.vm.modbus.service.ServiceModbusSerialUniversalImpl;
import com.vm.rest.chain.ChainModbus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/modbus")
public class ControllerModbusUniversal {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ServiceModbusUniversal service;
    public ControllerModbusUniversal() {
        this.service = new ServiceModbusSerialUniversalImpl();
    }
    public ControllerModbusUniversal(final ServiceModbusUniversal service) {
        this.service = service;
    }


    @RequestMapping(value = "/read-all", method = RequestMethod.GET)
    public DeviceCache readRegisterAll(){
        return MetadataGenerator.getDeviceCache();
    }

    @RequestMapping(value = "/read-one", method = RequestMethod.GET)
    public DeviceCache readRegisterOne(@RequestParam(value = "address", defaultValue = "0") int address,
                                       @RequestParam(value = "register", defaultValue = "0") int register){
        ChainModbus.modbusBodyQueryQueue.add(new ModbusBodyQuery(address, register, false));
        DeviceCache lists = MetadataGenerator.getDeviceCache();
        DeviceModelUniversal device = lists.getCache().stream().filter( el -> el.getAddress() == address && el.getRegister() == register).findFirst().orElse(null);
        return new DeviceCache(device);
    }

    @RequestMapping(value = "/write-one", method = RequestMethod.GET)
    public DeviceCache writeRegisterOne(@RequestParam(value = "address", defaultValue = "0") int address,
                                        @RequestParam(value = "register", defaultValue = "0") int register,
                                        @RequestParam(value = "value", defaultValue = "0") String value){
        ChainModbus.modbusBodyQueryQueue.add(new ModbusBodyQuery(address, register, value, true));
        DeviceCache lists = MetadataGenerator.getDeviceCache();
        DeviceModelUniversal device = lists.getCache().stream().filter( el -> el.getAddress() == address && el.getRegister() == register).findFirst().orElse(null);
        return new DeviceCache(device);
    }

    @RequestMapping(value = "/write-config-device", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public DeviceCache writeToJsonFileDeviceCache(@RequestBody DeviceCache deviceCache) {
      try {
          MetadataGenerator.writeToJsonFileDeviceCache(deviceCache);
      } catch (Exception e) {
          LOGGER.error(e.getMessage());
      }
      return MetadataGenerator.getDeviceCache();
    }
    @RequestMapping(value = "/read-config-device", method = RequestMethod.GET)
    public DeviceCache readFromJsonFileDeviceCache() {
        try {
            MetadataGenerator.readFromJsonFileDeviceCache();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return MetadataGenerator.getDeviceCache();
    }
    @RequestMapping(value = "/write-config-modbus-serial", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public ModbusMasterSerialModel writeToJsonFileModbusMasterSerialCache(@RequestBody ModbusMasterSerialModel modbusMasterSerialModel) {
        try {
            MetadataGenerator.writeToJsonFileModbusMasterSerialCache(modbusMasterSerialModel);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return MetadataGenerator.getModbusMasterSerialModel();
    }
    @RequestMapping(value = "/read-config-modbus-serial", method = RequestMethod.GET)
    public ModbusMasterSerialModel readFromJsonFileModbusMasterSerialCache() {
        try {
            MetadataGenerator.readFromJsonFileModbusMasterSerialCache();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return MetadataGenerator.getModbusMasterSerialModel();
    }
    @RequestMapping(value = "/write-config-modbus-tcp", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public ModbusMasterTcpModel writeToJsonFileModbusMasterTcpCache(@RequestBody ModbusMasterTcpModel modbusMasterTcpModel) {
        try {
            MetadataGenerator.writeToJsonFileModbusMasterTcpCache(modbusMasterTcpModel);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return MetadataGenerator.getModbusMasterTcpModel();
    }
    @RequestMapping(value = "/read-config-modbus-tcp", method = RequestMethod.GET)
    public ModbusMasterTcpModel readFromJsonFileModbusMasterTcpCache() {
        try {
            MetadataGenerator.readFromJsonFileModbusMasterTcpCache();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return MetadataGenerator.getModbusMasterTcpModel();
    }
}
