package fr.lteconsulting.activity.demo;

import com.google.gwt.user.client.Window;

import fr.lteconsulting.activity.IActivityClosingProcess;
import fr.lteconsulting.activity.IActivityContext;

public class Utils
{
	public static void standardConfirm( IActivityContext<?, ?> context, IActivityClosingProcess closingProcess )
	{
		if( Window.confirm( "Do you really want to cancel ?" ) )
			context.exit();
		else
			closingProcess.abort();
	}
}
