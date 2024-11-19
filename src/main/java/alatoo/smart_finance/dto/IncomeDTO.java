package alatoo.smart_finance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;
}
