package edu.mdc.cop2805c.assignment2.wallet;

import edu.mdc.cop2805c.assignment2.assets.CryptoCurrencyHolding;
import edu.mdc.cop2805c.assignment2.assets.NFT;
import edu.mdc.cop2805c.assignment2.base.CryptoCurrency;

import java.util.ArrayList;
import java.util.List;

public class WalletManager {
    private List<CryptoWallet<CryptoCurrencyHolding>> cryptoCurrencyWallets;
    private List<CryptoWallet<NFT>> nftWallets;
    private List<CryptoCurrency> cryptoCurrencies;

    public WalletManager() {
        this.cryptoCurrencyWallets = new ArrayList<>();
        this.nftWallets = new ArrayList<>();
        this.cryptoCurrencies = new ArrayList<>();
    }

    public void createCryptoCurrencyWallet(String niceName, String address, String privateKeyFilename) {
        CryptoWallet<CryptoCurrencyHolding> wallet = new CryptoWallet<>(niceName, address, privateKeyFilename);
        cryptoCurrencyWallets.add(wallet);
    }

    public void createNFTWallet(String niceName, String address, String privateKeyFilename) {
        CryptoWallet<NFT> wallet = new CryptoWallet<>(niceName, address, privateKeyFilename);
        nftWallets.add(wallet);
    }

    public CryptoWallet<CryptoCurrencyHolding> getCryptoCurrencyWallet(int index) {
        if (index >= 0 && index < cryptoCurrencyWallets.size()) {
            return cryptoCurrencyWallets.get(index);
        }
        return null;
    }

    public CryptoWallet<NFT> getNFTWallet(int index) {
        if (index >= 0 && index < nftWallets.size()) {
            return nftWallets.get(index);
        }
        return null;
    }

    public int getNumWallets() {
        return cryptoCurrencyWallets.size() + nftWallets.size();
    }

    public double getCombinedValueInUSD() {
        double total = 0;
        for (CryptoWallet<CryptoCurrencyHolding> wallet : cryptoCurrencyWallets) {
            total += wallet.getTotalValueInUSD();
        }
        for (CryptoWallet<NFT> wallet : nftWallets) {
            total += wallet.getTotalValueInUSD();
        }
        return total;
    }

    public String getShortDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your wallets:\n");
        sb.append(" Combined Value: USD ").append(String.format("%.2f", getCombinedValueInUSD())).append("\n");
        
        for (CryptoWallet<CryptoCurrencyHolding> wallet : cryptoCurrencyWallets) {
            sb.append(wallet.getShortDescription()).append("\n");
        }
        for (CryptoWallet<NFT> wallet : nftWallets) {
            sb.append(wallet.getShortDescription()).append("\n");
        }
        
        return sb.toString();
    }

    public CryptoCurrency getCryptoCurrency(String symbol) {
        return cryptoCurrencies.stream()
                .filter(cc -> cc.getSymbol().equalsIgnoreCase(symbol))
                .findFirst()
                .orElse(null);
    }

    public void addCryptoCurrency(CryptoCurrency newCryptoCurrency) {
        if (getCryptoCurrency(newCryptoCurrency.getSymbol()) == null) {
            cryptoCurrencies.add(newCryptoCurrency);
        }
    }

    public List<CryptoWallet<CryptoCurrencyHolding>> getCryptoCurrencyWallets() {
        return cryptoCurrencyWallets;
    }

    public List<CryptoWallet<NFT>> getNftWallets() {
        return nftWallets;
    }

    public List<CryptoCurrency> getCryptoCurrencies() {
        return cryptoCurrencies;
    }
}
