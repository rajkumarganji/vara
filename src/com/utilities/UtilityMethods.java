/*************************************** PURPOSE **********************************

 - This class contains all Generic methods which are not related to specific application
*/

package com.utilities;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;

public class UtilityMethods {

	static ConfigManager sys = new ConfigManager();
	static ConfigManager comp = new ConfigManager("Cmp");
	static Logger log = Logger.getLogger(UtilityMethods.class);
	static Thread thread;
	// ip address with regular expression
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	/**
	 * Purpose - to get the system name
	 * 
	 * @return - String (returns system name)
	 */
	public static String machineName() {
		String sComputername = null;
		try {
			sComputername = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			log.error("Unable to get the hostname..." + e.getCause());
		}
		return sComputername;
	}

	/**
	 * TODO To get the entire exception stack trace
	 * 
	 * @return returns the stack trace
	 */
	public static String getStackTrace() {
		String trace = "";
		int i;
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (i = 2; i < stackTrace.length; i++) {
			trace = trace + "\n" + stackTrace[i];
		}
		return trace;
	}

	public List<String> compareTowLists(List<String> l1, List<String> l2) throws Exception {
		Collection<String> similar = new HashSet<String>(l1);
		Collection<String> different = new HashSet<String>();
		different.addAll(l1);
		different.addAll(l2);
		l2.removeAll(l1);
		different.removeAll(similar);
		int i = 0;
		System.out.println("====" + l2.size());
		for (String string : l2) {
			comp.writeProperty(Integer.toString(i), string);
			System.out.println("---" + string);

			i++;
		}
		return l2;
	}

	/**
	 * Purpose - to get current date and time
	 * 
	 * @return - String (returns date and time)
	 */
	public static String getCurrentDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	/**
	 * Purpose - to get current date and time
	 * 
	 * @return - String (returns date and time)
	 */
	public static String getCurrentDateTime(String strFormate) {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(strFormate);
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	/**
	 * Purpose-To get current Date in MM/dd.yyy format
	 * 
	 * @param backDay
	 * @return
	 */

	public static String getCurrentDateTime(int backDay) {

		Calendar currentDate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("MM/dd/YYY");
		currentDate.add(Calendar.DATE, backDay);
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;

	}

	/**
	 * Purpose - To convert given time in "yyyy-MM-dd-HH:mm:ss" to IST time
	 * 
	 * @returns date in String format
	 * @throws Exception
	 */

	public static String convertToISTTime(String origTime) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		TimeZone obj = TimeZone.getTimeZone("GMT");
		formatter.setTimeZone(obj);
		try {
			Date date = formatter.parse(origTime);
			formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
			// System.out.println(date);
			return formatter.format(date);
		} catch (ParseException e) {
			log.error("Cannot parse given date .." + origTime);
			log.info("returning current date and time .." + origTime);
		}
		return getCurrentDateTime();
	}

	/**
	 * Purpose - to display message box
	 * 
	 * @param infoMessage
	 * @param location
	 */
	public static void infoBox(String infoMessage, String location) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + location, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Purpose - to stop the execution
	 * 
	 * @param suspendRunImagePath
	 * @throws Exception
	 */
	public static void suspendRun(String suspendRunImagePath) throws Exception {
		Assert.fail("TEST RUN HAS BEEN SUSPENDED");
	}

	/**
	 * Purpose - to generate the random number which will be used while saving a
	 * screenshot
	 * 
	 * @return - returns a random number
	 */
	public static int getRandomNumber() {
		Random rand = new Random();
		int numNoRange = rand.nextInt();
		return numNoRange;
	}

	public static int getRandomNumber(int max) {

		Random r = new Random();
		int randomNum = r.nextInt((max + 1) - 1) + 1;
		return randomNum;
	}

	public static int getOneinTwoRandomly(int a, int b) {
		Random r = new Random();
		int c = r.nextBoolean() ? a : b;
		return c;
	}

