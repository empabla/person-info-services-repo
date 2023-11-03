package pl.kurs.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.userservice.command.CreateRoleCommand;
import pl.kurs.userservice.command.UpdateRoleCommand;
import pl.kurs.userservice.exception.InvalidIdException;
import pl.kurs.userservice.exception.RoleNotFoundException;
import pl.kurs.userservice.model.AppRole;
import pl.kurs.userservice.model.AppUser;
import pl.kurs.userservice.repository.AppRoleRepository;
import pl.kurs.userservice.repository.AppUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppRoleService {

    private final AppRoleRepository appRoleRepository;

    private final AppUserRepository appUserRepository;

    @Transactional(readOnly = true)
    public AppRole getRoleById(Long id) {
        return appRoleRepository.findById(
                Optional.ofNullable(id)
                        .orElseThrow(() -> new InvalidIdException("Id cannot be null."))
        ).orElseThrow(() -> new RoleNotFoundException("Role with id " + id + " not found."));
    }

    @Transactional(readOnly = true)
    public List<AppRole> getAllRoles() {
        return appRoleRepository.findAll();
    }

    @Transactional
    public AppRole addRole(CreateRoleCommand command) {
        AppRole appRoleForSave = new AppRole();
        appRoleForSave.setName(command.getName());
        return appRoleRepository.save(appRoleForSave);
    }

    @Transactional
    public AppRole updateRoleName(Long roleId, UpdateRoleCommand command) {
        AppRole appRole = getRoleById(roleId);
        appRole.setName(command.getName());
        return appRoleRepository.save(appRole);
    }

    public void deleteRoleById(Long id) {
        AppRole appRoleToDelete = getRoleById(id);
        appRoleRepository.delete(appRoleToDelete);
    }

    @Transactional(readOnly = true)
    public List<AppUser> getUsersByRole(Long roleId) {
        return appUserRepository.findUsersByAppRoleId(roleId);
    }

}
