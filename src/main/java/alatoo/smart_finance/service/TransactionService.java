package alatoo.smart_finance.service;

import alatoo.smart_finance.entity.TransactionEntity;
import alatoo.smart_finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionEntity save(TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }

    public List<TransactionEntity> findByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<TransactionEntity> findAll() {
        return transactionRepository.findAll();
    }

    public TransactionEntity findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
