package alatoo.smart_finance.entity;

import alatoo.smart_finance.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "transactions") // Указываем имя таблицы
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Обязательное поле
    private BigDecimal amount;

    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING) // Храним как строку
    @Column(nullable = false)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // Связь с пользователем

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category; // Связь с категорией
}
