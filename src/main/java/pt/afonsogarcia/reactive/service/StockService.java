package pt.afonsogarcia.reactive.service;

import io.reactivex.Observable;
import pt.afonsogarcia.reactive.dto.StockDto;

import java.util.List;

public interface StockService {

    Observable<StockDto> getStockPrices(List<String> symbols);
}
