package pt.afonsogarcia.reactive.domain;


import org.springframework.data.repository.CrudRepository;

public interface StockDatabaseRepository extends CrudRepository<StockDatabase, Long> {
    StockDatabase findFirstBySymbolOrderByLastUpdateDesc(String symbol);
}
