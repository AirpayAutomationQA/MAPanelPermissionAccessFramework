package com.Airpay.PageObject;

public class AirPay_Payment_MA_Panel_PageObject {
	
	//****************************************MA Panel Locators************************************
	
	public static final String MA_UserID = "//*[contains(@class,'sign-user')]";
	public static final String MA_PWD ="//*[contains(@class,'sign-password')]";
	public static final String MA_SubmitBtn ="//*[@class='login-submit']";
	public static final String MA_HomeMenu ="//*[contains(@class,'home-menu')]";
	public static final String DashBoard ="//span[@class='glyphicon glyphicon-home']";
	public static final String SearchInnerLink ="//a[@href='searchinner.php?f=1']";
	public static final String CaretSymbol ="(//span[@class='caret'])[1]";
	public static final String ChangePWDSetting ="//a[@href='changepassword.php']";
	
	public static final String ReportsNextMenu ="(//span[@class='glyphicon glyphicon-edit pri-menu'])[1]//preceding::a[@class='mm-next'][1]";
	public static final String ReportSubMenu ="(//div[@class='mm-panel mm-hasnavbar mm-highest mm-current mm-opened']//ul//li)";
	public static final String TransactionAnalysis ="(//span[@class='glyphicon glyphicon-signal pri-menu'])[1]//preceding::a[@class='mm-next'][1]";
	public static final String MasterMenuNext ="(//span[@class='glyphicon glyphicon-cog pri-menu'])[1]//preceding::a[@class='mm-next'][1]";
	public static final String BulkProcessingMenuNext ="(//span[@class='glyphicon  glyphicon-cloud-upload pri-menu'])[1]//preceding::a[@class='mm-next'][1]";

	
	
	
	
	
	
	//public static final String ReportsNextMenu ="(//span[@class='glyphicon glyphicon-edit pri-menu'])[1]//preceding::a[@class='mm-next'][1]";

	
	

	
	
	

	
	
	public static final String DashBoardTransactionDetails ="//*[text()='Daily Total Transactions']";

	
	
	
	public static final String MerchantMember_NextBtn ="//*[@class='mm-next' and @href='#mm-1']";
	
	public static final String MasterMenu_NextBtn ="//*[@class='mm-next' and @href='#mm-6']";
    
	
	public static final String MM_Airpay_SettingNextBtn = "//*[@class='mm-next' and @href='#mm-3']";
	public static final String MM_Airpay_Setting ="//*[@href='airpaysettings.php']";
	
	
	
	
	public static final String MM_Merchant_Search ="//*[@class='clear-input required']";
	public static final String MM_Merchant_Select ="(//table[@class='table']/thead/tr/th)";
	public static final String MM_Merchant_IDName ="(//table[@class='table']/tbody/tr/td)[2]";
	public static final String MM_Merchant_SelectBtn ="(//span[contains(@class,'btnlink radius')])";
	public static final String MM_SaveCardFieldName ="(//label[@for='defaultsingleclick_flag'])[1]";
	public static final String MM_CheckbxColor ="(//label[@for='defaultsingleclick_flag' and @class='onoffswitch-label']/span//following::span)[1]";
	public static final String MM_SurchagreCheckbxColor ="(//label[contains(@for,'radioonusoffus') and @class='onoffswitch-label']/span//following::span)[1]";
	public static final String MM_Update_surchagrebtn ="//button[@type='button' and @id='confirm-status-update']";

	
	
	
	
	public static final String MM_SelectMerchantSymbol ="//*[@class='pull-right merchant-change']/a";
	
	public static final String MM_SigleSubmitBtn ="//*[@id='singlebutton']";
	public static final String MM_MAUpdatedMesg ="(//*[contains(@class,'img-block')]//following::p)[1]";
	public static final String MM_PP_SaveCardchkbox ="//*[@class='save-Card-Check']";
	
	public static final String MM_ONOFFCheckBoxSaveCard ="(//label[@for='singleclick_flag' and @class='onoffswitch-label']/span//following::span)[1]";
	public static final String MM_ONOFFCheckBoxSurchargeFlag ="(//label[@for='surcharge_flag' and @class='onoffswitch-label']/span//following::span)[1]";

	
//******************************************MA Panle Payment Mode URL Details locaters *********************************
	
	public static final String MM_PaymentMode_NextBtn ="//*[@class='mm-next' and @href='#mm-2']";
	public static final String MM_PayMent_Mode_UrlLink ="//*[@href='payment_mode_urls.php']";
	public static final String MM_SurChargeMenuLink ="(//a[@href='list_global_surcharge_rules.php'])[1]";

	
	public static final String MM_URLDetails ="//a[@href='#collapseTwo']";
	public static final String MM_GlobalSurchargeRulePage ="//*[text()='Add global surcharge rule']";
	public static final String MM_GlobalSurchargeRules="//*[text()='global surcharge rules']";

