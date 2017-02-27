package pt.afonsogarcia.reactive.dto;

import pt.afonsogarcia.reactive.domain.StockDatabase;

import java.math.BigDecimal;


public class StockDto {

    private String symbol;

    private String company;

    private BigDecimal price;

    private String currency;

    private BigDecimal change;

    private Boolean positive;

    public StockDto(StockDatabase stockDatabase) {
        this.symbol = stockDatabase.getSymbol();
        this.company = stockDatabase.getCompany();
        this.price = stockDatabase.getPrice();
        this.currency = stockDatabase.getCurrency();
        this.change = stockDatabase.getChange();
        this.positive = change.compareTo(BigDecimal.ZERO) >= 0;
    }

    public StockDto(String symbol, String company, BigDecimal price, String currency, BigDecimal change) {
        this.symbol = symbol;
        this.company = company;
        this.price = price;
        this.currency = currency;
        this.change = change;
        this.positive = change.compareTo(BigDecimal.ZERO) >= 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public Boolean getPositive() {
        return positive;
    }

    public StockDatabase toStockDatabase() {
        StockDatabase stockDatabase = new StockDatabase();
        stockDatabase.setSymbol(symbol);
        stockDatabase.setCompany(company);
        stockDatabase.setPrice(price);
        stockDatabase.setCurrency(currency);
        stockDatabase.setChange(change);
        return stockDatabase;
    }
}
