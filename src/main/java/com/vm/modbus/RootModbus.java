package com.vm.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.ModbusLocator;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import com.vm.modbus.entity.ModbusMasterTcpModel;

import java.util.List;

/**
 * Created by KIP-PC99 on 20.06.2018.
 */
public interface RootModbus<E extends Number> {

    void setUseBorders(final boolean useBorders);

    void setUseBorders(final boolean useBorders, final short bMax, final short bMin);

    List<E> readDataFromModBus(ModbusMasterSerialModel modbusMasterSerialModel,
                                      int adr, BatchRead<Integer> batch,
                                      boolean enableBatch,
                                      ModbusLocator... modbusLocator);

    List<E> readDataFromModBus(ModbusMasterTcpModel modbusMasterTcpModel,
                                      int adr, BatchRead<Integer> batch,
                                      boolean enableBatch,
                                      ModbusLocator... modbusLocator);

    void writeDataToModBus(ModbusMasterSerialModel modbusMasterSerialModel,
                                  int adr,
                                  E values,
                                  ModbusLocator modbusLocator);

    void writeDataToModBus(ModbusMasterTcpModel modbusMasterTcpModel,
                                  int adr,
                                  E values,
                                  ModbusLocator modbusLocator);
}
