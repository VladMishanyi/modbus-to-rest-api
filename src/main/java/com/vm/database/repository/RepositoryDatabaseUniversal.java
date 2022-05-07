package com.vm.database.repository;

import com.vm.database.table.TableModelUniversal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryDatabaseUniversal extends JpaRepository<TableModelUniversal, Long> {
    @Query(value = "SELECT * FROM modbus as m WHERE m.db_address = ?1 AND m.db_register = ?2 AND m.db_date BETWEEN ?3 AND ?4", nativeQuery = true)
    List<TableModelUniversal> findByAddressAndOffsetAndDateTimeBetween (final int address,
                                                                        final int register,
                                                                        final LocalDateTime start,
                                                                        final LocalDateTime end);

    @Query(value = "SELECT * FROM modbus as m WHERE m.db_address = ?1 AND m.db_register = ?2 ORDER BY db_date DESC LIMIT 1", nativeQuery = true)
    TableModelUniversal findLastValueByAddressAndOffsetAndDateTime (final int address, final int register);
}
