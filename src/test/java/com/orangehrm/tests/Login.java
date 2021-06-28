package com.orangehrm.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangehrm.base.Base;
import com.orangehrm.utils.CommonUtilities;

public class Login extends Base {

	@Test
	public static void login_001() throws Exception {
		
		boolean logoutlink = CommonUtilities.validLogin();

		Assert.assertTrue(logoutlink, "logoutlink is displayed");
		

}
	
	//@Test
	public static void login_002() throws Exception {
		
		boolean errormsg = CommonUtilities.inValidLogin();

		Assert.assertTrue(errormsg, "errormsg displayed");
		

}
	
	//@Test
	@Parameters({"username", "password"})
	public static void login_003(String username, String password) throws Exception {
		boolean errormsg=CommonUtilities.inValidLoginWithParameter(username, password);
		
		Assert.assertTrue(errormsg, "errormsg displayed");
		
		
	}

	//@Test(dataProvider = "dataFromExcel", dataProviderClass = com.orangehrm.dataproviders.Dataproviders.class)
	public static void login_004(String username, String password) throws Exception {
		
		boolean logoutlink=CommonUtilities.loginWithDataProvider(username, password);
		Assert.assertTrue(logoutlink, "logout link is displayed");
	}
}
