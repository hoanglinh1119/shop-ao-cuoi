package hoanglinh.codegym.service.User;

import hoanglinh.codegym.model.user.Role;


public interface RolesService {
    Iterable<Role> findAll();

    Role findById(Long id);

    void save(Role roles);

    void remove(Long id);
}
