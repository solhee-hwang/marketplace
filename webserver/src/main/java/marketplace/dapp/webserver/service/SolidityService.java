package marketplace.dapp.webserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marketplace.dapp.webserver.controller.DTO.ContractRequest;
import marketplace.dapp.webserver.domain.goods.Goods;
import marketplace.dapp.webserver.domain.goods.GoodsRepository;
import marketplace.dapp.webserver.domain.user.UserRepository;
import marketplace.dapp.webserver.solidity.MarketplaceAbi;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolidityService {
    private final GoodsRepository goodsRepository;
    private final UserRepository userRepository;

    public void registGoods(MarketplaceAbi contract,Long goodsId, BigInteger GOODSID) throws Exception {
        Goods getGoods = goodsRepository.findById(goodsId).get();
        BigInteger PRICE = BigInteger.valueOf(getGoods.getPrice());
        RemoteFunctionCall<TransactionReceipt> createItem = contract.createItem(GOODSID, PRICE);
        createItem.send();
        RemoteFunctionCall<Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>> getDetails = contract.getItemDetailsByGoodsId(GOODSID);
        Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger> details = getDetails.send();
        int auctionIndex = details.component2().intValue();
        getGoods.updateAuctionIndex(auctionIndex);
        goodsRepository.save(getGoods);
    }

    public void bidplacing(MarketplaceAbi contract, ContractRequest contractRequest, BigInteger price) throws Exception {
        int getGoodsAuctionIndex = goodsRepository.findById(contractRequest.getGoodsId())
                .orElseThrow(() -> new RuntimeException("없는 아이디입니다")).getAuctionIndex();
        String userAccounts = userRepository.findById(contractRequest.getGoodsId())
                .orElseThrow(()->new RuntimeException("없는 유저아이디")).getMetamaskAccounts();
        RemoteFunctionCall<TransactionReceipt> bidPlacing = contract.placeBid(BigInteger.valueOf(getGoodsAuctionIndex),price);
        bidPlacing.send();


    }
}
