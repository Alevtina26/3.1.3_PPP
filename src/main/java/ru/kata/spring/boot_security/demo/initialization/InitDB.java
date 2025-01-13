package ru.kata.spring.boot_security.demo.initialization;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class InitDB {

    private final RoleService roleService;
    private final UserService userService;


    public InitDB(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;

    }

    @PostConstruct
    private void fillDb() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        User user = new User("user@mail.ru", "Boris", "Britva", 32, "root");
        user.addRole(roleService.add(roleUser));
        userService.add(user);

        User admin = new User("admin@mail.ru", "Ivan", "Petrov", 18, "root");
        admin.addRole(roleService.add(roleAdmin));
        userService.add(admin);

    }
}
