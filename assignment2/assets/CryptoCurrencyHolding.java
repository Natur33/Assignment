package edu.mdc.cop2805c.assignment2.assets;

import edu.mdc.cop2805c.assignment2.base.CryptoCurrency;
import edu.mdc.cop2805c.assignment2.base.WalletStorable;


public class CryptoCurrencyHolding implements WalletStorable {
    private CryptoCurrency cryptoCurrency;
    private double amount;

    public CryptoCurrencyHolding(CryptoCurrency cryptoCurrency, double amount) {
        this.cryptoCurrency = cryptoCurrency;
        this.amount = amount;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String getBlockchainNetwork() {
        return cryptoCurrency.getBlockchainNetwork();
    }

    @Override
    public double getValueInUSD() {
        return amount * cryptoCurrency.getCurrentPrice();
    }

    @Override
    public String getShortDescription() {
        return String.format("%s: %.10f (USD %.2f)", cryptoCurrency.getSymbol(), amount, getValueInUSD());
    }

    @Override
    public String getLongDescription() {
        return String.format("[%s]%s (%s), \n\tCurrent Price:%.2f: %.10f",
                getBlockchainNetwork(), cryptoCurrency.getName(), cryptoCurrency.getSymbol(),
                cryptoCurrency.getCurrentPrice(), amount);
    }

    @Override
    public String toString() {
        return "CryptoCurrencyHolding{" +
                "cryptoCurrency=" + cryptoCurrency +
                ", amount=" + amount +
                '}';
    }
}