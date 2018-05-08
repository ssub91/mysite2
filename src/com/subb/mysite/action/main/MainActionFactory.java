package com.subb.mysite.action.main;

import com.subb.web.Action;
import com.subb.web.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
