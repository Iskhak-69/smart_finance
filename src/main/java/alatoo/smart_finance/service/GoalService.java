package alatoo.smart_finance.service;

import alatoo.smart_finance.entity.GoalEntity;
import alatoo.smart_finance.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public List<GoalEntity> findAll() {
        return goalRepository.findAll();
    }

    public GoalEntity save(GoalEntity goal) {
        return goalRepository.save(goal);
    }

    public List<GoalEntity> findByUserId(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    public GoalEntity findById(Long id) {
        return goalRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        goalRepository.deleteById(id);
    }
}
