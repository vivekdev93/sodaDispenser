package com.sodadispenser.soda.controller;


import com.sodadispenser.soda.exception.TenderExactAmountException;
import com.sodadispenser.soda.model.Transaction;
import com.sodadispenser.soda.service.DispenserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SodaDispenserController {

    @Autowired
    private DispenserService dispenserService;

    /**
     * rest api that returns the available soda types
     *
     * @return the types of soda's available
     */
    @GetMapping("/getTypes")
    public List<String> showSodaItemsAndPrice() {
        return dispenserService.showSodaItemsAndPrice();
    }

    /**
     * rest api to dispense selected soda per the amount paid
     *
     * @param sodaSelected     type of soda to be dispensed
     * @param numberOfQuarters for the number of soda's
     * @return a string to collect the dispensed soda
     */
    @GetMapping("/dispenseSoda")
    public Map<String, String> dispenseSoda(@RequestParam(name = "sodaType") String sodaSelected, @RequestParam(name = "numberOfQuarters", defaultValue = "0") int numberOfQuarters) throws Exception {
        if (numberOfQuarters == 0) {
            throw new TenderExactAmountException("Please insert a quarter to get the Soda");
        }
        String message = dispenserService.dispenseSoda(sodaSelected, numberOfQuarters);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    /**
     * rest api to get the available inventory
     *
     * @return map that provides the inventory available
     */
    @GetMapping("/getInventory")
    public Map<String, Integer> getInventory() {
        return dispenserService.getInventory();
    }

    /**
     * rest api to get all transactions till date
     *
     * @return all the transactions till now
     */
    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions() {
        return dispenserService.getAllTransactions();
    }

}
