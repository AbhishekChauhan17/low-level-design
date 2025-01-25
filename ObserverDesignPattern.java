//4. Event Management System
//Problem:
//
//You are building a system for an event management company. You have a list of attendees, and you want to send out updates to all attendees whenever there's a change in event details (e.g., time change, venue change, speaker change).
//Tasks:
//
//Implement an Event class (subject) that can notify all attendees (observers) whenever the event details are updated.
//Each attendee (observer) should receive a notification with the updated event details.

import java.util.ArrayList;

// EventDetails Interface
interface EventDetailsInterface {
	String getLocation();
	String getDatetime();
}

// Concrete class for Party Event
class PartyEventDetails implements EventDetailsInterface {
	private String location;
	private String datetime;

	public PartyEventDetails(String location, String datetime) {
		this.location = location;
		this.datetime = datetime;
	}

	@Override
	public String getLocation() { return location; }

	@Override
	public String getDatetime() { return datetime; }
}

// Concrete class for Conference Event
class ConferenceEventDetails implements EventDetailsInterface {
	private String location;
	private String datetime;
	private String speaker;

	public ConferenceEventDetails(String location, String datetime, String speaker) {
		this.location = location;
		this.datetime = datetime;
		this.speaker = speaker;
	}

	@Override
	public String getLocation() { return location; }

	@Override
	public String getDatetime() { return datetime; }

	public String getSpeaker() { return speaker; }
}

// Event Interface
interface EventInterface {
	void addPeople(AttendeeInterface attendee);
	void removePeople(AttendeeInterface attendee);
	void updateEventDetails(EventDetailsInterface eventDetails);
	void notifyPeople();
	String getLocation();
	String getDatetime();
}

// Event class implementing EventInterface
class Event implements EventInterface {
	private EventDetailsInterface eventDetails;
	private ArrayList<AttendeeInterface> people = new ArrayList<>();

	public Event(EventDetailsInterface eventDetails) {
		this.eventDetails = eventDetails;
	}

	public void addPeople(AttendeeInterface attendee) {
		this.people.add(attendee);
	}

	public void removePeople(AttendeeInterface attendee) {
		this.people.remove(attendee);
	}

	// Update event details and notify all attendees
	public void updateEventDetails(EventDetailsInterface eventDetails) {
		this.eventDetails = eventDetails;
		this.notifyPeople();
	}

	public void notifyPeople() {
		for (AttendeeInterface attendee : people) {
			attendee.update();
		}
	}

	@Override
	public String getLocation() {
		return eventDetails.getLocation();
	}

	@Override
	public String getDatetime() {
		return eventDetails.getDatetime();
	}
}

// Attendee Interface
interface AttendeeInterface {
	void update();
}

// Concrete class for Attendee
class Attendee implements AttendeeInterface {
	EventInterface event;

	public Attendee(EventInterface event) {
		this.event = event;
	}

	public void update() {
		System.out.println("Event location and datetime updated to: " + this.event.getLocation() + " and " + this.event.getDatetime());
	}
}

public class ObserverDesignPattern {
	public static void main(String[] args) {
		// Create a PartyEventDetails object
		EventDetailsInterface partyDetails = new PartyEventDetails("Tokyo", "2025-12-01");
		EventInterface partyEvent = new Event(partyDetails);

		// Create attendees
		AttendeeInterface firstPerson = new Attendee(partyEvent);
		AttendeeInterface secondPerson = new Attendee(partyEvent);
		AttendeeInterface thirdPerson = new Attendee(partyEvent);

		// Add people to the event
		partyEvent.addPeople(firstPerson);
		partyEvent.addPeople(secondPerson);
		partyEvent.addPeople(thirdPerson);

		// Update event details and notify attendees
		partyEvent.updateEventDetails(new PartyEventDetails("Hokkaido", "2025-12-31"));

		// Remove one person and update again
		partyEvent.removePeople(secondPerson);
		partyEvent.updateEventDetails(new PartyEventDetails("Kyoto", "2026-01-15"));
	}
}
