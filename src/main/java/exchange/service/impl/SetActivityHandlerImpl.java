package exchange.service.impl;

import exchange.model.Share;
import exchange.repository.ShareRepository;
import exchange.service.SetActivityHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SetActivityHandlerImpl implements SetActivityHandler {
    private final ShareRepository shareRepository;

    @Override
    public void doActivity(String type, int size, int price) {
        Share share = new Share();
        share.setPrice(price);
        share.setSize(size);
        share.setType(Share.Type.valueOf(type.toUpperCase()));
        shareRepository.save(share);
    }
}