	/**
	 * Purpose - to generate the random number which will be used while saving a
	 * screenshot
	 * 
	 * @return - returns a random number
	 */
	public static long getRandomNumberMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * TODO Typecasts the wait time values from String to integer
	 *
	 * @param WaitTime
	 * @return returns value of wait time in integer
	 * @throws Exception
	 */
	public static int getWaitTime(String WaitType) {
		int iSecondsToWait = 15;
		String wait;
		if (WaitType != null && !WaitType.equalsIgnoreCase("")) {
			wait = sys.getProperty(WaitType);
		} else {
			log.error("WaitType cannot be empty...defaulting to MEDIUM WAIT");
			wait = sys.getProperty("WAIT.MEDIUM");
		}
		try {
			iSecondsToWait = Integer.parseInt(wait);
		} catch (NumberFormatException e) {
			log.error("Please check the config file. Wait values must be a number...defaulting to 15 seconds");
		}
		return iSecondsToWait;
	}

	/**
	 * Method: To get caller method and class names
	 */

	public static void preserveMethodName() {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		String callerClassName = elements[1].getClassName();
		String callerMethodName = elements[1].getMethodName();
		String sMethod = "\"" + callerMethodName + "\"" + " method from " + callerClassName + " class";
//		ReportHelper.setsMethodName(sMethod);
	}

	/**
	 * 
	 * TODO method to run balloon popup process/method in a separate thread.
	 *
	 */
	public static void currentRunningTestCaseBalloonPopUp(final String sTestName) {
		thread = new Thread() {
			public void run() {
				BalloonPopUp.currentRunningTestCase(sTestName);
			}
		};
		thread.start();
	}

	/**
	 * 
	 * TODO method to verify if the balloon pop process/method is ended or not.
	 */
	public static void verifyPopUp() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			log.error("balloon popup thread Interrupted" + e.getStackTrace());
		}
	}

	/**
	 * 
	 * TODO Gives the name of operating system your are currently working on
	 * 
	 * @return returns the OS name
	 */
	public static String getOSName() {
		return System.getProperty("os.name");

	}

	/**
	 * 
	 * TODO Gives the seperator value according to Operation System
	 * 
	 * @return returns the seperator with respect to Operation System
	 */
	public static String getFileSeperator() {
		return System.getProperty("file.separator");
	}

	/**
	 * This method is used to generate alert stating that Captcha should be entered
	 * manually within 30 secs.
	 */
	public void waitForCaptcha(WebDriver driver, int timeInSeconds) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"alert('Please enter captcha manually. Captcha should be entered within " + timeInSeconds + " secs');");

		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Validate ip address with regular expression
	 * 
	 * @param ip ip address for validation
	 * @return true valid ip address, false invalid ip address
	 */
	public static boolean validateIP(final String ip) {
		return Pattern.compile(IPADDRESS_PATTERN).matcher(ip).find();
	}

	/**
	 * This method will kills the existing browsers
	 * 
	 */
	public static void clearTasks() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer32.exe");
			// Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			// waitForSecs(4);
			Thread.sleep(4000);
			log.info("Processes successfully closed");
		} catch (IOException e) {
			log.info("Processes already closed/Warning: Permissions are not there to close");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This will return last four milli seconds as random number
	 */
	public static String get4DRandomNumber() {
		String millis = String.valueOf(System.currentTimeMillis() % 100000);
		return millis;

	}

	public static String get5DRandomNumber() {
		String millis = String.valueOf(System.currentTimeMillis() % 1000000);
		return millis;

	}

	/**
	 * Generating the the Random number within given number
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randInt(int min, int max) {

		// NOTE: This will (intentionally) not run as written so that folks
		// copy-pasting have to think about how to initialize their
		// Random instance. Initialization of the Random instance is outside
		// the main scope of the question, but some decent options are to have
		// a field that is initialized once and then re-used as needed or to
		// use ThreadLocalRandom (if using at least Java 1.7).
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	/**
	 * Purpose - to get current date and time Two params timezone and format
	 * 
	 * @return - String (returns date and time)
	 */
	public static String getCurrentDateTime(String strFormate, String tz) {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(strFormate);
		TimeZone obj = TimeZone.getTimeZone(tz);
		formatter.setTimeZone(obj);
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	/**
	 * This method will returns the string of first letters of each word in the
	 * given string
	 * 
	 * @param s
	 * @return
	 */
	public static String getFirstLettersOfEachWord(String s) {
		Pattern p = Pattern.compile("\\b[a-zA-Z]");
		Matcher m = p.matcher(s);
		String s1 = "";
		while (m.find()) {
			s1 += m.group();
		}
		return s1;
	}

	/**
	 * To override the Hotspot JVM arguments
	 */
	public static void disableWarning() {
		System.err.close();
		System.setErr(System.out);
	}

	public static String randomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder(20);
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
}
