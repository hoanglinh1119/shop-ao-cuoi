package hoanglinh.codegym.service.User;

import hoanglinh.codegym.model.user.UserInfo;

public interface UserInforService {
    Iterable<UserInfo> findAll();

    UserInfo findById(Long id);

    void save(UserInfo userInfo);

    void remove(Long id);
}
