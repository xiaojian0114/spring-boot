package org.example.task.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.entity.StockPrice;
import org.example.task.mapper.StockPriceMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Random;

//@Component
@AllArgsConstructor
@Slf4j
public class StockPriceTask {
    private final StockPriceMapper stockPriceMapper;
    private final Random random = new Random();

    @Scheduled(fixedRate = 10000)
    public void updateStockPrice() {
        double price = 100 + random.nextDouble() * 5;
        BigDecimal formattedPrice = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);

        StockPrice stockPrice = new StockPrice();
        stockPrice.setPrice(formattedPrice.doubleValue());
        stockPrice.setName("小米");
        stockPrice.setUpdateTime(LocalDateTime.now());

        stockPriceMapper.insert(stockPrice);
        log.info("股票已更新：{} ， 时间: {}", formattedPrice, LocalDateTime.now()); // 使用格式化后的价格
    }
}
