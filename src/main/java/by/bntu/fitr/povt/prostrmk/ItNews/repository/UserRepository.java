package by.bntu.fitr.povt.prostrmk.ItNews.repository;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findUserByUsername(String username);
    User findUserById(Long id);
    List<User> findUsersByUsernameLikeIgnoreCase(String username);
    List<User> findAll(Pageable pageable);


}
