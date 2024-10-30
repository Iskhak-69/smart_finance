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
import java.util.stream.Collectors;

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
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionEntity> transactions = transactionService.findAll();
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionDTOs);
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setDate(transactionDTO.getDate());
        transaction.setType(TransactionType.valueOf(transactionDTO.getType()));

        // Получаем пользователя по ID
        UserEntity user = userService.findById(transactionDTO.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }
        transaction.setUser(user);

        // Получаем категорию по ID
        CategoryEntity category = categoryService.findById(transactionDTO.getCategoryId());
        if (category == null) {
            return ResponseEntity.badRequest().body(null);
        }
        transaction.setCategory(category);

        TransactionEntity savedTransaction = transactionService.save(transaction);
        return ResponseEntity.ok(convertToDTO(savedTransaction));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByUserId(@PathVariable Long userId) {
        List<TransactionEntity> transactions = transactionService.findByUserId(userId);
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionEntity transaction = transactionService.findById(id);
        return transaction != null ? ResponseEntity.ok(convertToDTO(transaction)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Helper method to convert TransactionEntity to TransactionDTO
    private TransactionDTO convertToDTO(TransactionEntity transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setDescription(transaction.getDescription());
        dto.setDate(transaction.getDate());
        dto.setType(transaction.getType().name());
        dto.setCategoryId(transaction.getCategory().getId());
        dto.setUserId(transaction.getUser().getId());
        return dto;
    }
}
