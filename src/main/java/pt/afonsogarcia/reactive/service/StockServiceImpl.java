package pt.afonsogarcia.reactive.service;

import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.afonsogarcia.reactive.domain.StockDatabase;
import pt.afonsogarcia.reactive.domain.StockDatabaseRepository;
import pt.afonsogarcia.reactive.dto.StockDto;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.util.Calendar;
import java.util.List;


@Service
public class StockServiceImpl implements StockService {

    private final StockDatabaseRepository repository;

    @Autowired
    StockServiceImpl(StockDatabaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<StockDto> getStockPrices(List<String> symbols) {
        return Observable.create(e -> {
            symbols
                    .parallelStream()
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
        StockDatabase stockDatabase = repository.findFirstBySymbolOrderByLastUpdateDesc(symbol);
        StockDto stockDto;

        Calendar nowMinusFive = Calendar.getInstance();
        nowMinusFive.add(Calendar.MINUTE, -5);

        if(stockDatabase == null  || stockDatabase.getLastUpdate().before(nowMinusFive)) {
            Stock stock = YahooFinance.get(symbol);

            stockDto = new StockDto(symbol,
                    stock.getName(),
                    stock.getQuote().getPrice(),
                    stock.getCurrency(),
                    stock.getQuote().getChangeInPercent());

            stockDatabase = stockDto.toStockDatabase();

            repository.save(stockDatabase);
        } else {
            stockDto = new StockDto(stockDatabase);
        }

        return stockDto;
    }
}
