package com.sumrid_k.pos.Report.repository;

import com.sumrid_k.pos.Report.model.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    ArrayList<Report> findAll();
}
