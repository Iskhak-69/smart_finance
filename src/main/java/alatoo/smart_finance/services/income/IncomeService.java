package alatoo.smart_finance.services.income;

import alatoo.smart_finance.dto.IncomeDTO;
import alatoo.smart_finance.entity.Income;

import java.util.List;

public interface IncomeService {
    Income postIncome(IncomeDTO incomeDTO);

    List<IncomeDTO> getAllIncomes();

    Income updateIncome(Long id, IncomeDTO incomeDTO);

    IncomeDTO getIncomeById(Long id);
}
