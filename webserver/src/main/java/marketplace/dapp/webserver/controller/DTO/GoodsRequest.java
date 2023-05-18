package marketplace.dapp.webserver.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import marketplace.dapp.webserver.domain.goods.Goods;
import marketplace.dapp.webserver.domain.user.User;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRequest {
    private String name;
    private String description;
    private int price;
    private int auctionIndex;
    private Long userId;

    public Goods toEntity(GoodsRequest goodsRequest, User user){
        return Goods.builder()
                .goodsName(goodsRequest.getName())
                .price(goodsRequest.getPrice())
                .soldOut(false)
                .user(user)
                .description(goodsRequest.getDescription()).build();
    }


    public void setAuctionIndex(int auctionIndex) {
        this.auctionIndex = auctionIndex;
    }
}