	public static final String MM_ConvenienceFeeField ="//*[text()='Convenience Fee']";
	public static final String MA_PTCField ="//*[text()='PTC']";
	public static final String MA_MSFField ="//*[text()='MSF (%)']";
	public static final String MA_AddNewRuleBtn ="//*[@href='add_global_surcharge_rule.php' or @href='add_merchant_surcharge_rule.php']";
	
	public static final String MA_MCCField ="//*[text()='MCC ']";
	public static final String MA_ChannelField ="//*[text()='Channel ']";
	public static final String MA_TitleField ="//*[text()='Title ']";
	public static final String MA_AmountField ="//*[text()='Amount']";

	
	
	
	
	
	
	public static final String MM_BankDetails ="//a[@href='#collapseOne']";

	public static final String MM_FindURLDetails = "(//*[@class='table border_table']/tbody/tr)";
	public static final String MM_FindBANKDetails = "((//*[@class='table']/tbody)[1]/tr)";

	public static final String MM_URLRowVerify ="(//*[@class='table border_table']/tbody/tr)[1]/td";
	public static final String MM_Bank_URL ="//following::tbody[1]/tr";
	
	public static final String MM_Transaction_History ="//a[@href='searchinner.php?f=1']";
	public static final String MM_MerchantTrxID = "//input[@class='clear-input merctransid']";
	public static final String MM_TransactionRecords = "(//table[@class='table']/tbody/tr)";
	
	//*[text()='Transaction Details']
	public static final String MMTransactiondetailsPage =  "//*[text()='Transaction Details']";
	public static final String MMMerchantTransactionID ="//*[text()='Merchant Transaction Id']/following-sibling::td";
	public static final String MMTransacionBank ="//*[text()='Bank']/following-sibling::td";
	public static final String MMTypeOFTransaction ="//*[text()='Type of Transaction']/following-sibling::td";
	public static final String MMTransacionIssuingBankName ="//*[text()='Issuing Bank Name']/following-sibling::td";
	public static final String MMTransacionIssuingBankCardType ="//*[text()='Card Type']/following-sibling::td";
	public static final String MMTransacionCardCategory ="//*[text()='Card Category']/following-sibling::td";
	public static final String MMApTransacionCardCategory ="//*[text()='PG Transaction Id']/following-sibling::td";
	
	public static final String MMTotalAmount = "//*[text()='Amount']/following-sibling::td";
	public static final String MMSurchagerAmt ="//*[text()='Surcharge Amount']/following-sibling::td";
	public static final String MMTransactionStatus ="//*[text()='Transaction Status']/following-sibling::td";
		
	public static final String MMTransacionAirapyMerchantID ="//*[text()='Airpay Merchant Id']/following-sibling::td";
	public static final String MMBankdetailsPage ="//*[contains(text(),'Bank Details')]";
	public static final String MMURLdetailsPage ="//*[contains(text(),'edit url bank mapping')]";

	public static final String MMbankStatus ="//select[@id='bank_status']";
	public static final String MMBankSaveBtn ="//*[@id='btneditbank' or @id='btneditmapping']";
	public static final String BankEditSuccMsg ="//*[contains(text(),'Bank edited successfully')]";
	public static final String URLEditSuccMsg ="//*[@class='success-block']/p";

	public static final String BankURLMiniumVal ="//*[@id='min_amount']";
	public static final String BankURLMaxiumVal ="//*[@id='max_amount']";
	public static final String TotAmt ="//*[@id='total_amount']";

	//****************************************** Surcharge *****************************************
	
	public static final String SurchargeSubmitBtn ="//div[@class='btnlink']/button";
	public static final String TitleErrMsg ="//*[@id='titleadd-error']";
	public static final String MccErrorMsg ="//*[@id='mcc-error']";
	public static final String ChannelErrMsg ="//*[@class='error' and @id='channel-error']";
	public static final String AmonutErrMsg ="//*[@id='amount-error']";
	public static final String PTCErrMsg ="//*[@id='convenience_percentage-error']";  // id got exchanged from developer side
	public static final String MSFErrMsg ="//*[@id='convenience_amount-error']";

	
	
	
	public static final String AmountInput ="//input[@id='amount']";
	
	public static final String FetchExistFirstRule ="(//table[@id='rulelisting' and @class='table']/tbody/tr/td[2])[1]";
	public static final String MCC_FieldClick ="//input[@class='select2-search__field']";
	public static final String MCC_FirstOptionClick ="(//ul[@class='select2-results__options']/li)[1]";
	public static final String MCC_SecondOptionClick ="(//ul[@class='select2-results__options']/li)[2]";
	public static final String MCC_thirdOptionClick ="(//ul[@class='select2-results__options']/li)[3]";

	public static final String ChannelName ="//select[@id='channel']";
	public static final String CardBankName ="(//select[@class='card_scheme' and @style='width: 100%; display: block;'])[1]";
	public static final String AmountSelectBox ="//select[@id='amountSelect']";
	public static final String AmountEditBox ="//input[contains(@class,'replica')]";
	public static final String PTCEditBox ="//input[contains(@id,'convenience_percentage')]";
	public static final String MSFEditBox ="//input[contains(@id,'convenience_amount')]";
	public static final String ExistRuleErrorMsg ="(//*[contains(@class,'img-block')]//following::p)[1]";
	public static final String TitleInput ="//input[@id='titleadd']";

