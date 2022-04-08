package com.odoo.aspire.config;

import org.aeonbits.owner.ConfigCache;

public class AppConfigManager {

    private AppConfigManager() {
    }

    public static AppConfig configuration() {
        return ConfigCache.getOrCreate(AppConfig.class);
    }
}

