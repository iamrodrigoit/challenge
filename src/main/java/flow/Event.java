package flow;

import flow.ActionException;
import flow.EventException;
import flow.IEvent;

public class Event implements IEvent<String> {
	private String value;

	public Event(String value) {
		this.value = value;
	}

	public String trigger() throws EventException, ActionException {
		return "Request:" + value;
	}

}
