package com.sodadispenser.soda.service;

import com.sodadispenser.soda.exception.StockOutException;
import com.sodadispenser.soda.exception.TenderExactAmountException;
import com.sodadispenser.soda.model.Transaction;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public interface DispenserService {

    List<String> showSodaItemsAndPrice();

    String dispenseSoda(String sodaSelected, double pricePaid) throws StockOutException, TenderExactAmountException;

    Map<String, Integer> getInventory();

    List<Transaction> getAllTransactions();

}
