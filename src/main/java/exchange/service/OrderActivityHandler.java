package exchange.service;

public interface OrderActivityHandler {
    void doActivity(String typeOrder, int size);
}
