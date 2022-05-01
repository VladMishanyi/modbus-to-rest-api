package com.vm.database.service;

import com.vm.database.repository.RepositoryDatabaseUniversal;
import com.vm.database.table.TableModelUniversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@ComponentScan(basePackages = {"com.vm"})
public class ServiceDatabaseUniversalImpl implements ServiceDatabaseUniversal {
    private final RepositoryDatabaseUniversal repositoryDatabaseUniversal;

    @Autowired
    public ServiceDatabaseUniversalImpl(final RepositoryDatabaseUniversal repositoryDatabaseUniversal) {
        this.repositoryDatabaseUniversal = repositoryDatabaseUniversal;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TableModelUniversal> findByAddressAndOffsetAndDateTimeBetween (final int address,
                                                                        final int register,
                                                                        final LocalDateTime start,
                                                                        final LocalDateTime end) {
        return repositoryDatabaseUniversal.findByAddressAndOffsetAndDateTimeBetween (address, register, start, end);
    }
    @Transactional(readOnly = true)
    @Override
    public TableModelUniversal findLastValueByAddressAndOffsetAndDateTime (final int address, final int register) {
        return repositoryDatabaseUniversal.findLastValueByAddressAndOffsetAndDateTime (address, register);
    }
    @Transactional
    @Override
    public void addTableDevice(final TableModelUniversal tableUniversal) {
        repositoryDatabaseUniversal.saveAndFlush(tableUniversal);
    }
    @Transactional
    @Override
    public void addAllTableDevice(final List<TableModelUniversal> tableUniversalList) {
        repositoryDatabaseUniversal.saveAll(tableUniversalList);
    }
}
