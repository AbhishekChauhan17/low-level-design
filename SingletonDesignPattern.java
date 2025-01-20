// Question: Logging System

// You are tasked with implementing a Logging System using the Singleton Design Pattern. The system should ensure that there is only one instance of the logger throughout the application. The logger should provide the following features:
// Requirements

//     Single Instance:
//         The logger must follow the Singleton Pattern to ensure only one instance exists.

//     Log Levels:
//         Support at least three log levels:
//             INFO
//             WARNING
//             ERROR

enum LogType {
  INFO,
  DEBUG,
  ERROR
}

class Logger {
  public static volatile Logger logger;
  public Logger nextLogger;
  public static Logger getInstance() {
    if (logger == null) {
      synchronized(Logger.class){
        if (logger == null) {
          logger = new Logger();
        }
      }
    }
    return logger;
  }
  public void setNextLogger(Logger nextLogger) {
    this.nextLogger = nextLogger;
  }
  public void log(LogType logType, String msg) {
    if (this.nextLogger != null)
      this.nextLogger.log(logType, msg);
    else 
      System.out.println("No valid log level found");
  }
}

class InfoLog extends Logger {
  InfoLog(Logger nextLogger) {
    super.setNextLogger(nextLogger);
  }
  public void log(LogType logType, String msg) {
    if (logType == LogType.INFO) {
      System.out.println("[INFO] " + msg);
    }
    else {
      super.nextLogger.log(logType, msg);
    }
  }
}

class DebugLog extends Logger {
  DebugLog(Logger nextLogger) {
    super.setNextLogger(nextLogger);
  }
  public void log(LogType logType, String msg) {
    if (logType == LogType.DEBUG) {
      System.out.println("[DEBUG] " + msg);
    }
    else {
      super.nextLogger.log(logType, msg);
    }
  }
}

class ErrorLog extends Logger {
  ErrorLog(Logger nextLogger) {
    super.setNextLogger(nextLogger);
  }
  public void log(LogType logType, String msg) {
    if (logType == LogType.ERROR) {
      System.out.println("[ERROR] " + msg);
    }
    else {
      super.nextLogger.log(logType, msg);
    }
  }
}

public class SingletonDesignPattern {
  public static void main(String []args) {
    Logger logger = Logger.getInstance();
    logger.setNextLogger(new InfoLog(new DebugLog(new ErrorLog(null))));
    logger.log(LogType.DEBUG, "this is the msg sent from the server");
  }
}
