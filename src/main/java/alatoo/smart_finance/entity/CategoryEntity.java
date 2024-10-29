package alatoo.smart_finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "categories") // Указываем имя таблицы
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Уникальное и обязательное поле
    private String name;

    @OneToMany(mappedBy = "category") // Указываем связь с транзакциями
    private List<TransactionEntity> transactions;
}
