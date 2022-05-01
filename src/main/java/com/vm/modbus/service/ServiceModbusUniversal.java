package com.vm.modbus.service;

import com.vm.modbus.device.DeviceCache;

public interface ServiceModbusUniversal {
    DeviceCache readDataFromRegisterAll ();

    DeviceCache readDataFromRegister (final int address,
                                      final int register);

    DeviceCache writeDataToRegister (final int address,
                                     final int register,
                                     final String value);
}
