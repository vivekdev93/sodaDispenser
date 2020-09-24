package com.sodadispenser.soda.dao.impl;


import com.sodadispenser.soda.dao.DispenserDAO;
import com.sodadispenser.soda.model.SodaType;
import com.sodadispenser.soda.model.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Qualifier("dispenserDAO")
public class DispenserDAOImpl implements DispenserDAO {
    private static List<SodaType> sodaTypes = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public DispenserDAOImpl() {
        initialize();
    }

    public void initialize() {
        SodaType coke = new SodaType("COKE", 25, 100);
        SodaType pepsi = new SodaType("PEPSI", 25, 100);
        SodaType mazza = new SodaType("MAZZA", 25, 100);
        SodaType soda = new SodaType("SODA", 25, 100);
        sodaTypes.add(coke);
        sodaTypes.add(pepsi);
        sodaTypes.add(mazza);
        sodaTypes.add(soda);
    }

    @Override
    public List<String> showSodaItemsAndPrice() {
        return sodaTypes.stream().map(SodaType::getName).collect(Collectors.toList());
    }

    @Override
    public Optional<SodaType> dispenseSoda(String sodaSelected) {
        return sodaTypes.stream().filter(soda -> soda.getName().equalsIgnoreCase(sodaSelected)).filter(sodaType -> sodaType.getQuantity() > 0).findAny();
    }

    @Override
    public Map<String, Integer> getInventory() {
        return sodaTypes.stream().collect(Collectors.toMap(SodaType::getName, SodaType::getQuantity));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public void updateTransaction(Transaction transaction) {
        transaction.setId(transactions.size() + 1);
        transactions.add(transaction);
    }
}
