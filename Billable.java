package com.herodevelop.hdfw;

public interface Billable {

    public void startSetup();

    public boolean isGdxIabSetupFinished();

    public void queryInventoryAsync();

    public boolean isGdxQueryInventoryFinished();

    public void consumeAsync();

    public boolean isGdxConsumeFinished();

    public void launchPurchaseFlow();
}
