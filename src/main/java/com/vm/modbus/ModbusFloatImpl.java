package com.vm.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.ModbusLocator;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import com.vm.modbus.entity.ModbusMasterTcpModel;
import com.vm.modbus.lib.FloatCut;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by KIP-PC99 on 14.09.2018.
 */
public class ModbusFloatImpl extends RootModbusImpl<Float> implements ModbusFloat {

    @Override
    public List<Float> readDataFromModBusDigs(
            final Float pow,
            final ModbusMasterSerialModel modbusMasterSerialModel,
            final int adr,
            final BatchRead<Integer> batch,
            final boolean enableBatch,
            final List<Float> list,
            final ModbusLocator... modbusLocator) throws ModbusInitException, ModbusTransportException {

        List<Float> val = super.readDataFromModBus(modbusMasterSerialModel, adr, batch, enableBatch, list, modbusLocator);
        return val.stream().map( e -> FloatCut.cut(pow, e)).collect(Collectors.toList());
    }

    @Override
    public List<Float> readDataFromModBusDigs(
            final Float pow,
            final ModbusMasterTcpModel modbusMasterTcpModel,
            final int adr,
            final BatchRead<Integer> batch,
            final boolean enableBatch,
            final List<Float> list,
            final ModbusLocator... modbusLocator) throws ModbusInitException, ModbusTransportException {

        List<Float> val = super.readDataFromModBus(modbusMasterTcpModel, adr, batch, enableBatch, list, modbusLocator);
        return val.stream().map( e -> FloatCut.cut(pow, e)).collect(Collectors.toList());
    }

    @Override
    public void setValuesDefault(final List<Float> list, final int length) {
        for (int i=0; i<=length; i++){
            list.add(0F);
        }
    }

    @Override
    public Float borderValue(short bMin, short bMax, Float val){
        if (val >= (float) bMax) return (float) bMax;
        if (val <= (float) bMin) return (float) bMin;
        return val;
    }
}
