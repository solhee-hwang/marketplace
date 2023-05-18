package marketplace.dapp.webserver.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import marketplace.dapp.webserver.domain.goods.Goods;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column
    private String nickName;
    private String password;
    private String metamaskAccounts;
    private String address;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    List<Goods> goodsList = new ArrayList<Goods>();
}
