package alatoo.smart_finance.controller;

import alatoo.smart_finance.dto.TransactionDTO;
import alatoo.smart_finance.entity.TransactionEntity;
import alatoo.smart_finance.entity.UserEntity;
import alatoo.smart_finance.entity.CategoryEntity;
import alatoo.smart_finance.enums.TransactionType;
import alatoo.smart_finance.service.TransactionService;
import alatoo.smart_finance.service.UserService;
import alatoo.smart_finance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<TransactionEntity>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setDate(transactionDTO.getDate());
        transaction.setType(TransactionType.valueOf(transactionDTO.getType()));

        // Получаем пользователя по ID
        UserEntity user = userService.findById(transactionDTO.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest().body(null); // или выбрасываем исключение
        }
        transaction.setUser(user);

        // Получаем категорию по ID
        CategoryEntity category = categoryService.findById(transactionDTO.getCategoryId());
        if (category == null) {
            return ResponseEntity.badRequest().body(null); // или выбрасываем исключение
        }
        transaction.setCategory(category);

        return ResponseEntity.ok(transactionService.save(transaction));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionEntity>> getTransactionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.findByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable Long id) {
        TransactionEntity transaction = transactionService.findById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
