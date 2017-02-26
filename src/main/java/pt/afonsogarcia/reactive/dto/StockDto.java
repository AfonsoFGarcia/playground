package pt.afonsogarcia.reactive.dto;

import java.math.BigDecimal;

public class StockDto {

    private String symbol;

    private String company;

    private BigDecimal price;

    private BigDecimal change;

    private Boolean positive;

    public StockDto(String symbol, String company, BigDecimal price, BigDecimal change) {
        this.symbol = symbol;
        this.company = company;
        this.price = price;
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

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public Boolean getPositive() {
        return positive;
    }
}
