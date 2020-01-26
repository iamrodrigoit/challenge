package flow;

import java.util.LinkedList;

import flow.ActionException;
import flow.AppException;
import flow.EventException;
import flow.IApp;
import flow.IEvent;
import flow.ProtocolException;

public class App implements IApp<String> {
	private LinkedList<String> responses;

	public App() {
	    responses = new LinkedList<String>();
	  }

	public String in(IEvent event) throws AppException, EventException, ProtocolException, ActionException {
		String request = (String) event.trigger();
		String response = makeResponse(request);
		responses.add(response);
		return response;
	}

	private String makeResponse(String request) {
		String value = request.replace("Request:", "");
		return "ACK:" + value;
	}

	public String popResponse() {
		return responses.remove();
	}

}