	public static final String MerchantAssignSurchargeRule ="//a[@href='list_merchant_surcharge_rules.php']";
	
	public static final String RuleCreatedVerify ="(//table[@id='rulelisting']/tbody/tr[@id])[1]/td/div[@class='btnlink']";
	public static final String RuleEditButton = "(//table[@id='rulelisting']/tbody/tr[@id])[1]/td/span/a";
	
	public static final String FetchRulename ="(//table[@id='rulelisting']/tbody/tr/td)[2]";

	public static final String BankNameDropBox = "(//select[@class='card_scheme'])";
	public static final String EditGlobalSurchagrePage ="//*[text()='Edit global surcharge rule']";
	public static final String EditMerchantSurchagrePage ="//*[text()='edit merchant surcharge rule']";

	
	
	public static final String RuleEditPopErrorMsg ="//*[text()='No']";


//************************************* Merchant Add surcharge rules ********************************
	
	public static final String MA_AssignSurchargeRuleLink =   "//a[@href='list_merchant_surcharge_rules.php']"; 
	public static final String MA_MerchantSurchageRule     ="//*[text()='Add merchant surcharge rule']";
	public static final String MA_DomesticRadioBtn = "//input[@name='card_country' and @id='radios-0']";
	public static final String MA_InternaltionRadioBtn = "//input[@name='card_country' and @id='radios-1']";
	public static final String MA_CardTypeDropDown ="//select[@id='card_type']";
	public static final String MA_SchemeTypeDropDown ="//select[@id='card-scheme']";
	
	public static final String MA_MerchantEditbtn ="(//table[@id='rulelisting']/tbody/tr[@id])[1]/td/span/a";
	
	
	public static final String MA_AccountDetailsPage =   "//*[text()='accounts details']"; 	
	public static final String MA_MerchantSettingLink =   "//a[@href='setting.php']";
	public static final String MA_MerchantMCCName = "//*[text()='Merchant Category']//following::td[1]";
	public static final String MA_AdvanceSerchBtn = "//*[contains(@class,'radius-5') and @aria-expanded='false']";
	
	
	public static final String MA_FilterCardTypeDropDown = "//select[@class='card_type valid']";
	public static final String MA_FilterChannelDropDown = "//select[@class='channelsearch valid']";
	public static final String MA_FilterMCCDropDown = "//select[@class='mcc valid']";
	public static final String MA_AmountLessGreaterDropDown ="//*[@class='amount valid']";
	public static final String MA_FilterSearchBtn = "//*[@class='btnlink adv-search-btn']";
	
	public static final String OnOffchkboxBtn ="(//*[@class='onoffswitch-switch'])";
		
	/*((//table[@id='rulelisting']/tbody/tr[@id])[1]/td[5]//following::span[@class='onoffswitch-switch'])[1]	
	(//table[@id='rulelisting']/tbody/tr[@id])[1]/td/div[@class='btnlink']
	(//table[@id='rulelisting']/tbody/tr[@id])[2]/td/div[@class='btnlink']//following::span[@class='btnlink radius-5'][1]
*/
	
	//********************************  Surcharge Rule *************************************************
	
	public static final String DisableMCCDropDown = "//select[@id='mcc']/option[@selected]";
	public static final String DisableChannelropDown = "//select[@id='channel']/option[@selected]";
	public static final String DisableCardTypeDropDown = "//select[@id='card_type']/option[@selected]";
	public static final String DisableCardSchemaDropDown = "//select[@id='card-scheme']/option[@selected]";
	public static final String DisableAmountSelectDropDown = "//select[@id='amountSelect']/option[@selected]";
	public static final String DisableAmountfetchData= "//input[@id='amount']";
	
 //*******************************************	express payment channel ************************
	
	public static final String ExpressPaymentPwdLink = "//*[@class='tab-link passwordSection']";
	public static final String ExppressPaymentpwdEdit ="//*[@type='password' and @class ='form-control newPassword signin-password']";
	public static final String ExpresspaymentSubmitBtn = "//*[@class='btn exp-pay-submit']";
	public static final String ExpresspaymentOTPsubmitbtn = "//*[@class='btn verify-otp']";

	
	public static final String ExpressPaymentCardSaves ="//*[@class='mobsvdcard dsksvdcard svdslider']//following::div[contains(@class,'saved-card-wrap')]";
	
	public static final String ExpressPaymentsPWDErrorcode ="//*[contains(@class,'formDom form-group errorvalue')]";
	public static final String ExpressPaymentForgetPWDLink ="//a[@class='float-right frgt-pswd']";
	//public static final String ExpressPaymentForgetPWDLinkSent

	
	
	
	
	

	
	
	
	

	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

	
	
	

	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
