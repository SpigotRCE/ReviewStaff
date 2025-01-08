package io.github.spigotrce.reviewstaff;


import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import io.github.spigotrce.reviewstaff.config.Config;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Plugin(
        id = "reviewstaff",
        name = "ReviewStaff",
        version = "1.0",
        authors = "SpigotRCE"
)
public class ReviewStaff {
    public static ReviewStaff INSTANCE;

    @Inject
    public static Logger LOGGER;
    @Inject
    @DataDirectory
    public static Path DATA_DIRECTORY;
    @Inject
    public static ProxyServer PROXY_SERVER;

    public static Config CONFIG;

    public static List<ScheduledTask> TASKS;

    @Inject
    public ReviewStaff(Logger logger, @DataDirectory Path dataDirectory, ProxyServer proxyServer) {
        INSTANCE = this;
        LOGGER = logger;
        DATA_DIRECTORY = dataDirectory;
        PROXY_SERVER = proxyServer;
        CONFIG = new Config(DATA_DIRECTORY);
        TASKS = new ArrayList<>();
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        LOGGER.info("Starting ReviewStaff...");
        LOGGER.info("Initializing config...");
        try {
            CONFIG.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("ReviewStaff successfully initialized!");
        LOGGER.info("Thanks for using ReviewStaff!");
        LOGGER.info("Developed by https://github.com/SpigotRCE/");
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        LOGGER.info("Stopping ReviewStaff...");
        TASKS.forEach(ScheduledTask::cancel);
        LOGGER.info("ReviewStaff stopped successfully!");
        LOGGER.info("Bye!");
    }
}
