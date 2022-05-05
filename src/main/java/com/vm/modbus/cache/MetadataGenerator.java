package com.vm.modbus.cache;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.vm.modbus.device.DeviceCache;
import com.vm.modbus.device.DeviceModelUniversal;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import com.vm.modbus.entity.ModbusMasterTcpModel;

import java.io.File;
import java.io.IOException;

public class MetadataGenerator {
    private final static String pathToJsonDeviceCache = "./modbus-configuration-device-cache.json";
    private final static String pathToJsonModbusMasterSerialCache = "./modbus-configuration-master-serial-cache.json";
    private final static String pathToJsonModbusMasterTcpCache = "./modbus-configuration-master-tcp-cache.json";
    private final static ObjectMapper jsonMapper = new ObjectMapper();
    private static DeviceCache deviceCache = new DeviceCache(
            new DeviceModelUniversal(
                    16,
                    1,
                    true,
                    true,
                    "1",
                    "10",
                    "11",
                    RegisterRange.HOLDING_REGISTER,
                    37,
                    DataType.FOUR_BYTE_FLOAT));
    private static ModbusMasterSerialModel modbusMasterSerialModel = new ModbusMasterSerialModel(
            "/dev/ttyUSB0",
            9600,
            8,
            1,
            0,
            200,
            1);

    private static ModbusMasterTcpModel modbusMasterTcpModel = new ModbusMasterTcpModel ("192.168.0.10", 502, 500, 1);
    public static DeviceCache getDeviceCache() {
        return deviceCache;
    }
    public static ModbusMasterSerialModel getModbusMasterSerialModel() {
        return modbusMasterSerialModel;
    }

    public static ModbusMasterTcpModel getModbusMasterTcpModel() {
        return modbusMasterTcpModel;
    }

    public synchronized static void writeToJsonFileDeviceCache(final DeviceCache deviceCache) throws IOException, StreamWriteException, DatabindException {
        jsonMapper.writeValue(new File(pathToJsonDeviceCache), deviceCache);
        MetadataGenerator.deviceCache = deviceCache;
    }
    public synchronized static void readFromJsonFileDeviceCache() throws IOException, StreamReadException, DatabindException {
        MetadataGenerator.deviceCache = jsonMapper.readValue(new File(pathToJsonDeviceCache), DeviceCache.class);
    }
    public synchronized static void writeToJsonFileModbusMasterSerialCache(final ModbusMasterSerialModel modbusMasterSerialModel) throws IOException, StreamWriteException, DatabindException {
        jsonMapper.writeValue(new File(pathToJsonModbusMasterSerialCache), modbusMasterSerialModel);
        MetadataGenerator.modbusMasterSerialModel = modbusMasterSerialModel;
    }
    public synchronized static void readFromJsonFileModbusMasterSerialCache() throws IOException, StreamReadException, DatabindException {
        MetadataGenerator.modbusMasterSerialModel = jsonMapper.readValue(new File(pathToJsonModbusMasterSerialCache), ModbusMasterSerialModel.class);
    }
    public synchronized static void writeToJsonFileModbusMasterTcpCache(final ModbusMasterTcpModel modbusMasterTcpModel) throws IOException, StreamWriteException, DatabindException {
        jsonMapper.writeValue(new File(pathToJsonModbusMasterTcpCache), modbusMasterTcpModel);
        MetadataGenerator.modbusMasterTcpModel = modbusMasterTcpModel;
    }
    public synchronized static void readFromJsonFileModbusMasterTcpCache() throws IOException, StreamReadException, DatabindException {
        MetadataGenerator.modbusMasterTcpModel = jsonMapper.readValue(new File(pathToJsonModbusMasterTcpCache), ModbusMasterTcpModel.class);
    }
}
