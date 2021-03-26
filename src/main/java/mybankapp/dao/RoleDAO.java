package mybankapp.dao;

import mybankapp.model.Role;

import java.util.Optional;

public interface RoleDAO {

    Optional<Role> findByName(String name);
    void addRole(Role role);
}
