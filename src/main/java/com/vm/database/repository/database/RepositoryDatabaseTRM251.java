package com.vm.database.repository.database;

import com.vm.database.table.TableModelTRM251;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryDatabaseTRM251 extends JpaRepository<TableModelTRM251, Long> {

    List<TableModelTRM251> findByDateBetween (LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT * FROM trm251 ORDER BY date DESC LIMIT 1",nativeQuery = true)
    TableModelTRM251 findLastValueByDate ();
}
