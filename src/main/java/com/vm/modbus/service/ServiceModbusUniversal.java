package com.vm.modbus.service;

import com.vm.modbus.device.DeviceCache;

public interface ServiceModbusUniversal {
    DeviceCache readDataFromRegisterAll (final int address,
                                         final int register,
                                         final DeviceCache deviceCache);

    DeviceCache readDataFromRegister (final int address,
                                      final int register,
                                      final DeviceCache deviceCache);

    DeviceCache writeDataToRegister (final int address,
                                     final int register,
                                     final String value,
                                     final DeviceCache deviceCache);
}
