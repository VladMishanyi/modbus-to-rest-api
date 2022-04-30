package com.vm.database.service;

import com.vm.database.repository.RepositoryUniversal;
import com.vm.database.table.TableModelUniversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@ComponentScan(basePackages = {"com.vm"})
public class ServiceUniversalImpl implements ServiceUniversal {
    private final RepositoryUniversal repositoryUniversal;

    @Autowired
    public ServiceUniversalImpl(final RepositoryUniversal repositoryUniversal) {
        this.repositoryUniversal = repositoryUniversal;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TableModelUniversal> findByAddressAndOffsetAndDateTimeBetween (final int address,
                                                                        final int register,
                                                                        final LocalDateTime start,
                                                                        final LocalDateTime end) {
        return repositoryUniversal.findByAddressAndOffsetAndDateTimeBetween (address, register, start, end);
    }
    @Transactional(readOnly = true)
    @Override
    public TableModelUniversal findLastValueByAddressAndOffsetAndDateTime (final int address, final int register) {
        return repositoryUniversal.findLastValueByAddressAndOffsetAndDateTime (address, register);
    }
    @Transactional
    @Override
    public void addTableDevice(final TableModelUniversal tableUniversal) {
        repositoryUniversal.saveAndFlush(tableUniversal);
    }
    @Transactional
    @Override
    public void addAllTableDevice(final List<TableModelUniversal> tableUniversalList) {
        repositoryUniversal.saveAll(tableUniversalList);
    }
    }
}
