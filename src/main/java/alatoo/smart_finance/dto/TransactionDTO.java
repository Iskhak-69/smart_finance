package alatoo.smart_finance.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDTO {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String type; // INCOME or EXPENSE
    private Long categoryId; // ID категории
    private Long userId; // ID пользователя
}

