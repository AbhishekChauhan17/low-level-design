// Problem: Notification System

// You are tasked with implementing a notification system using the Null Object Design Pattern. The system should handle different types of notifications (e.g., Email, SMS, and Push Notifications). In case a user opts out of a particular notification type, the system should use a null object to gracefully handle the absence of that notification type instead of introducing null checks.
// Requirements

//     Notification Interface:
//         Define a Notification interface with a method send(String message).

//     Concrete Notification Types:
//         EmailNotification: Sends an email notification.
//         SMSNotification: Sends an SMS notification.
//         PushNotification: Sends a push notification.

//     Null Object:
//         Implement a NullNotification class that implements the Notification interface but does nothing when the send method is called.

//     Notification Service:
//         A class that manages notification objects and sends messages through them.

//     Main:
//         Create a scenario where some users have opted out of specific notification types, and the NullNotification object ensures the system works seamlessly without null checks.

interface NotificationService {
  public void sendNotification(String msg);
}

class EmailNotification implements NotificationService {
  public void sendNotification(String msg) {
    System.out.println("The msg: " + msg + " will be sent through the email notification service");
  }
}

class SMSNotification implements NotificationService {
  public void sendNotification(String msg) {
    System.out.println("The msg: " + msg + " will be sent through the SMS notification service");
  }
}

class PushNotification implements NotificationService {
  public void sendNotification(String msg) {
    System.out.println("The msg: " + msg + " will be sent through the push notification service");
  }
}

class NullNotification implements NotificationService {
  public void sendNotification(String msg) {
    System.out.println("The msg: " + msg + " cannot be sent through any notification service. So will call on your registered number instead");
  }
}

class NotificationServiceFactory {
  public NotificationService getNotificationService(String notificationServiceType) {
    return switch(notificationServiceType) {
      case "Email" -> new EmailNotification();
      case "SMS" -> new SMSNotification();
      case "Push" -> new PushNotification();
      default -> new NullNotification();
    };
  }
}

public class NullObjectDesignPattern {
  public static void main(String []args) {
    NotificationServiceFactory notificationServiceFactory = new NotificationServiceFactory();
    NotificationService notificationService;
    notificationService = notificationServiceFactory.getNotificationService("Email");
    notificationService.sendNotification("this is a sample message");
    notificationService = notificationServiceFactory.getNotificationService("SMS");
    notificationService.sendNotification("this is a sample message");
    notificationService = notificationServiceFactory.getNotificationService("Push");
    notificationService.sendNotification("this is a sample message");
    notificationService = notificationServiceFactory.getNotificationService("random");
    notificationService.sendNotification("this is a sample message");
  }
}