package fr.lteconsulting.activity.demo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import fr.lteconsulting.activity.IActivityClosingProcess;
import fr.lteconsulting.activity.utils.Activity;
import fr.lteconsulting.hexa.client.ui.UiBuilder;

public class WelcomeActivity extends Activity<Void, Void, WelcomeActivity.View>
{
	protected class View extends Composite
	{
		Button list = new Button( "My list");
		Button login = new Button( "Log in");
		
		View()
		{
			initWidget( UiBuilder.vert( new Label("Welcome !"), list, login ) );
			getElement().getStyle().setBackgroundColor( "white" );
		}
	}
	
	public WelcomeActivity()
	{
		setView( new View() );
		
		view().list.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				context().start( new ListActivity(), Application.ITEMS, null );
			}
		} );
		
		view().login.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
			}
		} );
	}

	@Override
	protected void onStart()
	{
	}
	
	@Override
	public void onClose( IActivityClosingProcess closingProcess )
	{
		context().exit();
	}
}
