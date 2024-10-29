package alatoo.smart_finance.service;

import alatoo.smart_finance.entity.CategoryEntity;
import alatoo.smart_finance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
