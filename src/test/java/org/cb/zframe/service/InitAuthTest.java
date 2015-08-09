package org.cb.zframe.service;

import org.cb.zframe.auth.service.InitManager;

public class InitAuthTest extends BaseManagerTestCase {
	private InitManager initManager;

	public void setInitManager(InitManager initManager) {
		this.initManager = initManager;
	}

	public void testInit() {
		try {
			initManager.initAuth("Auth.xls");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
