package pt.afonsogarcia.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class StockUiController {

    @GetMapping("/stocks")
    public String getStocksPage() {
        return "pages/stocks";
    }
}
