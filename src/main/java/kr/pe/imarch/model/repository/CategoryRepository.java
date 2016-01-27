package kr.pe.imarch.model.repository;

import kr.pe.imarch.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by USER on 2016-01-27.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
