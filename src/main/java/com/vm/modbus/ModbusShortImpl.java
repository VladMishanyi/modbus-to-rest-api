package com.vm.modbus;

import java.util.List;

/**
 * Created by KIP-PC99 on 14.09.2018.
 */

public class ModbusShortImpl extends RootModbusImpl<Short> implements ModbusShort{
    @Override
    public void setValuesDefault(final List<Short> list, final int length) {
        for (int i=0; i<=length; i++){
            list.add((short) 0);
        }
    }
    @Override
    public Short borderValue(short bMin, short bMax, Short val){
        if (val >= bMax) return bMax;
        if (val <= bMin) return bMin;
        return val;
    }
}
