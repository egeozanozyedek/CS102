package com.has.base;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Taha Khurram
 * @version 5/5/2018
 */
public class Options
{

	//properties
	GlobalOptions globalOptions;
	LocalOptions localOptions;

	//contructor
	public Options() throws IOException
	{

		this.localOptions = new LocalOptions(Options.readFile(LocalOptions.PATH_FOR_WINDOWS));
		this.globalOptions = new GlobalOptions(Options.readFile(GlobalOptions.PATH_FOR_WINDOWS));
//		if (isWindows())
//		{
//			this.localOptions = new LocalOptions(Options.readFile(LocalOptions.PATH_FOR_WINDOWS));
//			this.globalOptions = new GlobalOptions(Options.readFile(GlobalOptions.PATH_FOR_WINDOWS));
//		}
//		if (isLinux())
//		{
//			this.localOptions = new LocalOptions(Options.readFile(LocalOptions.PATH_FOR_LINUX));
//			this.globalOptions = new GlobalOptions(Options.readFile(GlobalOptions.PATH_FOR_LINUX));
//		}
	}

	//methods

	public static String readFile(String pathname) throws IOException
	{

		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");

		try
		{
			while (scanner.hasNextLine())
			{
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		}
		finally
		{
			scanner.close();
		}
	}

	public static void writeStringToFile(String pathName, String text) throws IOException, FileNotFoundException
	{
		try (PrintWriter out = new PrintWriter(pathName))
		{
			out.println(text);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

//	public static boolean isWindows()
//	{
//		return getOsName().indexOf("win") >= 0;
//	}
//
//	public static boolean isLinux()
//	{
//		return getOsName().indexOf("nux") >= 0;
//	}
//
//	public static String getOsName()
//	{
//		return System.getProperty("os.name");
//	}

	/**
	 * returns the localOptions
	 *
	 * @return LocalOptions localOptions
	 */
	public LocalOptions getLocal()
	{
		return this.localOptions;
	}

	/**
	 * returns globalOptions
	 *
	 * @return GlobalOptions globalOptions
	 */
	public GlobalOptions getGlobal()
	{
		return this.globalOptions;
	}
	public void setGlobal(GlobalOptions options)
	{
		this.globalOptions = options;
	}

	public void createJsonFiles()
	{
		try
		{
			writeStringToFile(LocalOptions.PATH_FOR_WINDOWS, this.localOptions.getJsonText().replace("\\", "\\\\"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			writeStringToFile(GlobalOptions.PATH_FOR_WINDOWS, this.globalOptions.getJsonText());
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (isLinux())
//		{
//			try
//			{
//				writeStringToFile(LocalOptions.PATH_FOR_LINUX, this.localOptions.getJsonText());
//			}
//			catch (FileNotFoundException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			catch (IOException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try
//			{
//				writeStringToFile(GlobalOptions.PATH_FOR_LINUX, this.globalOptions.getJsonText());
//			}
//			catch (FileNotFoundException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			catch (IOException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	public enum EmailFrequency
	{
		SEND_DAILY(0),
		SEND_WEEKLY(1),
		SEND_MONTHLY(2),
		SEND_EVERY_2_MONTHS(3),
		SEND_EVERY_6_MONTHS(4),
		SEND_EVERY_YEAR(5);

		private final int value;


		private EmailFrequency(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		private static EmailFrequency[] list = EmailFrequency.values();

		public static EmailFrequency getEmailFrequency(int i)
		{
			return list[i];
		}

		public static String getEmailFrequencyAsString(int i)
		{
			return list[i].toString();
		}
	}

	public class LocalOptions implements Serializable
	{

		public static final String PATH_FOR_WINDOWS = "C:\\Users\\EgeOzan\\Desktop\\HAS-Framework-Merged\\res\\json\\local options.json";
		public static final String PATH_FOR_LINUX = " ";

		//properties
		String videoDownloadPath;
		List<String> securityPin;

		//constructors
		public LocalOptions(String jsonText)
		{
			try
			{
				JSONObject json = new JSONObject(jsonText);
				this.videoDownloadPath = json.getString("video_download_path");
				System.out.println( this.videoDownloadPath);


				JSONArray arr = json.getJSONArray("security_pin");
				this.securityPin = new ArrayList<String>();
				for (int i = 0; i < arr.length(); i++)
				{
					this.securityPin.add(arr.getString(i));
				}
//         System.out.println( videoDownloadPath );
//         for( String x : this.securityPin ) {
//            System.out.println( x );
//         }
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		//methods

		/**
		 * returns the properties
		 *
		 * @return DataContainer properties
		 */
		public String getVideoDownloadPath()
		{
			return this.videoDownloadPath;
		}

		public void setVideoDownloadPath(String videoDownloadPath)
		{
			this.videoDownloadPath = videoDownloadPath;
		}

		public List<String> getSecurityPin()
		{
			return this.securityPin;
		}

//   public boolean checkSecurityPin( List<String> securityPin) {
//	   System.out.println( this.securityPin );
//	   return this.securityPin.equals(securityPin);
//   }

		public void setSecurityPin(List<String> securityPin)
		{
			this.securityPin = securityPin;
		}

		public String getJsonText()
		{
			String result;

			String a = this.securityPin.get(0);
			String b = this.securityPin.get(1);
			String c = this.securityPin.get(2);
			String d = this.securityPin.get(3);

			result = "{\n\"video_download_path\":\"" + this.videoDownloadPath + "\",\n";
			result += "\"security_pin\":[\"" + a + "\",\"" + b + "\",\"" + c + "\",\"" + d + "\"]\n}";

			return result;
		}

	}

	public class GlobalOptions
	{

		public static final String PATH_FOR_WINDOWS = "C:\\Users\\EgeOzan\\Desktop\\HAS-Framework-Merged\\res\\json\\global options.json";
		public static final String PATH_FOR_LINUX = " ";

		//properties
		int temperatureForSmartControl;
		String notificationEmail;
		boolean sendEmailNotifications;
		EmailFrequency emailFrequency;
		int intrusionVideoLength;
		int googleDriveVideosSizeLimit;
		SleepingTime sleepingTime;

		//contructors
		public GlobalOptions(String jsonText)
		{
			try
			{
				JSONObject json = new JSONObject(jsonText);

				this.temperatureForSmartControl = json.getInt("temperature_for_smart_control");
				this.notificationEmail = json.getString("notification_email");
				this.sendEmailNotifications = json.getBoolean("send_email_notifications");
				this.emailFrequency = EmailFrequency.getEmailFrequency(json.getInt("email_frequency"));
				this.intrusionVideoLength = json.getInt("intrusion_video_length");
				this.googleDriveVideosSizeLimit = json.getInt("google_drive_videos_size_limit");
				System.out.println( this.googleDriveVideosSizeLimit);

				JSONArray arr = json.getJSONArray("sleeping_time");
				System.out.println( arr);
				System.out.println(arr.getInt(0) );
				System.out.println(arr.getInt(1) );
				System.out.println(arr.getInt(2) );
				System.out.println(arr.getInt(3) );


				this.sleepingTime = new SleepingTime(arr.getInt(0), arr.getInt(1), arr.getInt(2), arr.getInt(3));

//         System.out.println( this.temperatureForSmartControl );
//         System.out.println( this.notificationEmail );
//         System.out.println( this.sendEmailNotifications );
//         System.out.println( this.emailFrequency );
//         System.out.println( this.intrusionVideoLength );
//         System.out.println( this.googleDriveVideosSizeLimit );
//         for( int x : this.sleepingTime ) {
//            System.out.println( x );
//         }
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		//methods



		public String getNotificationEmail()
		{
			return this.notificationEmail;
		}

		public void setNotificationEmail(String notificationEmail)
		{
			this.notificationEmail = notificationEmail;
		}

		public boolean getSendEmailNotifications()
		{
			return this.sendEmailNotifications;
		}

		public void setSendEmailNotifications(boolean sendEmailNotifications)
		{
			this.sendEmailNotifications = sendEmailNotifications;
		}

		public int getTemperatureForSmartControl()
		{
			return this.temperatureForSmartControl;
		}

		public void setTemperatureForSmartControl(int temperatureForSmartControl)
		{
			this.temperatureForSmartControl = temperatureForSmartControl;
		}

		public int getEmailFrequency()
		{
			return this.emailFrequency.ordinal();
		}

		public void setEmailFrequency(int emailFrequency)
		{
			this.emailFrequency = EmailFrequency.getEmailFrequency(emailFrequency);
		}

		public int getIntrusionVideoLength()
		{
			return this.intrusionVideoLength;
		}

		public void setIntrusionVideoLength(int intrusionVideoLength)
		{
			this.intrusionVideoLength = intrusionVideoLength;
		}

		public int getGoogleDriveVideosSizeLimit()
		{
			return this.googleDriveVideosSizeLimit;
		}

		public void setGoogleDriveVideosSizeLimit(int googleDriveVideosSizeLimit)
		{
			this.googleDriveVideosSizeLimit = googleDriveVideosSizeLimit;
		}

		public SleepingTime getSleepingTime()
		{
			return this.sleepingTime;
		}

		public void setSleepingTime(SleepingTime sleepingTime)
		{
			this.sleepingTime = sleepingTime;
		}

		public String getJsonText()
		{
			String result;

			result = "{\n\"sleeping_time\":[";
			result += this.sleepingTime.getStartingHour() + ",";
			result += this.sleepingTime.getStartingMinute() + ",";
			result += this.sleepingTime.getEndingHour() + ",";
			result += this.sleepingTime.getEndingMinute() + "],\n";

			result += "\"temperature_for_smart_control\":" + this.temperatureForSmartControl + ",\n";
			result += "\"notification_email\":\"" + this.notificationEmail + "\",\n";
			result += "\"send_email_notifications\":" + this.sendEmailNotifications + ",\n";
			result += "\"email_frequency\":" + this.emailFrequency.ordinal() + ",\n";
			result += "\"intrusion_video_length\":" + this.intrusionVideoLength + ",\n";
			result += "\"google_drive_videos_size_limit\":" + this.googleDriveVideosSizeLimit + ",\n}";

			return result;
		}
	}

	public class SleepingTime
	{
		private int startingHour;
		private int startingMinute;
		private int endingHour;
		private int endingMinute;

		public SleepingTime(int startingHour, int startingMinute, int endingHour, int endingMinute)
		{
			this.startingHour = startingHour;
			this.startingMinute = startingMinute;
			this.endingHour = endingHour;
			this.endingMinute = endingMinute;
		}

		public int getStartingHour()
		{
			return this.startingHour;
		}

		public void setStartingHour(int startingHour)
		{
			this.startingHour = startingHour;
		}

		public int getStartingMinute()
		{
			return this.startingMinute;
		}

		public void setStartingMinute(int startingMinute)
		{
			this.startingMinute = startingMinute;
		}

		public int getEndingHour()
		{
			return this.endingHour;
		}

		public void setEndingHour(int endingHour)
		{
			this.endingHour = endingHour;
		}

		public int getEndingMinute()
		{
			return this.endingMinute;
		}

		public void setEndingMinute(int endingMinute)
		{
			this.endingMinute = endingMinute;
		}
	}
}