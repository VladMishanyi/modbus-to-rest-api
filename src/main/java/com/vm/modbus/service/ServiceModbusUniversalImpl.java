package com.vm.modbus.service;

import com.vm.modbus.device.DeviceCache;
import com.vm.modbus.en.DigsFloat;
import com.vm.modbus.reposiptory.RepositoryModbusUniversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = {"com.vm.modbus"})
public class ServiceModbusUniversalImpl implements ServiceModbusUniversal {
    private final RepositoryModbusUniversal repository;

    @Autowired
    public ServiceModbusUniversalImpl(RepositoryModbusUniversal repository) {
        this.repository = repository;
    }

    @Override
    public DeviceCache readDataFromRegisterAll (final int address, final int register){
        return repository.readDataFromRegisterAll(address, register, true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false);
    }

    @Override
    public DeviceCache readDataFromRegister (final int address, final int register) {
        return repository.readDataFromRegister(address, register, true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false);
    }

    @Override
    public DeviceCache writeDataToRegister (final int address, final int register, final String value) {
        return repository.writeDataToRegister(address, register, value);
    }

}
