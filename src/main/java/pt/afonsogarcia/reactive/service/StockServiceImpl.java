package pt.afonsogarcia.reactive.service;

import io.reactivex.Observable;
import org.springframework.stereotype.Service;
import pt.afonsogarcia.reactive.dto.StockDto;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.util.List;


@Service
public class StockServiceImpl implements StockService {

    @Override
    public Observable<StockDto> getStockPrices(List<String> symbols) {
        return Observable.create(e -> {
            symbols
                    .parallelStream()
                    //.stream()
                    .forEach(symbol -> {
                try {
                    e.onNext(getStockPrice(symbol));
                } catch (Exception e1) {
                    e.onError(e1);
                }
            });
            e.onComplete();
        });
    }

    private StockDto getStockPrice(String symbol) throws Exception {
        Stock stock = YahooFinance.get(symbol);
        return new StockDto(symbol,
                            stock.getName(),
                            stock.getQuote().getPrice(),
                            stock.getCurrency(),
                            stock.getQuote().getChangeInPercent());
    }
}
