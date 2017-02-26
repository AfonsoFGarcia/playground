package pt.afonsogarcia.reactive.dto;

public class StockDto {

    private String symbol;

    private String company;

    private Double price;

    public StockDto(String symbol, String company, Double price) {
        this.symbol = symbol;
        this.company = company;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
