package com.vm.modbus.reposiptory;

import com.vm.modbus.device.DeviceCache;

public interface RepositoryModbusUniversal {
    DeviceCache readDataFromRegisterAll (final int address,
                                         final int register,
                                         final boolean useBorders,
                                         final short borderMin,
                                         final short borderMax,
                                         final float digsFloat,
                                         final boolean enableBatch,
                                         final DeviceCache deviceCache);

    DeviceCache readDataFromRegister (final int address,
                                      final int register,
                                      final boolean useBorders,
                                      final short borderMin,
                                      final short borderMax,
                                      final float digsFloat,
                                      final boolean enableBatch,
                                      final DeviceCache deviceCache);

    DeviceCache writeDataToRegister (final int address,
                                     final int register,
                                     final String value,
                                     final DeviceCache deviceCache);
}
