package alatoo.smart_finance.repository;

import alatoo.smart_finance.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Long> {
    List<GoalEntity> findByUserId(Long userId);
}
