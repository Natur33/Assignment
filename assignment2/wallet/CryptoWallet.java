package edu.mdc.cop2805c.assignment2.wallet;

import java.util.ArrayList;
import java.util.List;

import edu.mdc.cop2805c.assignment2.assets.CryptoCurrencyHolding;
import edu.mdc.cop2805c.assignment2.assets.NFT;
import edu.mdc.cop2805c.assignment2.base.CryptoCurrency;

public class CryptoWallet<T> {
    private String niceName;
    private String address;
    @SuppressWarnings("unused")
    private String privateKeyFilename;
    private List<T> holdings;

    public CryptoWallet(String niceName, String address, String privateKeyFilename) {
        this.niceName = niceName;
        this.address = address;
        this.privateKeyFilename = privateKeyFilename;
        this.holdings = new ArrayList<>();
    }

    public void buy(T asset, String fromAddress) {
        holdings.add(asset);
    }

    public void sell(CryptoCurrencyHolding sellHolding, String toAddress) {
        holdings.remove(sellHolding);
    }

    public void transferTo(CryptoCurrencyHolding transferHolding, CryptoWallet<T> toWallet) {
        if (holdings.remove(transferHolding)) {
            toWallet.buy(transferHolding, this.address);
        }
    }

    public double getTotalValueInUSD() {
        return holdings.stream()
                .mapToDouble(holding -> {
                    if (holding instanceof CryptoCurrencyHolding) {
                        return ((CryptoCurrencyHolding) holding).getValueInUSD();
                    }
                    return 0.0;
                })
                .sum();
    }

    public String getShortDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ***********************************\n");
        sb.append(String.format(" * Wallet name: %s\n", niceName));
        for (T holding : holdings) {
            if (holding instanceof CryptoCurrencyHolding) {
                CryptoCurrencyHolding cch = (CryptoCurrencyHolding) holding;
                sb.append(String.format(" * - %s: %.10f (USD %.2f)\n", 
                    cch.getCryptoCurrency().getSymbol(), 
                    cch.getAmount(), 
                    cch.getValueInUSD()));
            } else if (holding instanceof NFT) {
                NFT nft = (NFT) holding;
                sb.append(String.format(" * - %s: USD %.2f\n", 
                    nft.getName(), 
                    nft.getValueInUSD()));
            }
        }
        sb.append(" ***********************************\n");
        sb.append(String.format(" * Balance: USD %.2f\n", getTotalValueInUSD()));
        sb.append(" ***********************************");
        return sb.toString();
    }
    /*public String getShortDescription() {
    StringBuilder sb = new StringBuilder();
    sb.append("Your wallets:\n");
    sb.append(String.format(" Combined Value: USD %.2f\n", getTotalValueInUSD()));
    
    for (CryptoWallet<CryptoCurrencyHolding> wallet : cryptoCurrencyWallets) {
        sb.append(wallet.getShortDescription()).append("\n\n");
    }
    for (CryptoWallet<NFT> wallet : nftWallets) {
        sb.append(wallet.getShortDescription()).append("\n\n");
    }
    
    return sb.toString();
}*/

public String getLongDescription() {
    StringBuilder sb = new StringBuilder();
    sb.append(" ***********************************\n");
    sb.append(String.format(" * Wallet name: %s\n", niceName));
    sb.append(String.format(" * Wallet address: %s\n", address));
    sb.append(String.format(" * Wallet private Key filename: %s\n", privateKeyFilename));
    for (T holding : holdings) {
        if (holding instanceof CryptoCurrencyHolding) {
            CryptoCurrencyHolding cch = (CryptoCurrencyHolding) holding;
            sb.append(String.format(" * - [%s]%s (%s), \n\tCurrent Price:%.2f: %.10f\n", 
                cch.getCryptoCurrency().getBlockchainNetwork(),
                cch.getCryptoCurrency().getName(),
                cch.getCryptoCurrency().getSymbol(),
                cch.getCryptoCurrency().getCurrentPrice(),
                cch.getAmount()));
        } else if (holding instanceof NFT) {
            NFT nft = (NFT) holding;
            sb.append(String.format(" * - [%s]%s, \n\tDescription:%s, \n\tContract Address:%s, \n\tPrice:%.1f\n", 
                nft.getBlockchainNetwork(),
                nft.getName(),
                nft.getDescription(),
                nft.getContractAddress(),
                nft.getValueInUSD()));
        }
    }
    sb.append(" ***********************************\n");
    sb.append(String.format(" * Balance: USD %.2f\n", getTotalValueInUSD()));
    sb.append(" ***********************************");
    return sb.toString();
}

public String getNiceName() {
    return this.niceName;
}

//public List<CryptoWallet<CryptoCurrencyHolding>> getHoldings() {
    // TODO Auto-generated method stub
   // throw new UnsupportedOperationException("Unimplemented method 'getHoldings'");
//}
public List<T> getHoldings() {
    return new ArrayList<>(this.holdings);
}

public CryptoCurrency getCryptoCurrency() {
    if (!holdings.isEmpty() && holdings.get(0) instanceof CryptoCurrencyHolding) {
        return ((CryptoCurrencyHolding) holdings.get(0)).getCryptoCurrency();
    }
    return null;
}
}