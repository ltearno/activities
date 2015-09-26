package fr.lteconsulting.activity.demo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import fr.lteconsulting.activity.IActivity;
import fr.lteconsulting.activity.IActivityCallback;
import fr.lteconsulting.activity.IActivityClosingProcess;
import fr.lteconsulting.activity.IActivityContext;
import fr.lteconsulting.activity.demo.activity.MenuActivity;
import fr.lteconsulting.activity.demo.activity.PreviousNextActivity;
import fr.lteconsulting.activity.utils.SimpleActivityController;
import fr.lteconsulting.hexa.client.ui.UiBuilder;

/**
 * The GWT EntryPoint class to MVP application
 * 
 * @author Arnaud Tournier
 * 
 */
public class Application implements EntryPoint
{
	enum Response
	{
		Titi,
		Tata,
		Pourquoi,
		Non;
	}
	
	@Override
	public void onModuleLoad()
	{
		final SimpleActivityController ctrl = new SimpleActivityController();

		RootLayoutPanel.get().add( ctrl );
		
		ctrl.start( new MenuActivity<Response>( Response.class ), Response.Pourquoi, new IActivityCallback<Response>()
		{
			@Override
			public void onCancel()
			{
			}

			@Override
			public void onResult( Response result )
			{
				Window.alert( "Choose " + result.toString() );
			}

			@Override
			public void onError( Throwable throwable )
			{
			}
		} );
		
		//ctrl.start( new MyActivity(), "Salut !", null );
		
//		Timer t = new Timer()
//		{
//			@Override
//			public void run()
//			{
//				ctrl.closeAll();
//			}
//		};
//		t.schedule( 2000 );

//		ctrl.start( new TotoActivity(), new IActivityCallback()
//		{
//			@Override
//			public void onResult( Object result )
//			{
//				Window.alert( "Result ! : " + result );
//
//				ctrl.start( new MyActivity( ctrl ) );
//			}
//
//			@Override
//			public void onError( Throwable throwable )
//			{
//				Window.alert( "Error ! : " + throwable );
//			}
//
//			@Override
//			public void onCancel()
//			{
//				Window.alert( "Cancelled..." );
//			}
//		} );
	}
}

class TotoActivity implements IActivity<Void, Integer>
{
	IActivityContext<Void, Integer> context;

	Button cancel = new Button( "cancel" );
	Button validate = new Button( "validate" );
	Button exception = new Button( "exception" );

	@Override
	public void start( final IActivityContext<Void, Integer> context )
	{
		this.context = context;

		context.getDisplay().setView( UiBuilder.vert( new Label( "Welcome !" ), validate, exception, cancel ) );

		validate.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				context.exit( (Integer) 53 );
			}
		} );

		exception.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				context.exit( new IllegalStateException( "bad bad bad!" ) );
			}
		} );

		cancel.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				context.exit();
			}
		} );
	}

	@Override
	public void close( IActivityClosingProcess closingProcess )
	{
		boolean res = Window.confirm( "Really quit ?" );
		if( res )
			context.exit();
		else
			closingProcess.abort();
	}
}

class MyActivity extends PreviousNextActivity<String, Void>
{
	static int count = 0;
	
	MyActivity nextOne;
	
	Label view = new Label( "I am the activity " + count );

	public MyActivity()
	{
		count++;
	}

	@Override
	protected void onStart()
	{
		view.setText( "Hello " + getContext().getParameter() );
		setView( view );
	}

	@Override
	protected void onNext()
	{
		if( nextOne == null )
			nextOne = new MyActivity();

		getContext().start( nextOne, "Mister " + Math.random(), null );
	}

	@Override
	protected void onPrevious()
	{
		getContext().exit();
	}
}