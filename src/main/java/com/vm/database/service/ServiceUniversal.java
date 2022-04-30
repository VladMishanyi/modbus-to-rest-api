package com.vm.database.service;

import com.vm.database.table.TableModelUniversal;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceUniversal {
    List<TableModelUniversal> findByAddressAndOffsetAndDateTimeBetween (final int address,
                                                                        final int register,
                                                                        final LocalDateTime start,
                                                                        final LocalDateTime end);
    TableModelUniversal findLastValueByAddressAndOffsetAndDateTime (final int address, final int register);
    void addTableDevice(final TableModelUniversal tableUniversal);
    void addAllTableDevice(final List<TableModelUniversal> tableUniversalList);
}
