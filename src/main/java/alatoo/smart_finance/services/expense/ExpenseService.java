package alatoo.smart_finance.services.expense;

import alatoo.smart_finance.dto.ExpenseDTO;
import alatoo.smart_finance.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);
}
