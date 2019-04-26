package com.sumrid_k.pos.Report.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sumrid_k.pos.Report.ReportApplication;
import com.sumrid_k.pos.Report.model.Bill;
import com.sumrid_k.pos.Report.model.Product;
import com.sumrid_k.pos.Report.model.Report;
import com.sumrid_k.pos.Report.model.Stock;
import com.sumrid_k.pos.Report.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    private Logger logger = LoggerFactory.getLogger(ReportApplication.class);

    @Autowired
    private RestTemplate restTemplate;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    @Autowired
    private ReportRepository reportRepository;

    public void getDataAllServices() {
        // Get data from each service
        ResponseEntity responseBills = restTemplate.getForEntity("https://bill-service/bills", ArrayList.class);
        ResponseEntity responseProducts = restTemplate.getForEntity("https://product-service/product", ArrayList.class);
        ResponseEntity responseStocks = restTemplate.getForEntity("https://stock-service/stocks", ArrayList.class);

        logger.info(gson.toJson(responseBills.getBody()));
        logger.info(gson.toJson(responseProducts.getBody()));
        logger.info(gson.toJson(responseStocks.getBody()));

        // Convert json to object
        Type typeOfBill = new TypeToken<ArrayList<Bill>>() {}.getType();
        Type typeOfStock = new TypeToken<ArrayList<Stock>>() {}.getType();
        Type typeOfProdoct = new TypeToken<List<Product>>() {}.getType();

        String billJson = gson.toJson(responseBills.getBody());
        String stockJson = gson.toJson(responseStocks.getBody());
        String productJson = gson.toJson(responseProducts.getBody());

        ArrayList<Bill> bills = gson.fromJson(billJson, typeOfBill);
        ArrayList<Stock> stocks = gson.fromJson(stockJson, typeOfStock);
        List<Product> products = gson.fromJson(productJson, typeOfProdoct);


        // Create new report
        Report report = new Report();
        report.setDate(new Date());
//        report.setBestseller(findBestSeller(bills));
//        report.setLowInventory(findLowInventory(stocks));
        report.setIncome(calculateIncome(bills));
        report.setProfit(calculateProfit());

        // Save to database
        reportRepository.save(report);
    }

    public List<Report> getReports() {
        logger.debug("get report from database");
        return reportRepository.findAll();
    }

//    private long findBestSeller(ArrayList<Bill> bills) {
//        long tmpProduct = 0;
//        int tmpAmount = 0;
//        for (Bill bill : bills) {
//            if (bill.getAmount() > tmpAmount) {
//                tmpProduct = bill.getId();
//            }
//        }
//        return tmpProduct;
//    }

    private Stock findLowInventory(ArrayList<Stock> stocks) {
        Stock tempProduct = new Stock();
        int tmpInventory = Integer.MAX_VALUE;

        for (Stock stock : stocks) {
            if (stock.getQuantity() < tmpInventory) {
                tempProduct = stock;
            }
        }

        return tempProduct;
    }

    private double calculateIncome(ArrayList<Bill> bills) {
        double totalIncome = 0;
        for(Bill bill : bills) {
            totalIncome += bill.getTotalPrice();
        }
        return totalIncome;
    }

    private double calculateProfit() {
        return 45.4;
    }
}
