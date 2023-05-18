package marketplace.dapp.webserver.controller;

import lombok.RequiredArgsConstructor;
import marketplace.dapp.webserver.controller.DTO.GoodsRequest;
import marketplace.dapp.webserver.service.GoodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/goods")
@RequiredArgsConstructor
@RestController
public class GoodsController {
    private final GoodsService goodsService;
    @PostMapping("create")
    public ResponseEntity<?> createGoods(@RequestBody GoodsRequest goodsRequest){
        goodsService.create(goodsRequest);
        return ResponseEntity.ok("save");
    }
}
