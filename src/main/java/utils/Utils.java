package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import web.Locations;

public class Utils {
	
	/**
	 * Log4j Logger.
	 */
	private final static Logger LOG = LogManager.getLogger(Locations.class);
	static ConfigLoader loader = ConfigLoader.getInstance();
	private static Properties prop;
	final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	final static Random rand = new Random();

	final static Set<String> identifiers = new HashSet<String>();

	public String generateRandomName() {
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public String generateRandomEmail() {
		return "ssautomationtest+" + generateRandomName() + "@gmail.com";
		// return generateRandomName() + "@gmail.com";
	}

	static int accountNumber = 1;

	public String generateRandomEmail(String prefix) {
		return prefix + "+automation" + accountNumber++ + "@gmail.com";
	}

	public void writeDataToTextFile(String newEmail) throws IOException {
		FileWriter fw = null;
		try {
			String filename = System.getProperty("user.dir") + File.separator + "target" + File.separator + "WebQA.txt";
			fw = new FileWriter(filename, true);
			fw.write(newEmail + "\n");
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	public String getLoginDetails(String username) {
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config" + File.separator + "accounts.properties";
		prop = propertyUtils.propertyLoader(filePath);
		return prop.getProperty(username);
	}

	/**
	 * Method to read gift card details based on required balance
	 *
	 * @param username
	 * @return
	 */
	public Map<String, String> getGiftCardDetails() {
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config" + File.separator + "giftcards.properties";
		prop = propertyUtils.propertyLoader(filePath);
		Map<String, String> gcMap = new HashMap<>();
		try {
			Scanner inFile = new Scanner(new File(filePath));
			while (inFile.hasNext()) {
				StringBuilder sb = new StringBuilder();
				sb.append(inFile.nextLine());
				String str = sb.toString();
				LOG.info(str);
				gcMap.put(str.split(",")[0], str.split(",")[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return gcMap;
	}

	/**
	 * Method to read gift cards
	 *
	 * @param amountCondition - less, high, zero
	 * @param noOfGiftCards   - 1, 2, 3 etc.
	 * @return - {gcNumber, gcPin} map containing gift card number and gift card pin
	 */
//	public Map<String, String> getGiftCardDetails(String amountCondition, int noOfGiftCards) {
//		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//				+ File.separator + "resources" + File.separator + "config" + File.separator + "giftcards.properties";
//		prop = propertyUtils.propertyLoader(filePath);
//		Map<String, String> gcMap = new HashMap<>();
//		try {
//			Scanner inFile = new Scanner(new File(filePath));
//			while (inFile.hasNext()) {
//				StringBuilder sb = new StringBuilder();
//				sb.append(inFile.nextLine());
//				String str = sb.toString();
//				// LOG.info(str);
//				gcMap.put(str.split(",")[0], str.split(",")[1]);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return getRequiredGiftCards(gcMap, amountCondition, noOfGiftCards);
//	}

//	public Map<String, String> getRequiredGiftCards(Map<String, String> gcMap, String amountCondition,
//			int noOfGiftCards) {
//		Map<String, String> validGcMap = new HashMap<>();
//		int count = 0;
//		switch (amountCondition) {
//		case "less": {
//			for (Map.Entry<String, String> mapElement : gcMap.entrySet()) {
//				if (count == noOfGiftCards)
//					break;
//
//				String gcNumber = mapElement.getKey();
//				String gcPin = mapElement.getValue();
//				new SOAPRequest().giftCardBalanceRequest(gcNumber);
//				Double gcAmount = Double.parseDouble(loader.getProperty("giftCardBalance"));
//				if (gcAmount < 2) {
//					LOG.info("[!!!] Recharging gift card -> " + gcNumber);
//					new SOAPRequest().giftCardRechargeRequest(gcNumber, "1.0");
//					if (Double.parseDouble(loader.getProperty("giftCardBalance")) < 1.0)
//						continue;
//					else {
//						validGcMap.put(gcNumber, gcPin);
//						count++;
//						LOG.info("Found " + count + " cards with " + amountCondition + " amount");
//					}
//				} else if (gcAmount > 0 && gcAmount < 5) {
//					validGcMap.put(gcNumber, gcPin);
//					count++;
//					LOG.info("Found " + count + " cards with " + amountCondition + " amount");
//				}
//			}
//			break;
//		}
//		case "high": {
//			for (Map.Entry<String, String> mapElement : gcMap.entrySet()) {
//				if (count == noOfGiftCards)
//					break;
//				new SOAPRequest().giftCardBalanceRequest(mapElement.getKey());
//				Double gcAmount = Double.parseDouble(loader.getProperty("giftCardBalance"));
//				if (gcAmount > 100) {
//					String gcNumber = mapElement.getKey();
//					String gcPin = mapElement.getValue();
//					validGcMap.put(gcNumber, gcPin);
//					count++;
//					LOG.info("Found " + count + " cards with " + amountCondition + " amount");
//				} else if (gcAmount > 20 && gcAmount < 100) {
//					String gcNumber = mapElement.getKey();
//					new SOAPRequest().giftCardRechargeRequest(gcNumber, "100.0");
//					if (Double.parseDouble(loader.getProperty("giftCardBalance")) > 100) {
//						String gcPin = mapElement.getValue();
//						validGcMap.put(gcNumber, gcPin);
//						count++;
//						LOG.info("Found " + count + " cards with " + amountCondition + " amount");
//					}
//				}
//			}
//			break;
//		}
//		case "zero": {
//			for (Map.Entry<String, String> mapElement : gcMap.entrySet()) {
//				if (count == noOfGiftCards)
//					break;
//				new SOAPRequest().giftCardBalanceRequest(mapElement.getKey());
//				Double gcAmount = Double.parseDouble(loader.getProperty("giftCardBalance"));
//				if (gcAmount == 0) {
//					String gcNumber = mapElement.getKey();
//					String gcPin = mapElement.getValue();
//					validGcMap.put(gcNumber, gcPin);
//					count++;
//					LOG.info("Found " + count + " cards with " + amountCondition + " amount");
//				}
//			}
//			break;
//		}
//
//		default:
//			LOG.info("Could not find the expected gift card with condition as " + amountCondition + " amount"
//					+ noOfGiftCards + " cards");
//		}
//
//		return validGcMap;
//	}

	public List<String> getAccountList() {
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config" + File.separator + "accounts.properties";
		prop = propertyUtils.propertyLoader(filePath);
		List<String> accountList = new ArrayList<>();
		try {
			Scanner inFile = new Scanner(new File(filePath));
			while (inFile.hasNext()) {
				StringBuilder sb = new StringBuilder();
				sb.append(inFile.nextLine());
				String str = sb.toString().split("=")[1];
				accountList.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return accountList;
	}

	public static void sendSlackChatNotificationUsingBolt(String messageToSend) {
		String token = loader.getProperty("slackAppToken");
		String channelId = loader.getProperty("slackChannelId");
		Slack slack = Slack.getInstance();

		MethodsClient methods = slack.methods(token);
		ChatPostMessageRequest request = ChatPostMessageRequest.builder().channel(channelId)
				.text(messageToSend).build();

		ChatPostMessageResponse response;
		try {
			response = methods.chatPostMessage(request);
			// LOG.info(response.getMessage());
			LOG.info("Response status of Slack-API call: " + response.isOk());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SlackApiException e) {
			e.printStackTrace();
		}

		try {
			slack.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
