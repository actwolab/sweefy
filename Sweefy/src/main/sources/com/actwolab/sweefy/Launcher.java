package com.actwolab.sweefy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * 起動クラス
 *
 * @author actwo
 *
 */
@WebListener
public class Launcher implements ServletContextListener
{
	/**
	 * バージョン文字列
	 */
	private static String version = "0.9.0";

	/**
	 * ホームフォルダ
	 */
	private static String homeDir;

	/**
	 * ロギングオブジェクト
	 */
	private static Logger logger;

	/**
	 * 初期化処理を行います.
	 */
	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		try
		{
			// ホームフォルダのパスを取得します.
			homeDir = System.getenv("SWEEFY_HOME");

			// ログオブジェクトを設定します.
			Logger.getRootLogger().setLevel(Level.INFO);
			Logger.getRootLogger().addAppender(new ConsoleAppender(new PatternLayout("[%p] %d{yyyy/MM/dd HH:mm:ss,SSS} %m%n")));

			// 起動クラスのロガーを設定します.
			logger = Logger.getLogger(Launcher.class);

			// 起動ログを書き込みます.
			logger.info("Sweefy Version:" + version);
			logger.info("Sweefy Home:" + homeDir);
		}
		catch (Exception e)
		{
			logger.fatal("Sweefy could not startup webapps", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 終了処理を行います.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
	}
}
