package com.vm.modbus.service;

import com.vm.modbus.device.DeviceCache;
import com.vm.modbus.en.DigsFloat;
import com.vm.modbus.reposiptory.RepositoryModbusUniversal;
import com.vm.modbus.reposiptory.RepositoryModbusUniversalImpl;

public class ServiceModbusUniversalImpl implements ServiceModbusUniversal {
    private final RepositoryModbusUniversal repository;

    public ServiceModbusUniversalImpl() {
        this.repository = new RepositoryModbusUniversalImpl();
    }

    public ServiceModbusUniversalImpl(RepositoryModbusUniversal repository) {
        this.repository = repository;
    }

    @Override
    public DeviceCache readDataFromRegisterAll (final int address,
                                                final int register,
                                                final DeviceCache deviceCache){
        return repository.readDataFromRegisterAll(address, register, true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false, deviceCache);
    }

    @Override
    public DeviceCache readDataFromRegister (final int address,
                                             final int register,
                                             final DeviceCache deviceCache) {
        return repository.readDataFromRegister(address, register, true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false, deviceCache);
    }

    @Override
    public DeviceCache writeDataToRegister (final int address,
                                            final int register,
                                            final String value,
                                            final DeviceCache deviceCache) {
        return repository.writeDataToRegister(address, register, value, deviceCache);
    }

}
