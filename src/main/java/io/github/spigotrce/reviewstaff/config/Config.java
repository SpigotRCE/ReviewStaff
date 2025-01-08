package io.github.spigotrce.reviewstaff.config;

import java.nio.file.Path;

public class Config extends ConfigProvider {
    public Config(Path dataDirectory) {
        super("config.yml", "file-version", dataDirectory.toFile());
    }

    @Override
    public void onReload() {
    }
}
