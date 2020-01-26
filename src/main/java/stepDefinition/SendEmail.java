package stepDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import flow.ActionException;
import flow.Adapter;
import flow.AdapterException;
import flow.AdapterNotFoundException;
import flow.Agent;
import flow.AgentException;
import flow.App;
import flow.AppException;
import flow.EmptyFlowException;
import flow.Engine;
import flow.EventException;
import flow.IAction;
import flow.IAdapter;
import flow.ProtocolException;

public class SendEmail {

	private Agent agent;
	private App app;
	private Engine engine;
	private Map<String, IAdapter> adapters = new HashMap<String, IAdapter>();

	@Given("^A User sends an \"([^\"]*)\" message$")
	public void a_User_sends_an_message(String act) throws AgentException, EmptyFlowException, AdapterNotFoundException, AppException, EventException, ProtocolException, ActionException, AdapterException {

		agent = new Agent(act);
		app = new App();

	    adapters.put("TEXT", new Adapter());
	    
		List<IAction> flow = agent.act();
		if (flow.isEmpty()) {
			throw new EmptyFlowException();
		}
		for (IAction action : flow) {
			String type = action.getType();
			if (!adapters.containsKey(type)) {
				throw new AdapterNotFoundException();
			}
			app.in(adapters.get(type).adapt(action));
		}
	}

	@When("^The message is converted by the Adapter$")
	public void the_message_is_converted_by_the_Adapter() throws AgentException, EmptyFlowException, AdapterNotFoundException, AdapterException, ActionException, EventException, ProtocolException, AppException {
		engine = new Engine(agent, adapters, app);
	    engine.run();
	}

	@Then("^The Message server should contain the \"([^\"]*)\" message in the queue$")
	public void the_Message_server_should_contain_the_message_in_the_queue(String act) {
	    App app = (App) engine.getApp();
	    Assert.assertEquals("ACK:"+act, app.popResponse());
	}

}
