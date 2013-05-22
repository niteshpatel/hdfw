package com.herodevelop.hdfw;

public interface Billable {

    public void startSetup();

    public void queryInventoryAsync();

    public void consumeAsync();

    public void launchPurchaseFlow();

    public boolean isAsyncInProgress();

    public boolean isIabHelperSetup();
}
