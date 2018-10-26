package platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import platform.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findUserByEmail (String email);
//	List<User> findAllUsersByTeacherId (Long id);
	}
