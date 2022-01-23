package com.vm.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.ModbusLocator;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import com.vm.modbus.entity.ModbusMasterTcpModel;
import com.vm.modbus.lib.FloatCut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by KIP-PC99 on 14.09.2018.
 */
@Component
public class ModbusFloatImpl extends RootModbusImpl<Float> implements ModbusFloat {

    @Override
    public List<Float> readDataFromModBusDigs(
            final Float pow,
            final ModbusMasterSerialModel modbusMasterSerialModel,
            final int adr,
            final BatchRead<Integer> batch,
            final boolean enableBatch,
            final ModbusLocator... modbusLocator){

        List<Float> val = super.readDataFromModBus(modbusMasterSerialModel, adr, batch, enableBatch, modbusLocator);
        return val.stream().map( e -> FloatCut.cut(pow, e)).collect(Collectors.toList());
    }

    @Override
    public List<Float> readDataFromModBusDigs(
            final Float pow,
            final ModbusMasterTcpModel modbusMasterTcpModel,
            final int adr,
            final BatchRead<Integer> batch,
            final boolean enableBatch,
            final ModbusLocator... modbusLocator){

        List<Float> val = super.readDataFromModBus(modbusMasterTcpModel, adr, batch, enableBatch, modbusLocator);
        return val.stream().map( e -> FloatCut.cut(pow, e)).collect(Collectors.toList());
    }

    @Override
    public synchronized void setValuesDefault(final List<Float> list, final int length) {
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
