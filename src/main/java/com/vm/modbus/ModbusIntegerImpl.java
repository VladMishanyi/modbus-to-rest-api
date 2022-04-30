package com.vm.modbus;

import java.util.List;

/**
 * Created by KIP-PC99 on 14.09.2018.
 */
public class ModbusIntegerImpl extends RootModbusImpl<Integer> implements ModbusInteger {

    @Override
    protected void setValuesDefault(final List<Integer> list, final int length) {
        for (int i=0; i<=length; i++){
            list.add(0);
        }
    }

    @Override
    protected Integer borderValue(short bMin, short bMax, Integer val){
        if (val >= (int) bMax) return (int) bMax;
        if (val <= (int) bMin) return (int) bMin;
        return val;
    }
}
