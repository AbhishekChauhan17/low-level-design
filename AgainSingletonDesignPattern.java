// same question as the singleton design pattern file

import java.text.SimpleDateFormat;
import java.util.Date;

enum LogType {
    INFO,
    DEBUG,
    ERROR
}

class Logger {
    private static volatile Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(LogType logType, String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("[" + logType + "] " + timestamp + " - " + message);
    }
}

public class AgainSingletonDesignPattern {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log(LogType.INFO, "Application started.");
        logger.log(LogType.DEBUG, "Loading user data.");
        logger.log(LogType.ERROR, "Failed to connect to database.");
    }
}
