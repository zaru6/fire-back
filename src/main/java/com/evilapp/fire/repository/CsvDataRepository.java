package com.evilapp.fire.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.evilapp.fire.model.CsvData;

public interface CsvDataRepository extends JpaRepository<CsvData, Long> {
}
