package alatoo.smart_finance.controller;

import alatoo.smart_finance.dto.GoalDTO;
import alatoo.smart_finance.entity.GoalEntity;
import alatoo.smart_finance.entity.UserEntity;
import alatoo.smart_finance.service.GoalService;
import alatoo.smart_finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;
    @Autowired
    private UserService userService;

    // Получить все цели
    @GetMapping
    public ResponseEntity<List<GoalDTO>> getAllGoals() {
        List<GoalEntity> goals = goalService.findAll();
        List<GoalDTO> goalDTOs = goals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(goalDTOs);
    }

    // Создать новую цель
    @PostMapping
    public ResponseEntity<GoalDTO> createGoal(@RequestBody GoalDTO goalDTO) {
        if (goalDTO.getUserId() == null || goalDTO.getDescription() == null || goalDTO.getTargetAmount() == null) {
            return ResponseEntity.badRequest().build();
        }
        GoalEntity goalEntity = convertToEntity(goalDTO);

        UserEntity userEntity = userService.findById(goalDTO.getUserId());
        if (userEntity == null) {
            return ResponseEntity.badRequest().build();
        }

        goalEntity.setUser(userEntity);
        GoalEntity savedGoal = goalService.save(goalEntity);

        return ResponseEntity.ok(convertToDTO(savedGoal));
    }

    // Получить цели по ID пользователя
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GoalDTO>> getGoalsByUserId(@PathVariable Long userId) {
        List<GoalEntity> goals = goalService.findByUserId(userId);
        List<GoalDTO> goalDTOs = goals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(goalDTOs);
    }

    // Получить цель по ID
    @GetMapping("/{id}")
    public ResponseEntity<GoalDTO> getGoalById(@PathVariable Long id) {
        GoalEntity goal = goalService.findById(id);
        return goal != null ? ResponseEntity.ok(convertToDTO(goal)) : ResponseEntity.notFound().build();
    }

    // Удалить цель
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Преобразование GoalEntity в GoalDTO
    private GoalDTO convertToDTO(GoalEntity goal) {
        GoalDTO dto = new GoalDTO();
        dto.setId(goal.getId());
        dto.setDescription(goal.getDescription());
        dto.setTargetAmount(goal.getTargetAmount());
        dto.setSavedAmount(goal.getSavedAmount());
        dto.setUserId(goal.getUser().getId());
        dto.setDueDate(goal.getDueDate());
        return dto;
    }

    // Преобразование GoalDTO в GoalEntity
    private GoalEntity convertToEntity(GoalDTO goalDTO) {
        GoalEntity goalEntity = new GoalEntity();
        goalEntity.setId(goalDTO.getId());
        goalEntity.setDescription(goalDTO.getDescription());
        goalEntity.setTargetAmount(goalDTO.getTargetAmount());
        goalEntity.setSavedAmount(goalDTO.getSavedAmount());
        goalEntity.setDueDate(goalDTO.getDueDate());
        return goalEntity;
    }
}
