package hoanglinh.codegym.repositories.user;

import hoanglinh.codegym.model.user.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
    Account findByUsername(String username);

}
