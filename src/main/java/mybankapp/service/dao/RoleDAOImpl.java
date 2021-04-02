package mybankapp.service.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.domain.model.Role;
import mybankapp.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleDAOImpl implements RoleDAO{

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
