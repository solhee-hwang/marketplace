package marketplace.dapp.webserver.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import marketplace.dapp.webserver.domain.user.User;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String nickName;
    private String password;
    private String address;
    private String metamaskAddress;

    public User toEntity(UserRequest userRequest){
        return User.builder()
                .nickName(userRequest.getNickName())
                .password(userRequest.getPassword())
                .metamaskAccounts(userRequest.getMetamaskAddress())
                .address(userRequest.getAddress())
                .build();
    }
}
