package com.sumrid_k.pos.Report.service;

import com.sumrid_k.pos.Report.model.Bill;
import com.sumrid_k.pos.Report.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    @Autowired
    private BillRepository repository;

    public Bill save(Bill bill) {
        return repository.save(bill);
    }
}
