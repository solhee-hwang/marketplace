# marketplace

### 중고거래에 경매서비스를 붙인 Dapp

marketplace.sol

- 서버에 있는 goodsId를 가져와 경매에 등록
- 경매에 참여하고자 하는 유저는 본인 계정과 참여 가격을 입력하고 경매참여
- 경매 참여시 이더는 소모, 최고가를 입력한 유저가 나타나면 기존에 입력했던 이더 반환
- 판매자가 soldout처리하면 해당 아이템 경매 종료

백엔드 서버는 회원정보와 아이템정보를 넣어두는 용도로만 간단하게 구현

---


Web3j에서 abi를 java 클래스로 바꾸는 명령어
```
web3j generate solidity -a ./webserver/marketplaceAbi.js -o ./webserver/src/main/java -p marketplace.dapp.webserver
```
