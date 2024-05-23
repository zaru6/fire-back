package com.evilapp.fire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evilapp.fire.model.CsvData;
import com.evilapp.fire.repository.CsvDataRepository;

import java.util.List;

@Service
public class CsvDataService {

    @Autowired
    private CsvDataRepository repository;

    public void saveAll(List<CsvData> dataList) {
        repository.saveAll(dataList);
    }
}
