package alatoo.smart_finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users") // Указываем имя таблицы
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Уникальное и обязательное поле
    private String username;

    @Column(nullable = false) // Обязательное поле
    private String password; // Хранить в зашифрованном виде

    @OneToMany(mappedBy = "user") // Указываем связь с транзакциями
    private List<TransactionEntity> transactions;
}
