package com.vm.database.repository;

import com.vm.database.table.TableModelUniversal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryUniversal extends JpaRepository<TableModelUniversal, Long> {

    @Query(value = "SELECT * FROM modbus as m WHERE m.address = ?1 AND m.register = ?2 AND m.date FROM_UNIXTIME(?3) AND FROM_UNIXTIME(?4)", nativeQuery = true)
    List<TableModelUniversal> findByAddressAndOffsetAndDateTimeBetween (final int address,
                                                                        final int register,
                                                                        final LocalDateTime start,
                                                                        final LocalDateTime end);

    @Query(value = "SELECT * FROM modbus as m WHERE m.address = ?1 AND m.register = ?2 ORDER BY date DESC LIMIT 1", nativeQuery = true)
    TableModelUniversal findLastValueByAddressAndOffsetAndDateTime (final int address, final int register);
}
