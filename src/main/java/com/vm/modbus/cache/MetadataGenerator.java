package com.vm.modbus.cache;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vm.modbus.device.DeviceCache;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MetadataGenerator {

    private final static String pathToJson = "./modbus-configuration.json";
    private final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final static ObjectMapper jsonMapper = new ObjectMapper();


    public static void writeToJsonFile(final  DeviceCache deviceCache) throws IOException, StreamWriteException, DatabindException {
        if (readWriteLock.writeLock().tryLock()) {
            readWriteLock.writeLock().lock();
            jsonMapper.writeValue(new File("./modbus-configuration.json"), deviceCache);
            readWriteLock.writeLock().unlock();
        } else {
            throw new IOException("Can't lock json writer");
        }
    }
    public static DeviceCache readFromJsonFile() throws IOException, StreamReadException, DatabindException {
        DeviceCache deviceCache;
        if (readWriteLock.readLock().tryLock()) {
            readWriteLock.readLock().lock();
            deviceCache = jsonMapper.readValue(new File(pathToJson), DeviceCache.class);
            readWriteLock.readLock().unlock();
        } else {
            throw new IOException("Can't lock json reader");
        }
        return deviceCache;
    }
}
