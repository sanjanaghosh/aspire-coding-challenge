package com.odoo.aspire.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config.properties",
        "classpath:grid.properties",
        "classpath:login.properties"})
public interface AppConfig extends Config {

    @Key("target")
    String target();

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("url.base")
    String url();

    @Key("timeout")
    int timeout();

    @Key("faker.locale")
    String faker();

    @Key("accounts.email")
    String email();

    @Key("accounts.password")
    String password();
}
