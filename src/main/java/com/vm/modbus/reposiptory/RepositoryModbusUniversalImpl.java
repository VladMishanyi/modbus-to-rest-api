package com.vm.modbus.reposiptory;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.ModbusLocator;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.vm.modbus.*;
import com.vm.modbus.device.DeviceCache;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RepositoryModbusUniversalImpl implements RepositoryModbusUniversal {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private ModbusMasterSerialModel modbusMasterSerialFirst;
    private final BatchRead<Integer> batchRead;
    private final ModbusFloat modbusFloat;
    private final ModbusShort modbusShort;
    private final ModbusInteger modbusInteger;

    public RepositoryModbusUniversalImpl() {
        this.modbusMasterSerialFirst = initMaster();
        this.batchRead = new BatchRead<>();
        this.modbusFloat = new ModbusFloatImpl();
        this.modbusShort = new ModbusShortImpl();
        this.modbusInteger = new ModbusIntegerImpl();
    }
    public RepositoryModbusUniversalImpl(final ModbusMasterSerialModel modbusMasterSerialFirst,
                                         final BatchRead<Integer> batchRead,
                                         final ModbusFloat modbusFloat,
                                         final ModbusShort modbusShort,
                                         final ModbusInteger modbusInteger) {
        this.modbusMasterSerialFirst = modbusMasterSerialFirst;
        this.batchRead = batchRead;
        this.modbusFloat = modbusFloat;
        this.modbusShort = modbusShort;
        this.modbusInteger = modbusInteger;
    }

    private ModbusMasterSerialModel initMaster() {
        return new ModbusMasterSerialModel("/dev/ttyUSB0", 9600, 8, 1, 0, 200, 1);
    }

    @Override
    public DeviceCache readDataFromRegisterAll(final int address,
                                               final int register,
                                               final boolean useBorders,
                                               final short borderMin,
                                               final short borderMax,
                                               final float digsFloat,
                                               final boolean enableBatch,
                                               final DeviceCache deviceCache) {
        if (modbusFloat != null && modbusInteger != null){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            modbusInteger.setUseBorders(useBorders, borderMax, borderMin);
            deviceCache.getCache().stream().filter(c -> !c.isWrite()).forEach( fil -> {
                if (fil.getDataType() == 8) {
                    List<Float> list = new ArrayList<>();
                    try {
                        list = modbusFloat.readDataFromModBusDigs(
                                digsFloat,
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                batchRead,
                                enableBatch,
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException | RuntimeException e) {
                        modbusMasterSerialFirst = initMaster();
                    } catch (ModbusTransportException e) {
                        LOGGER.error("Not connected device: " + address + " offset: " + fil.getOffset());
                    }
                    fil.setValue(list.get(0).toString());
                }
                if (fil.getDataType() == 2) {
                    List<Integer> list = new ArrayList<>();
                    try {
                        list = modbusInteger.readDataFromModBus(
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                batchRead,
                                enableBatch,
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException e) {
                        modbusMasterSerialFirst = initMaster();
                    } catch (ModbusTransportException e) {
                        LOGGER.error("Not connected device: " + address + " offset: " + fil.getOffset());
                    }
                    fil.setValue(list.get(0).toString());
                }

            });
        }
        return deviceCache;
    }

    @Override
    public DeviceCache readDataFromRegister(final int address,
                                            final int register,
                                            final boolean useBorders,
                                            final short borderMin,
                                            final short borderMax,
                                            final float digsFloat,
                                            final boolean enableBatch,
                                            final DeviceCache deviceCache) {
        if (modbusFloat != null && modbusInteger != null){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            modbusInteger.setUseBorders(useBorders, borderMax, borderMin);
            deviceCache.getCache().stream().filter( c -> (c.getAddress() == address) && (c.getRegister() == register) && (!c.isWrite())).forEachOrdered( fil -> {
                if (fil.getDataType() == 8) {
                    List<Float> list = new ArrayList<>();
                    try {
                        list = modbusFloat.readDataFromModBusDigs(
                                digsFloat,
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                batchRead,
                                enableBatch,
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException e) {
                        modbusMasterSerialFirst = initMaster();
                    } catch (ModbusTransportException e) {
                        LOGGER.error("Not connected device: " + address + " offset: " + fil.getOffset());
                    }
                    fil.setValue(list.get(0).toString());
                }
                if (fil.getDataType() == 2) {
                    List<Integer> list = new ArrayList<>();
                    try {
                        list = modbusInteger.readDataFromModBus(
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                batchRead,
                                enableBatch,
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException e) {
                        modbusMasterSerialFirst = initMaster();
                    } catch (ModbusTransportException e) {
                        LOGGER.error("Not connected device: " + address + " offset: " + fil.getOffset());
                    }
                    fil.setValue(list.get(0).toString());
                }

            });
        }
        return deviceCache;
    }

    @Override
    public DeviceCache writeDataToRegister (final int address,
                                            final int register,
                                            final String value,
                                            final DeviceCache deviceCache) {
        if (modbusFloat != null && modbusShort != null) {
            deviceCache.getCache().stream().filter( c -> (c.getAddress() == address) && (c.getRegister() == register) && (c.isWrite())).forEachOrdered( fil -> {
                if (fil.getDataType() == 8) {
                    try {
                        modbusFloat.writeDataToModBus (
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                Float.valueOf(value),
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException e) {
                        modbusMasterSerialFirst = initMaster();
                    } catch (ModbusTransportException e) {
                        LOGGER.error("Not connected device: " + address + " offset: " + fil.getOffset());
                    }
                    fil.setValue(value);
                }
                if (fil.getDataType() == 2) {
                    try {
                        modbusShort.writeDataToModBus (
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                Short.valueOf(value),
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException e) {
                        modbusMasterSerialFirst = initMaster();
                    } catch (ModbusTransportException e) {
                        LOGGER.error("Not connected device: " + address + " offset: " + fil.getOffset());
                    }
                    fil.setValue(value);
                }

            });
        }
        return deviceCache;
    }
}
