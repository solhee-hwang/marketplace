package marketplace.dapp.webserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marketplace.dapp.webserver.controller.DTO.GoodsRequest;
import marketplace.dapp.webserver.domain.goods.Goods;
import marketplace.dapp.webserver.domain.goods.GoodsRepository;
import marketplace.dapp.webserver.domain.user.User;
import marketplace.dapp.webserver.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final UserRepository userRepository;
    public void create(GoodsRequest goodsRequest) {
        User user = userRepository.findById(goodsRequest.getUserId()).get();
        Goods goods = goodsRequest.toEntity(goodsRequest,user);
        goodsRepository.save(goods);
    }

}
