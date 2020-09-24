package com.sodadispenser.soda.dao;


import com.sodadispenser.soda.model.SodaType;
import com.sodadispenser.soda.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DispenserDAO {


    List<String> showSodaItemsAndPrice();

    Optional<SodaType> dispenseSoda(String sodaSelected);

    Map<String, Integer> getInventory();

    List<Transaction> getAllTransactions();
    void updateTransaction(Transaction transaction);
}


