package marketplace.dapp.webserver.domain.goods;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
}
