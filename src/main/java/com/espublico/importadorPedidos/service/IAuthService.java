package com.espublico.importadorPedidos.service;

import com.espublico.importadorPedidos.dto.RegisterDTO;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface IAuthService {

    String processRegistration(@ModelAttribute RegisterDTO registerDTO);
}
