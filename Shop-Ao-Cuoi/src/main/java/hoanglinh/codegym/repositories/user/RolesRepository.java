package hoanglinh.codegym.repositories.user;

import hoanglinh.codegym.model.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Role,Long> {
}
