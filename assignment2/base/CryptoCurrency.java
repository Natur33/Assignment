package edu.mdc.cop2805c.assignment2.base;

import java.util.Objects;

public class CryptoCurrency extends CryptoAsset {
    private String name;
    private String symbol;
    private double currentPrice;

    public CryptoCurrency(String name, String symbol, double currentPrice, String blockchainNetwork) {
        super(blockchainNetwork);
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                ", blockchainNetwork='" + getBlockchainNetwork() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoCurrency that = (CryptoCurrency) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}