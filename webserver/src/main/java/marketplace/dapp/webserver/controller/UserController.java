package marketplace.dapp.webserver.controller;

import lombok.RequiredArgsConstructor;
import marketplace.dapp.webserver.controller.DTO.UserRequest;
import marketplace.dapp.webserver.service.Userservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final Userservice userservice;
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest){
        userservice.signUp(userRequest);
        return ResponseEntity.ok("signup");
    }
}
