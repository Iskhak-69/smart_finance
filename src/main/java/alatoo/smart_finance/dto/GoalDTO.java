package alatoo.smart_finance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoalDTO {
    private Long id;                 // ID цели
    private String description;      // Описание цели
    private String dueDate;          // Дата окончания
    private BigDecimal targetAmount; // Сумма для накопления
    private BigDecimal savedAmount;  // Уже накопленная сумма
    private Long userId;             // ID пользователя
}
