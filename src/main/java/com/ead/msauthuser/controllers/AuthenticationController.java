package com.ead.msauthuser.controllers;

import com.ead.msauthuser.dtos.UserDto;
import com.ead.msauthuser.enums.UserStatus;
import com.ead.msauthuser.enums.UserType;
import com.ead.msauthuser.models.UserModel;
import com.ead.msauthuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {

        log.debug("POST registerUser userName received {}", userDto.getUsername());

        if (userService.existsByUsername(userDto.getUsername())) {
            log.warn("Username {} is Already Taken", userDto.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken!");
        }

        if (userService.existsByEmail(userDto.getEmail())) {
            log.warn("Email {} is Already Taken", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);

        log.debug("POST registerUser userModel userId {}", userModel.getUserId());
        log.info("User saved successfully userId {}", userModel.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/")
    public String index() {
        log.trace("TRACE"); //detalhamento definidos em arq de configuração
        log.debug("DEBUG"); //ambiente de desenvolvimento definidos em arq de configuração
        log.info("INFO"); //não trás tanto detalhamento, mas em produção trás informações para ter controle do que está acontecendo nos processos.
        log.warn("WARN"); //alerta
        log.error("ERROR"); //erro na aplicação

        try {
            throw new Exception("Message");
        } catch (Exception e) {
            log.error("-----ERROR-----", e);
        }

        return "Logging Spring Boot...";
    }
}
