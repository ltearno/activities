package fr.lteconsulting.activity.demo;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.lteconsulting.activity.IActivity;
import fr.lteconsulting.activity.IActivityClosingProcess;
import fr.lteconsulting.activity.IActivityContext;

/**
 * A base class for creating a previous next activity
 */
public abstract class PreviousNextActivity implements IActivity
{
	public enum ReturnValue
	{
		PREVIOUS,
		NEXT;
	}

	/**
	 * Starting of the activity.
	 */
	protected abstract void onStart();

	protected abstract void onNext();

	protected abstract void onPrevious();

	private IActivityContext context;

	private final View view;

	public PreviousNextActivity()
	{
		view = new View();
		
		view.previous.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				onPrevious();
			}
		} );

		view.next.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				onNext();
			}
		} );
	}

	@Override
	public void start( IActivityContext context )
	{
		this.context = context;

		context.getDisplay().setView( view );

		onStart();
	}

	@Override
	public void close( IActivityClosingProcess closingProcess )
	{
		context.exit();
	}

	protected void setView( IsWidget view )
	{
		this.view.setContent( view );
	}

	protected void previous()
	{
		context.exit( ReturnValue.PREVIOUS );
	}

	protected void next()
	{
		context.exit( ReturnValue.NEXT );
	}
	
	protected IActivityContext getContext()
	{
		return context;
	}

	private class View extends Composite
	{
		SimplePanel content = new SimplePanel();
		Button previous = new Button( "PREVIOUS" );
		Button next = new Button( "NEXT" );

		View()
		{
			DockLayoutPanel panel = new DockLayoutPanel( Unit.PCT );

			panel.addNorth( content, 90 );
			panel.addWest( previous, 50 );
			panel.add( next );

			initWidget( panel );

			getElement().getStyle().setBackgroundColor( "grey" );
		}

		void setContent( IsWidget content )
		{
			this.content.setWidget( content );
		}
	}
}
