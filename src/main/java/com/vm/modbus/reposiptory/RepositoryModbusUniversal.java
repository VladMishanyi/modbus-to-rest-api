package com.vm.modbus.reposiptory;

import com.vm.modbus.device.DeviceCache;

public interface RepositoryModbusUniversal {
    DeviceCache readDataFromRegisterAll (final boolean useBorders,
                                         final short borderMin,
                                         final short borderMax,
                                         final float digsFloat,
                                         final boolean enableBatch);

    DeviceCache readDataFromRegister (final int address,
                                      final int register,
                                      final boolean useBorders,
                                      final short borderMin,
                                      final short borderMax,
                                      final float digsFloat,
                                      final boolean enableBatch);

    DeviceCache writeDataToRegister (final int address,
                                     final int register,
                                     final String value);
}
