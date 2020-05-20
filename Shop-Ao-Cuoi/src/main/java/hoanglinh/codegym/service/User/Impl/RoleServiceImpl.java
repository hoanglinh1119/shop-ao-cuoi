package hoanglinh.codegym.service.User.Impl;

import hoanglinh.codegym.model.user.Role;
import hoanglinh.codegym.repositories.user.RolesRepository;
import hoanglinh.codegym.service.User.RolesService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public Iterable<Role> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return rolesRepository.findOne(id);
    }

    @Override
    public void save(Role roles) {
     rolesRepository.save(roles);
    }

    @Override
    public void remove(Long id) {
       rolesRepository.delete(id);
    }
}
