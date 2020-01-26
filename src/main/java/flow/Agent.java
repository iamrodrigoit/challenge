package flow;

import java.util.LinkedList;
import java.util.List;

import flow.AgentException;
import flow.IAction;
import flow.IAgent;

public class Agent implements IAgent {
	private String[] values;

	public Agent(String... values) {
		this.values = values;
	}

	public List<IAction> act() throws AgentException {
		List<IAction> flow = new LinkedList<IAction>();
		for (String value : values) {
			flow.add(new Action(value));
	    }
	    return flow;
	}

}
