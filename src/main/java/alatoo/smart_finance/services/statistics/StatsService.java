package alatoo.smart_finance.services.statistics;

import alatoo.smart_finance.dto.GraphDTO;
import alatoo.smart_finance.dto.StatsDTO;

public interface StatsService {
    GraphDTO getChartData();
    StatsDTO getStats();
}
