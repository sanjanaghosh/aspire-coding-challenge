package com.odoo.aspire.data;

import com.odoo.aspire.model.Account;
import com.odoo.aspire.model.AccountBuilder;
import com.odoo.aspire.config.AppConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountDataFactory {
    private static final Logger logger = LogManager.getLogger(AccountDataFactory.class);

    public AccountDataFactory() {}

    public Account getAccountInfo() {
        Account account = new AccountBuilder().email(AppConfigManager.configuration().email()).password(AppConfigManager.configuration().password()).build();
        logger.info(account);
        return account;
    }
}
