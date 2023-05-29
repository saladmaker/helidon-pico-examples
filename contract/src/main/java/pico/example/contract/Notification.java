package pico.example.contract;

import io.helidon.pico.api.Contract;

@Contract
public interface Notification {

    public void send(String message);

}
