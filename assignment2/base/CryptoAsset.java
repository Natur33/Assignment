package edu.mdc.cop2805c.assignment2.base;

// Abstract class CryptoAsset
public abstract class CryptoAsset {
    private String blockchainNetwork;

    public CryptoAsset(String blockchainNetwork) {
        this.blockchainNetwork = blockchainNetwork;
    }

    public String getBlockchainNetwork() {
        return blockchainNetwork;
    }

    public void setBlockchainNetwork(String blockchainNetwork) {
        this.blockchainNetwork = blockchainNetwork;
    }

    @Override
    public String toString() {
        return "CryptoAsset{" +
                "blockchainNetwork='" + blockchainNetwork + '\'' +
                '}';
    }
}