package flow;

import flow.ActionException;
import flow.IAction;

public class Action implements IAction<String> {
	
	private String value;
	
	public Action(String value) {
		this.value = value;
	}
	
	public String execute() throws ActionException {
		return value;
	}
	
	public String getType() {
		return "TEXT";
	}

}
