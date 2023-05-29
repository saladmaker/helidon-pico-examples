package pico.example.nima;

import io.helidon.pico.api.Contract;

@Contract
public interface Notification {

    public String message(String message);

}
