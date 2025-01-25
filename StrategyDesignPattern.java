// 8. Notification System:
// Problem: You are building a notification system where the user can choose how they want to be notified (e.g., Email, SMS, Push Notifications).
// Task: Implement the Strategy Pattern to handle different notification methods.
// What You Need to Do:
// Define an interface NotificationStrategy with a method sendNotification(message).
// Implement concrete strategies like EmailNotification, SMSNotification, and PushNotification.
// Implement a NotificationContext class to allow dynamic selection of notification methods.

import java.util.HashMap;

class InvalidNotificationStrategyException extends Exception {
	public InvalidNotificationStrategyException(String msg) {
		super(msg);
	}
}

enum NotificationType {
	EMAIL, SMS, MOBILE
}

interface NotificationStrategy {
	public abstract void sendNotification(String msg);
}

class EmailNotification implements NotificationStrategy {
	public void sendNotification(String msg) {
		System.out.println("this notification is sent from email: " + msg);
	}
}

class SMSNotification implements NotificationStrategy {
	public void sendNotification(String msg) {
		System.out.println("this notification is sent from sms: " + msg);
	}
}

class MobileNotification implements NotificationStrategy {
	public void sendNotification(String msg) {
		System.out.println("this notification is sent from mobile: " + msg);
	}
}



class StrategyFactory {
	HashMap<NotificationType, NotificationStrategy> strategyMap = new HashMap<>();

	public NotificationStrategy getNotificationStyle(NotificationType notificationType) throws InvalidNotificationStrategyException {
		if (notificationType == null) throw new InvalidNotificationStrategyException("Invalid notification strategy selected.");
		switch(notificationType) {
			case EMAIL:
				if (!this.strategyMap.containsKey(NotificationType.EMAIL)) strategyMap.put(NotificationType.EMAIL, new EmailNotification());
				break;
			case SMS:
				if (!this.strategyMap.containsKey(NotificationType.SMS)) strategyMap.put(NotificationType.SMS, new SMSNotification());
				break;
			case MOBILE:
				if (!this.strategyMap.containsKey(NotificationType.MOBILE)) strategyMap.put(NotificationType.MOBILE, new MobileNotification());
				break;
			default:
				throw new InvalidNotificationStrategyException("Invalid notification strategy selected.");
		}
		return strategyMap.get(notificationType);
	}
}

class NotificationSystem {
	NotificationStrategy notificationStrategy;
	public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
		this.notificationStrategy = notificationStrategy;
	}
	public void sendNotify(String msg) {
		this.notificationStrategy.sendNotification(msg);
	}
}

public class StrategyDesignPattern {
	public static void main(String []args) {
		try {
			String msg = "this is the message that is sent in the notification";
			NotificationSystem notificationSystem = new NotificationSystem();
			StrategyFactory notificationStyleFactory = new StrategyFactory();
			notificationSystem.setNotificationStrategy(notificationStyleFactory.getNotificationStyle(NotificationType.EMAIL));
			notificationSystem.sendNotify(msg);
			notificationSystem.setNotificationStrategy(notificationStyleFactory.getNotificationStyle(NotificationType.SMS));
			notificationSystem.sendNotify(msg);
			notificationSystem.setNotificationStrategy(notificationStyleFactory.getNotificationStyle(NotificationType.MOBILE));
			notificationSystem.sendNotify(msg);
		}
		catch(InvalidNotificationStrategyException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}