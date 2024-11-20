package alatoo.smart_finance.dto;

import alatoo.smart_finance.entity.Expense;
import alatoo.smart_finance.entity.Income;
import lombok.Data;

@Data
public class StatsDTO {
    private Double expense;
    private Double income;
    private Income latestIncome;
    private Expense latestExpense;

    private Double balance;
    private Double minIncome;
    private Double maxIncome;
    private Double minExpense;
    private Double maxExpense;
}
