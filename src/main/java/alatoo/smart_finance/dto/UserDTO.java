package alatoo.smart_finance.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password; // Если нужно передать пароль для создания пользователя
}
