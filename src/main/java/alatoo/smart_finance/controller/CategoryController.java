package alatoo.smart_finance.controller;

import alatoo.smart_finance.dto.CategoryDTO;
import alatoo.smart_finance.entity.CategoryEntity;
import alatoo.smart_finance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryDTO.getName());
        return ResponseEntity.ok(categoryService.save(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable Long id) {
        CategoryEntity category = categoryService.findById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryEntity category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        category.setName(categoryDTO.getName());
        return ResponseEntity.ok(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
