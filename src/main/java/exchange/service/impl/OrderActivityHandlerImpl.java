package exchange.service.impl;

import exchange.model.Share;
import exchange.repository.ShareRepository;
import exchange.service.OrderActivityHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderActivityHandlerImpl implements OrderActivityHandler {
    private static final String ORDER_TYPE_BUY = "buy";
    private static final String ORDER_TYPE_SELL = "sell";
    private final ShareRepository shareRepository;

    @Override
    public void doActivity(String typeOrder, int size) {
        Share share = null;
        if (typeOrder.equals(ORDER_TYPE_BUY)) {
            share = shareRepository.findByPrice(shareRepository.findMinPriceForAsk())
                    .orElseThrow(() ->
                            new RuntimeException("Can't find share with type ask "
                                    + "and size not null"));
        } else if (typeOrder.equals(ORDER_TYPE_SELL)) {
            share = shareRepository.findByPrice(shareRepository.findMaxPriceForBid())
                    .orElseThrow(() -> new RuntimeException("Can't find share with "
                            + "type bid and size not null"));
        }
        if (share != null) {
            if (share.getSize() >= size) {
                int newSize = share.getSize() - size;
                share.setSize(newSize);
                shareRepository.save(share);
            } else {
                size -= share.getSize();
                share.setSize(0);
                shareRepository.save(share);
                doActivity(typeOrder, size);
            }
        }
    }
}
