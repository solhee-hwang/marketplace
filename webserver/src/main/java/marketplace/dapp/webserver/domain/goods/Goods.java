package marketplace.dapp.webserver.domain.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import marketplace.dapp.webserver.domain.user.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long goodsId;

    @Column
    private int auctionIndex;
    private String goodsName;
    private String description;
    private int price;
    private boolean soldOut;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void updateAuctionIndex(int auctionIndex) {
        this.auctionIndex = auctionIndex;
    }
}
