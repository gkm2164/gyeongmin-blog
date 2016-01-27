package kr.pe.imarch.model.repository;

import kr.pe.imarch.model.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by USER on 2016-01-27.
 */
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
}
