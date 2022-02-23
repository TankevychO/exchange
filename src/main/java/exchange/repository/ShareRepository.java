package exchange.repository;

import exchange.model.Share;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShareRepository extends JpaRepository<Share, Long> {
    Optional<Share> findByType(Share.Type type);

    @Query("select min(s.price) FROM Share s WHERE s.type = 'ASK' and s.size > 0")
    int findMinPriceForAsk();

    @Query("select max(s.price) FROM Share s WHERE s.type = 'BID' and s.size > 0")
    int findMaxPriceForBid();

    @Query("select s.size from Share s where s.price = ?1")
    int findSizeByPrice(int price);

    Optional<Share> findByPrice(int price);
}
