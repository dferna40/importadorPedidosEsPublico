package com.espublico.importadorPedidos.service.impl;

import com.espublico.importadorPedidos.dto.RegisterDTO;
import com.espublico.importadorPedidos.model.Roles;
import com.espublico.importadorPedidos.model.User;
import com.espublico.importadorPedidos.repository.RolesRepository;
import com.espublico.importadorPedidos.repository.UserRepository;
import com.espublico.importadorPedidos.service.IRegistrationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.function.Supplier;

@Service
public class RegistrationServiceImpl implements IRegistrationService {

    @Autowired
    RolesRepository rolesRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Inyectamos dependencias a través del constructor
    public RegistrationServiceImpl(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        if (userRepository.existsByUserName(registerDTO.getUsername())) {
            System.out.println("");
        }
        User user = new User();
        user.setUserName(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        // Setea el rol de usuario
        Roles roles = rolesRepository.findByName("ADMIN").orElseThrow(new Supplier<EntityNotFoundException>() {
            @Override
            public EntityNotFoundException get() {
                return new EntityNotFoundException("Rol no encontrado");
            }
        });
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
    }
}
