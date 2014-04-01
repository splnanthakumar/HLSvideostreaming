package com.example.videostreaming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class C {

	public static String failure = "failure";
	public static String access_denied = ("Access denied! Sorry you are not authorized to access this page.");
	public static SharedPreferences shared;
	public static SharedPreferences.Editor editor;
	public static int stat = 0;
	public static String next_title;
	public static String next_percent;

	public static String user = "tenant";
	public static String empty = "";
	public static String address = "http://www.addressfinder.co.nz/api/location";
	public static String MSG_CONN = "Please Check Your Internet Connection";
	public static String FILE_DWLD = "http://twcms.indivar.info/api/download/file/";
	public static String SMS = "http://api.clickatell.com/http/auth?user=tenantwallet&password=IKAcWURFLGaRIQ&api_id=3437254";
	// login
	public static String LOGIN_URL = "http://twcms.indivar.info/api/user/login";
	public static String SIGNUP_URL = "http://twcms.indivar.info/api/singup";
	public static String EMAIL_URL = "http://twcms.indivar.info/getusername/";
	public static String REQPASS_URL = "http://twcms.indivar.info/api/request/password";
	public static String SECANS_URL = "http://twcms.indivar.info/api/security/question/track";
	public static String NEWPASS = "http://twcms.indivar.info/api/ask/newpassword";
	public static String CHNGE_SEC = "http://twcms.indivar.info/api/get_current_security_question";
	public static String UP_CHNGE_SEC = "http://twcms.indivar.info/api/change/security_question";
	public static String USERINFO_URL = "http://twcms.indivar.info/api/user/info";
	public static String UPDATE_USERINFO = "http://twcms.indivar.info/api/user/profile/";
	public static String ROLE_LIST_URL = "http://twcms.indivar.info/api/userrolelist";
	public static String RESET_PASS = "http://twcms.indivar.info/api/reset/password";
	public static String SEC_QUE = "http://twcms.indivar.info/api/get_all_security_question";
	public static String RENT_PAY = "http://twcms.indivar.info/api/add/rent_payment";
	public static String ENTITY = "http://twcms.indivar.info/api/select_fields_values/invite_flatmates_sponsors";
	public static String TERMS = "http://twcms.indivar.info/api/user/terms_and_conditions";
	public static String INACTIVE_CC = "http://twcms.indivar.info/api/inactive/nid/credit_card";
	public static String PROF_STAT = "http://twcms.indivar.info/api/profile/complete";
	public static String HELP = "http://twcms.indivar.info/api/select_fields_values/help_request";
	public static String FILE_DOWNLD = "http://twcms.indivar.info/api/rental/download/file";

	// add
	public static String ADD = "http://twcms.indivar.info/api/add";
	public static String BANK_DETS = "http://twcms.indivar.info/api/add/bank_details";
	public static String CREATE_RENTAL = "http://twcms.indivar.info/api/add/create_rental";
	public static String DOC_STORE = "http://twcms.indivar.info/api/add/document_store";
	public static String CREDIT_CARD = "http://twcms.indivar.info/api/add/credit_card";
	public static String DIR_DEB_ACC = "http://twcms.indivar.info/api/add/direct_debit_accounts";
	public static String REC_ACC = "http://twcms.indivar.info/api/add/recieving_bank_account";
	public static String SUB_CONTENT = "http://twcms.indivar.info/api/add/subscription_content_type";
	public static String CREATE_UTIL = "http://twcms.indivar.info/api/add/create_utility";
	public static String UTIL_PAYMENT = "http://twcms.indivar.info/api/add/utility_payments";
	public static String ALERT = "http://twcms.indivar.info/api/add/alerts";
	public static String ALERT_VAL = "http://twcms.indivar.info/api/select_fields_values/alerts";
	public static String ADD_SCH_REMIND = "http://twcms.indivar.info/api/add/schedule_reminders";
	public static String ADD_SCH_PAYMENT = "http://twcms.indivar.info/api/user/schedule_payments";
	public static String ADD_INVITE = "http://twcms.indivar.info/api/add/invite_flatmates_sponsors";
	public static String CREATE_ACC_FORPAYMENT = "http://twcms.indivar.info/api/add/select_account_for_payment";
	public static String SUB_VALUES = "http://twcms.indivar.info/api/select_fields_values/subscription_content_type";

	public static String RENTAL_REQS = "http://twcms.indivar.info/api/user/rental_requests";
	public static String RBA_REFER_NO = "http://twcms.indivar.info/api/reference_number/recieving_bank_account";
	public static String DDA_REFER_NO = "http://twcms.indivar.info/api/reference_number/direct_debit_accounts";
	public static String SCH_REMIND = "http://twcms.indivar.info/api/select_fields_values/schedule_reminders";
	// view
	public static String V_MYSENT_RENTALS = "http://twcms.indivar.info/api/user/my_created_rentals";
	public static String V_BANK_DETS = "http://twcms.indivar.info/api/view/bank_details";
	public static String V_CREATE_RENTAL = "http://twcms.indivar.info/api/view/create_rental";
	public static String V_DOC_STORE = "http://twcms.indivar.info/api/view/document_store";
	public static String V_CREDIT_CARD = "http://twcms.indivar.info/api/view/credit_card";
	public static String V_REC_ACC = "http://twcms.indivar.info/api/view/recieving_bank_account";
	public static String V_DIR_DEB_ACC = "http://twcms.indivar.info/api/view/direct_debit_accounts";
	public static String V_SUB_CONTENT = "http://twcms.indivar.info/api/view/subscription_content_type";
	public static String V_CREATE_UTIL = "http://twcms.indivar.info/api/view/create_utility";
	public static String V_UTIL_PAYMENT = "http://twcms.indivar.info/api/view/utility_payments";
	public static String V_BANK = "http://twcms.indivar.info/api/select_fields_values/bank_details";
	public static String V_ALERT = "http://twcms.indivar.info/api/view/alerts";
	public static String V_INVITE = "http://twcms.indivar.info/api/view/invite_flatmates_sponsors";
	public static String V_MYRENTALS = "http://twcms.indivar.info/api/user/my_rentals";
	public static String V_ACCOUNT_REQS = "http://twcms.indivar.info/api/user/service_account_requests";
	public static String V_REQ_PAYMENT = "http://twcms.indivar.info/api/user/requested_payments";
	public static String V_CARDS = "http://twcms.indivar.info/api/select_fields_values/rent_payment";
	public static String V_SCH_REMINDERS = "http://twcms.indivar.info/api/view/schedule_reminders";
	public static String CHECK_CC = "http://twcms.indivar.info/api/check_card_status";
	public static String CC_LENGTH = "http://twcms.indivar.info/api/find_number_of/credit_card";
	public static String SENDEMAIL = "http://twcms.indivar.info/api/add/help_request";
	// edit
	public static String E_BANK_DETS = "http://twcms.indivar.info/api/edit/nid/bank_details";
	public static String E_CREATE_RENTAL = "http://twcms.indivar.info/api/edit/nid/create_rental";
	public static String E_DOC_STORE = "http://twcms.indivar.info/api/edit/nid/document_store";
	public static String E_CREDIT_CARD = "http://twcms.indivar.info/api/edit/nid/credit_card";
	public static String E_REC_ACC = "http://twcms.indivar.info/api/edit/nid/recieving_bank_account";
	public static String E_DIR_DEB_ACC = "http://twcms.indivar.info/api/edit/nid/direct_debit_accounts";
	public static String E_SUB_CONTENT = "http://twcms.indivar.info/api/edit/nid/subscription_content_type";
	public static String E_CREATE_UTIL = "http://twcms.indivar.info/api/edit/nid/create_utility";
	public static String E_UTIL_PAYMENT = "http://twcms.indivar.info/api/edit/nid/utility_payments";
	public static String SUBSCRIPTION_CONTENT_TYPE = "http://twcms.indivar.info/api/edit/nid/utility_payments";

	public static String S_CREATE_RENTAL = "http://twcms.indivar.info/api/select_fields_values/create_utility";

	// notifications
	public static String N_UPRENT_REQ = "http://twcms.indivar.info/api/user/upcoming_rentals";
	public static String N_UPSERV_REQ = "http://twcms.indivar.info/api/user/upcoming_service_accounts";
	public static String N_GEN_RENT = "http://twcms.indivar.info/api/user/generated_rentals";
	public static String N_GEN_SER = "http://twcms.indivar.info/api/user/generated_service_accounts";

	public static String N_PENDING = "http://twcms.indivar.info/api/user/pending_activations";
	// not for sponsor only
	public static String N_SPONS_REQ_SENT = "http://twcms.indivar.info/api/user/sponsor_request_sent";
	public static String N_SPONS_REQ_REC = "http://twcms.indivar.info/api/user/sponsor_request_recieved";
	// MOB NO VERIFY
	public static String N_VER_MOBNO = "http://twcms.indivar.info/api/verify_mobile_number";

	private static Toast toast;

	static ProgressDialog process;

	public static void progressStart(Context context, String title, String msg) {

		process = ProgressDialog.show(context, title, msg);
	}

	public static void progressStop() {

		process.dismiss();
	}

	public static void shared_Init(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
	}

	public static void setSharedValue(Context context, String key, String value) {
		shared_Init(context);
		editor = shared.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getSharedValue(Context context, String key) {
		shared_Init(context);
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString(key, null);

	}

	public static String getTokenid(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("token_id", null);

	}

	public static String getRoleid(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("role_id", null);

	}

	public static String getUsername(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("username", null);

	}

	public static String getTWid(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("twid", null);

	}

	public static String getUid(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("uid", null);

	}

	public static String getUserRole(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("userrole", null);

	}

	public static String getLogin(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("login", null);

	}

	public static String getStatus(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("status", null);

	}

	public static String getNextField(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("next_field", null);

	}

	public static String getNextPercent(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("next_percent", null);

	}

	public static String getEmail(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("email", null);

	}

	public static String getPassword(Context context) {
		shared = context.getSharedPreferences("Storing Internal",
				Context.MODE_PRIVATE);
		return shared.getString("password", null);

	}

	public static void ToastShort(Context context, String msg) {

		toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		toast.show();

	}

	public static void ToastLong(Context context, String msg) {

		toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
		toast.show();
	}

	public static void ToastCancel(Context context) {
		if (toast != null)
			toast.cancel();

	}

	public static String ConvertStreamintoString(InputStream content) {
		// TODO Auto-generated method stub
		String rline = "";
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(content));
		try {
			while ((rline = bf.readLine()) != null) {
				sb.append(rline);
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void test(Context context, String msg) {
		try {
			throw new Exception("Who called me?");
		} catch (Exception e) {
			Toast.makeText(context,
					"class name" + e.getStackTrace()[1].getClassName(), 2000)
					.show();
		}
	}

	public static void hideKeyboard(Activity activity, View v) {
		InputMethodManager in1 = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		in1.hideSoftInputFromWindow(v.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static void check(RadioButton rd1, final RadioButton rd2) {
		rd1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (buttonView.isChecked()) {
					rd2.setChecked(false);
				} else {
					rd2.setChecked(true);
				}
			}
		});
	}

	public static List<String> getAbsolute(String value) {
		List<String> val = new ArrayList<String>();
		val = Arrays.asList(value.split(","));
		List<String> val1 = new ArrayList<String>();
		for (int index = 0; index < val.size(); index++) {
			val1.add(val.get(index).toString().replaceAll("[\\[\"\\]{}]", ""));
			Log.v("values are  ", "" + val1.get(index));
		}
		return val1;
	}

	public static void setValues(List<String> list,
			ArrayAdapter<String> adapter, Spinner spinner, Context context) {
		// TODO Auto-generated method stub
		adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		spinner.setAdapter(adapter);

	}

	public static String UniqueId() {

		String s = "tw" + UUID.randomUUID().toString();
		return s.substring(0, 12);
	}

	public static String putStatusOn(String admin, String ten_app, String date,
			String name, String id) {
		// TODO Auto-generated method stub

		if (ten_app.equals("[]") || ten_app.equals("pending")) {
			return "Awaiting Acceptence from " + name;
		} else if (ten_app.equals("accept")) {

			return "Approved by " + name + " on " + date;
		} else
			return "Rejected by " + name;

	}

	public static String putStatusOff(String admin, String ten_app,
			String date, String id, String name) {
		// TODO Auto-generated method stub
		Log.v("tenant approval is :", "" + ten_app);
		if (admin.equals("pending"))
			return "Waiting for Confirmation From Support Team (Request ID: "
					+ id + ")";
		else if (admin.equals("approved"))
			return putStatusOn("", ten_app, date, name, id);
		else
			return "Rejected by Admin on" + date;

	}

}
