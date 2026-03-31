package com.opencart.qa.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;

import com.opencart.qa.utils.AppConstants;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());			
	}
	
	@Test
	public void homePageTitleTest() {
		String homePageActTitle =  homePage.getHomePageTitle();
		Assert.assertEquals(homePageActTitle, AppConstants.HOME_PAGE_TITLE);
	}
	public void logoutLinksExistTest() {
		Assert.assertTrue(homePage.isLogoutLinkExist());
	}
	@Test
	public void headersTest() {
		List<String> actHeadersList = homePage.getHomePageHeaders();
		Assert.assertEquals(actHeadersList, AppConstants.Exp_Headers_List);
	}
	
	//macbook--3
	//imac--1
	//samsung--2
	//airtel--0
	//5x2 data 5 rows and 2 columns
	
	@DataProvider
	public Object [][] getsearchTestData() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"canon", 1},
			{"samsung", 2},
			{"airtel", 0}	
		};	
		
	}
	
	@Test(dataProvider = "getsearchTestData")
	public void searchTest(String searchKey, int expResultsCount){
		resultsPage = homePage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(), expResultsCount);
		
		
	}
}
