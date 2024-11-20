package alatoo.smart_finance.dto;

import alatoo.smart_finance.entity.Expense;
import alatoo.smart_finance.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {
    private List<Expense> expenseList;
    private List<Income> incomeList;
}
