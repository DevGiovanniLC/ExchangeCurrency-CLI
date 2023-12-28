package model;

public record Currency(String symbol, String name, Double referenceValue){
    @Override
    public String toString() {
        return this.symbol + ", " + this.name;
    }
}



