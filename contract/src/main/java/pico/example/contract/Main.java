package pico.example.contract;

import java.util.Optional;

import io.helidon.config.Config;
import io.helidon.config.ConfigSources;

import io.helidon.logging.common.LogConfig;

import io.helidon.pico.api.Bootstrap;
import io.helidon.pico.api.BootstrapDefault;
import io.helidon.pico.api.PicoServices;
import io.helidon.pico.api.ServiceProvider;

public class Main {

    public static void main(String... args) {

        LogConfig.configureRuntime();

        Optional<Bootstrap> existingBootstrap = PicoServices.globalBootstrap();
        if (existingBootstrap.isEmpty()) {
            Config config = Config.builder()
                    .addSource(ConfigSources.classpath("application.properties"))
                    .disableSystemPropertiesSource()
                    .disableEnvironmentVariablesSource()
                    .build();
            Bootstrap bootstrap = BootstrapDefault.builder()
                    .config(config)
                    .build();
            PicoServices.globalBootstrap(bootstrap);
        }

        PicoServices picoServices = PicoServices.picoServices().get();
        // this line is needed!
        picoServices.services();

        ServiceProvider<Notification> service = picoServices.services().lookupFirst(Notification.class);
        service.get().send("pico notification!");
    }

}
