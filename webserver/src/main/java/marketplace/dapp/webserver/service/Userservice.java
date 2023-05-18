package marketplace.dapp.webserver.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marketplace.dapp.webserver.controller.DTO.UserRequest;
import marketplace.dapp.webserver.domain.user.User;
import marketplace.dapp.webserver.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Builder
@Slf4j
@Service
public class Userservice {
    private final UserRepository userRepository;
    public void signUp(UserRequest userRequest) {
        User user = userRequest.toEntity(userRequest);
        userRepository.save(user);
    }
}
