package exchange.service.impl;

import exchange.model.Share;
import exchange.repository.ShareRepository;
import exchange.service.PrintActivityHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PrintActivityHandlerImpl implements PrintActivityHandler {
    private static final String PRINT_TYPE_BEST_BID = "best_bid";
    private static final String PRINT_TYPE_BEST_ASK = "best_ask";
    private static final String PRINT_TYPE_SIZE = "size";
    private final ShareRepository shareRepository;

    @Override
    public String doActivity(String type, int price) {
        if (type.equals(PRINT_TYPE_BEST_BID)) {
            Share share = shareRepository.findByPrice(shareRepository.findMaxPriceForBid())
                    .orElseThrow(() ->
                    new RuntimeException("Can't find share with type bid and size not null"));
            return share.getPrice() + "," + share.getSize();
        } else if (type.equals(PRINT_TYPE_BEST_ASK)) {
            Share share = shareRepository.findByPrice(shareRepository.findMinPriceForAsk())
                    .orElseThrow(() ->
                    new RuntimeException("Can't find share with type ask and size not null"));
            return share.getPrice() + "," + share.getSize();
        } else if (type.equals(PRINT_TYPE_SIZE)) {
            return String.valueOf(shareRepository.findSizeByPrice(price));
        }
        return "Not correct input data";
    }
}
