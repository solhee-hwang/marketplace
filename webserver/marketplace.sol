pragma solidity ^0.8.0;

contract Marketplace {
    
    address payable public owner;
    uint public itemCount = 0;
    mapping(uint => Item) public items;
    
    struct Item {
        uint goodsId;
        uint index;
        uint price;
        address payable seller;
        bool soldOut;
        address payable buyer; // 수정된 부분
        address payable highestBidder;
        uint highestBid;
    }
    
    event ItemCreated (
        uint goodsId,
        uint index,
        uint price,
        address payable seller,
        bool soldOut
    );
    

    event BidPlaced (
        uint index,
        uint price,
        address payable bidder,
        uint bid
    );
    
    constructor() {
        owner = payable(msg.sender);
    }
    
    modifier onlyOwner() {
        require(msg.sender == owner);
        _;
    }
    
    function createItem(uint _goodsId ,uint _price) public {
        require(_price > 0);
        itemCount ++;
        items[itemCount] = Item(_goodsId,itemCount, _price, payable(msg.sender), false, payable(address(0)), payable(address(0)), 0); // 수정된 부분
        emit ItemCreated(_goodsId,itemCount, _price, payable(msg.sender), false);
    }
    
    function getItems() public view returns (Item[] memory) {
        Item[] memory _items = new Item[](itemCount);
        for (uint i = 1; i <= itemCount; i++) {
            _items[i - 1] = items[i];
        }
        return _items;
    }
    //  경매참여 함수(물건 거래)
    function placeBid(uint _index) public payable {
        Item storage _item = items[_index];
        require(msg.value > _item.highestBid, "Bid is not high enough");
        require(!_item.soldOut, "Item is already sold");
        require(_item.seller != msg.sender, "Seller cannot bid on their own item");
        
        if (_item.highestBidder != address(0)) {
            // Refund the previous highest bidder if there was one
            _item.highestBidder.transfer(_item.highestBid);
        }
        
        _item.highestBidder = payable(msg.sender);
        _item.highestBid = msg.value;
        
        emit BidPlaced(_item.goodsId, _item.price, payable(msg.sender), msg.value);
    }
    function soldout(uint _index) public onlyOwner {
        Item storage _item = items[_index];
        require(_item.index > 0 && _item.index <= itemCount, "Invalid item id");
        require(!_item.soldOut, "Item is already sold");
        _item.soldOut = true;
    }
    function getItemDetails(uint _index) public view returns (uint, uint,uint, address, bool, uint) {
    Item storage _item = items[_index];
    require(_item.index > 0 && _item.index <= itemCount, "Invalid item id");
    return (_item.goodsId, _item.index,_item.price, _item.highestBidder, _item.soldOut, _item.highestBid);
}


}
