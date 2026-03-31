package com.opencart.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class HomePage {

	//1. initiat driver and ele util
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. page class constructor..
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	//3. private By locators: page object
	private final By logoutlink = By.linkText("Logout");
	private final By headers = By.cssSelector("div#content h2");
	private final By searchTextField = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");
	
	// 4. public page actions/method:
	
	public String getHomePageTitle() {
		String actTitle = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Home Page title:" + actTitle);
		return actTitle;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.isElementDisplayed(logoutlink);	
		
	}
	
	public List<String> getHomePageHeaders() {
		List<WebElement> headerList = eleUtil.waitForAllElementsPresence(headers, AppConstants.SHORT_TIME_OUT);
		List<String> headersValueList = new ArrayList<String>();
		
		for (WebElement e : headerList) {
			String text = e.getText();
			headersValueList.add(text);
		}
		return headersValueList;
		
	}
	
	public ResultsPage doSearch(String searchkey) {
		System.out.println("Search Key:" + searchkey);
		eleUtil.doSendKeys(searchTextField, searchkey, AppConstants.SHORT_TIME_OUT);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
		
	}
	
	
	
}
