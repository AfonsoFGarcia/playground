package pt.afonsogarcia.reactive.controller;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    private SseEmitter emitter;

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
        emitter = new SseEmitter();

        stockService.getStockPrices(Arrays.asList(symbols.split(",")))
                .subscribeOn(Schedulers.io())
                .subscribe(this::sendStock,
                           this::error,
                           this::finish);

        return emitter;
    }

    private void error(Throwable throwable) throws IOException {
        send("error", throwable.getMessage());
        emitter.completeWithError(throwable);
    }

    private void finish() throws IOException {
        send("done", "All is done");
        emitter.complete();
    }

    private void sendStock(StockDto stockDto) throws IOException {
        send("stock-info", stockDto);
    }

    private void send(String eventType, Object payload) throws IOException {
        SseEmitter.SseEventBuilder builder = SseEmitter.event();

        builder.data(payload, MediaType.APPLICATION_JSON_UTF8)
                .id(Double.toString(Math.random() * 1000000))
                .name(eventType);

        emitter.send(builder);
    }
}
