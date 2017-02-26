package pt.afonsogarcia.reactive.controller;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pt.afonsogarcia.reactive.dto.StockDto;
import pt.afonsogarcia.reactive.service.StockService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbols}")
    @ResponseBody
    public ResponseEntity getStockPrices(@PathVariable("symbols") String symbols) {
        List<String> symbolList = Arrays.asList(symbols.split(","));
        List<StockDto> stockPrices = new ArrayList<>();
        final ResponseEntity[] responseEntity = new ResponseEntity[1];

        Observable<StockDto> stockObservable = stockService.getStockPrices(symbolList);

        stockObservable
                .subscribe(stockPrices::add,
                        err -> responseEntity[0] = ResponseEntity.status(500).body(err.getMessage()),
                        () -> responseEntity[0] = ResponseEntity.ok(stockPrices));

        return responseEntity[0];
    }

    @GetMapping("/stream/{symbols}")
    public ResponseBodyEmitter enableStream(@PathVariable("symbols") String symbols) {
        SseEmitter emitter = new SseEmitter();

        stockService.getStockPrices(Arrays.asList(symbols.split(",")))
                .subscribeOn(Schedulers.computation())
                .subscribe(emitter::send,
                        emitter::completeWithError,
                        emitter::complete);

        return emitter;
    }
}
