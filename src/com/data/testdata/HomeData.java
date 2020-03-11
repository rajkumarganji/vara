package com.data.testdata;

import com.datamanager.ConfigManager;

public class HomeData {

	ConfigManager appData = new ConfigManager("App");
	// public final String vportURL = constructURL(appData);
	public final String OFS_URL = appData.getProperty("App.OFS.URL");
	public final String BOSS_URL = appData.getProperty("App.BOSS.URL");
	public final String ODT_Tool_URL = appData.getProperty("ODT_Tool_URL");
	public final String ODT_Tool_Import_URL = appData.getProperty("ODT_Tool_Import_URL");
}
