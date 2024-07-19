package edu.mdc.cop2805c.assignment2.assets;

import edu.mdc.cop2805c.assignment2.base.CryptoAsset;
import edu.mdc.cop2805c.assignment2.base.WalletStorable;

public class NFT extends CryptoAsset implements WalletStorable {
    private String name;
    private String description;
    private String contractAddress;
    private double valueInUSD;

    public NFT(String name, String description, String contractAddress, String blockchainNetwork, double valueInUSD) {
        super(blockchainNetwork);
        this.name = name;
        this.description = description;
        this.contractAddress = contractAddress;
        this.valueInUSD = valueInUSD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public void setValueInUSD(double valueInUSD) {
        this.valueInUSD = valueInUSD;
    }

    @Override
    public double getValueInUSD() {
        return valueInUSD;
    }

    @Override
    public String getShortDescription() {
        return name + ": USD " + String.format("%.2f", valueInUSD);
    }

    @Override
    public String getLongDescription() {
        return String.format("[%s]%s, \n\tDescription:%s, \n\tContract Address:%s, \n\tPrice:%.1f",
                getBlockchainNetwork(), name, description, contractAddress, valueInUSD);
    }

    @Override
    public String toString() {
        return "NFT{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", contractAddress='" + contractAddress + '\'' +
                ", valueInUSD=" + valueInUSD +
                ", blockchainNetwork='" + getBlockchainNetwork() + '\'' +
                '}';
    }
}