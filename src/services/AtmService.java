package services;

import models.atm.Atm;
import models.client.Client;
import utils.Address;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AtmService {
    private final List<Atm> atms = new ArrayList<>();
    private final AccountService accountService;

    public AtmService() {
        this.accountService = AccountService.getInstance();
    }

    public void createAtm(Address address, BigDecimal initialFunds) {
        Atm atm = new Atm(address, initialFunds);
    }

    /**
     * Display atms.
     */
    public void showAtms() {
        System.out.println("ATMs:");
        for (Atm atm : atms) {
            System.out.println(atm);
        }
    }

//    public void depositInAtm()
//    public void depositInAccount()
}
