package pico.example.nima;

import io.helidon.common.http.GET;
import io.helidon.common.http.HeaderParam;
import io.helidon.common.http.Http;
import io.helidon.common.http.Path;
import io.helidon.common.http.PathParam;
import io.helidon.common.http.QueryParam;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;

@Singleton
@Path("/Nima")
public class NimaResource {
    private static final System.Logger LOGGER = System.getLogger(NimaResource.class.getName());

    private Provider<Notification> notificationService;

    @Inject
    public NimaResource(Provider<Notification> notificationProvider) {
        this.notificationService = notificationProvider;
    }

    @GET
    @Path("/{name}")
    String greetNamed(@PathParam("name") String name,
            @HeaderParam(Http.Header.HOST_STRING) String hostHeader,
            @QueryParam("message") String message) {
        String format = """
                /{name} = %1$s,
                host header = %2$s,
                message query = %3$s,
                notification service = %4$s
                """;
        var notification = notificationService.get().message(message);
        return String.format(format, name, hostHeader, message, notification);
    }

    @PostConstruct
    void init() {
        LOGGER.log(System.Logger.Level.INFO, "post construct lifecycle");

    }

    @PreDestroy
    void clean() {
        LOGGER.log(System.Logger.Level.INFO, "pre destroy lifecycle");
    }

}
