package com.vm.modbus.service;

import com.vm.modbus.device.DeviceCache;
import com.vm.modbus.en.DigsFloat;
import com.vm.modbus.reposiptory.RepositoryModbusUniversal;
import com.vm.modbus.reposiptory.RepositoryModbusSerialUniversalImpl;

public class ServiceModbusSerialUniversalImpl implements ServiceModbusUniversal {
    private final RepositoryModbusUniversal repository;

    public ServiceModbusSerialUniversalImpl() {
        this.repository = new RepositoryModbusSerialUniversalImpl();
    }

    public ServiceModbusSerialUniversalImpl(RepositoryModbusUniversal repository) {
        this.repository = repository;
    }

    @Override
    public DeviceCache readDataFromRegisterAll (){
        return repository.readDataFromRegisterAll(true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false);
    }

    @Override
    public DeviceCache readDataFromRegister (final int address,
                                             final int register) {
        return repository.readDataFromRegister(address, register, true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false);
    }

    @Override
    public DeviceCache writeDataToRegister (final int address,
                                            final int register,
                                            final String value) {
        return repository.writeDataToRegister(address, register, value);
    }

}
