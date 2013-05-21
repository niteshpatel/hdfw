package com.herodevelop.hdfw;

public interface Billable {

    public void startSetup();

    public boolean isGdxIabSetupFinished();

    public void queryInventoryAsync();

    public void consumeAsync();

    public void destroy();
}
