package com.vm.modbus.reposiptory;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.ModbusLocator;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.vm.modbus.*;
import com.vm.modbus.cache.MetadataGenerator;
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
        return MetadataGenerator.getModbusMasterSerialModel();
    }
    private DeviceCache initDevice() {
        return MetadataGenerator.getDeviceCache();
    }

    @Override
    public DeviceCache readDataFromRegisterAll(final boolean useBorders,
                                               final short borderMin,
                                               final short borderMax,
                                               final float digsFloat,
                                               final boolean enableBatch) {
        DeviceCache deviceCache = initDevice();
        if (modbusFloat != null && modbusInteger != null){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            modbusInteger.setUseBorders(useBorders, borderMax, borderMin);
            deviceCache.getCache().stream().filter(c -> !c.isWrite()).forEach( fil -> {
                if (fil.getDataType() == 8) {
                    List<Float> listFloat = new ArrayList<>();
                    try {
                        listFloat = modbusFloat.readDataFromModBusDigs(
                                digsFloat,
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                batchRead,
                                enableBatch,
                                listFloat,
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException | RuntimeException e) {
                        modbusFloat.setValuesDefault(listFloat, 1);
                        modbusMasterSerialFirst = initMaster();
                        LOGGER.error("ModBus read init problem, device:"+ fil.getAddress() + " offset:" + fil.getOffset() + " type:" + fil.getDataType() + " error:" + e.getMessage());
                    } catch (ModbusTransportException e) {
                        modbusFloat.setValuesDefault(listFloat, 1);
                        LOGGER.error("ModBus read transport problem, device:" + fil.getAddress() + " offset:" + fil.getOffset() + " type:" + fil.getDataType() + " error:" + e.getMessage());
                    }
                    fil.setValue(listFloat.get(0).toString());
                }
                if (fil.getDataType() == 2) {
                    List<Integer> listInt = new ArrayList<>();
                    try {
                        listInt = modbusInteger.readDataFromModBus(
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                batchRead,
                                enableBatch,
                                listInt,
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException e) {
                        modbusInteger.setValuesDefault(listInt, 1);
                        modbusMasterSerialFirst = initMaster();
                        LOGGER.error("ModBus read init problem, device:"+ fil.getAddress() + " offset:" + fil.getOffset() + " type:" + fil.getDataType() + " error:" + e.getMessage());
                    } catch (ModbusTransportException e) {
                        modbusInteger.setValuesDefault(listInt, 1);
                        LOGGER.error("ModBus read transport problem, device:" + fil.getAddress() + " offset:" + fil.getOffset() + " type:" + fil.getDataType() + " error:" + e.getMessage());
                    }
                    fil.setValue(listInt.get(0).toString());
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
                                            final boolean enableBatch) {
        DeviceCache deviceCache = initDevice();
        if (modbusFloat != null && modbusInteger != null){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            modbusInteger.setUseBorders(useBorders, borderMax, borderMin);
            deviceCache.getCache().stream().filter( c -> (c.getAddress() == address) && (c.getRegister() == register) && (!c.isWrite())).forEachOrdered( fil -> {
                if (fil.getDataType() == 8) {
                    List<Float> listFloat = new ArrayList<>();
                    try {
                        listFloat = modbusFloat.readDataFromModBusDigs(
                                digsFloat,
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                batchRead,
                                enableBatch,
                                listFloat,
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException e) {
                        modbusFloat.setValuesDefault(listFloat, 1);
                        modbusMasterSerialFirst = initMaster();
                        LOGGER.error("ModBus read init problem, slave address №"+ address + " offset: " + fil.getOffset() + " error: " + e.getMessage());
                    } catch (ModbusTransportException e) {
                        modbusFloat.setValuesDefault(listFloat, 1);
                        LOGGER.error("ModBus read transport problem, device: " + address + " offset: " + fil.getOffset() + " error: " + e.getMessage());
                    }
                    fil.setValue(listFloat.get(0).toString());
                }
                if (fil.getDataType() == 2) {
                    List<Integer> listInt = new ArrayList<>();
                    try {
                        listInt = modbusInteger.readDataFromModBus(
                                modbusMasterSerialFirst,
                                fil.getAddress(),
                                batchRead,
                                enableBatch,
                                listInt,
                                new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    } catch (ModbusInitException e) {
                        modbusInteger.setValuesDefault(listInt, 1);
                        modbusMasterSerialFirst = initMaster();
                        LOGGER.error("ModBus Init problem, slave address №"+ address + " offset: " + fil.getOffset() + " error: " + e.getMessage());
                    } catch (ModbusTransportException e) {
                        modbusInteger.setValuesDefault(listInt, 1);
                        LOGGER.error("ModBus transport problem, device: " + address + " offset: " + fil.getOffset() + " error: " + e.getMessage());
                    }
                    fil.setValue(listInt.get(0).toString());
                }
            });
        }
        return deviceCache;
    }

    @Override
    public DeviceCache writeDataToRegister (final int address,
                                            final int register,
                                            final String value) {
        DeviceCache deviceCache = initDevice();
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
                        LOGGER.error("ModBus write init problem, slave address №"+ address + " offset: " + fil.getOffset() + " error: " + e.getMessage());
                    } catch (ModbusTransportException e) {
                        LOGGER.error("ModBus write transport problem, device: " + address + " offset: " + fil.getOffset() + " error: " + e.getMessage());
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
                        LOGGER.error("ModBus write init problem, slave address №"+ address + " offset: " + fil.getOffset() + " error: " + e.getMessage());
                    } catch (ModbusTransportException e) {
                        LOGGER.error("ModBus write transport problem, device: " + address + " offset: " + fil.getOffset() + " error: " + e.getMessage());
                    }
                    fil.setValue(value);
                }
            });
        }
        return deviceCache;
    }
}
