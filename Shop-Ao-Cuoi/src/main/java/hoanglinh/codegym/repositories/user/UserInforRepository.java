package hoanglinh.codegym.repositories.user;

import hoanglinh.codegym.model.user.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInforRepository extends CrudRepository<UserInfo,Long> {
}
