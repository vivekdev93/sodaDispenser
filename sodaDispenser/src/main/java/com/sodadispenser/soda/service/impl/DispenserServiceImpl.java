package com.sodadispenser.soda.service.impl;

import com.sodadispenser.soda.dao.DispenserDAO;
import com.sodadispenser.soda.exception.StockOutException;
import com.sodadispenser.soda.exception.TenderExactAmountException;
import com.sodadispenser.soda.model.SodaType;
import com.sodadispenser.soda.model.Transaction;
import com.sodadispenser.soda.service.DispenserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Qualifier("dispenserService")
public class DispenserServiceImpl implements DispenserService {

    @Autowired
    private DispenserDAO dispenserDAO;

    @Override
    public List<String> showSodaItemsAndPrice() {
        return dispenserDAO.showSodaItemsAndPrice();
    }

    @Override
    public String dispenseSoda(String sodaSelected, double pricePaid) throws StockOutException, TenderExactAmountException {
        if ((pricePaid / 25) != (int) (pricePaid / 25)) {
            throw new TenderExactAmountException("Price paid is not in quarters. Please insert amount in multiples of quarters");
        }
        Optional<SodaType> optional = dispenserDAO.dispenseSoda(sodaSelected);
        SodaType sodaType = optional.orElse(null);
        if (sodaType != null) {
            int quantity = (int) (pricePaid / sodaType.getPrice());
            if (quantity < sodaType.getQuantity()) {
                sodaType.setQuantity(sodaType.getQuantity() - quantity);
                Transaction transaction = new Transaction(sodaSelected, pricePaid, getFormattedDate(new Date()));
                dispenserDAO.updateTransaction(transaction);
                return "Hurray! Your soda is Being dispensed cheers";
            } else {
                throw new StockOutException("Sorry requested soda type has been stocked out");
            }
        }

        return "Sorry requested soda type has been stocked out";
    }

    private String getFormattedDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    @Override
    public Map<String, Integer> getInventory() {
        return dispenserDAO.getInventory();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return dispenserDAO.getAllTransactions();
    }

    private Date convertStringToDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    private Date getStartDate(Date date) {
        return getDate(date, true);
    }

    private Date getEndDate(Date date) {
        return getDate(date, false);
    }

    private Date getDate(Date date, boolean isStartDate) {
        Instant inst = date.toInstant();
        LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
        Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        if (!isStartDate) {
            dayInst = localDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        }
        return Date.from(dayInst);
    }
}
