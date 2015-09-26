package fr.lteconsulting.activity.utils;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.activity.IActivity;
import fr.lteconsulting.activity.IActivityCallback;
import fr.lteconsulting.activity.IActivityContext;
import fr.lteconsulting.activity.IActivityDisplay;
import fr.lteconsulting.activity.controller.IActivityController;
import fr.lteconsulting.activity.controller.IActivityControllerDisplay;

public class ActivityController implements IActivityController
{
	private final List<ActivityContext> activities = new ArrayList<>();

	private final IActivityControllerDisplay display;

	public ActivityController( IActivityControllerDisplay display )
	{
		this.display = display;
	}
	
	protected IActivityControllerDisplay getDisplay()
	{
		return display;
	}

	@Override
	public void start( IActivity activity, Object parameter, IActivityCallback callback )
	{
		ActivityContext info = new ActivityContext( activity, parameter, callback );
		activities.add( info );

		info.startPanel();
	}

	private class ActivityContext implements IActivityContext
	{
		final IActivity activity;
		final Object parameter;
		final IActivityCallback callback;

		IActivityDisplay display;
		
		ActivityContext( IActivity activiy, Object parameter, IActivityCallback callback )
		{
			this.activity = activiy;
			this.parameter = parameter;
			this.callback = callback;
		}
		
		void ensureDisplay()
		{
			if( display == null )
				display = ActivityController.this.display.create();
		}

		void startPanel()
		{
			activity.start( this );
		}

		@Override
		public IActivityDisplay getDisplay()
		{
			ensureDisplay();
			return display;
		}
		
		@Override
		public Object getParameter()
		{
			return parameter;
		}

		@Override
		public void exit()
		{
			if( display != null )
			{
				display.setView( null );
				display = null;
			}
			
			activities.remove( this );

			if( callback != null )
				callback.onCancel();
		}

		@Override
		public void exit( Throwable throwable )
		{
			if( display != null )
			{
				display.setView( null );
				display = null;
			}
			
			activities.remove( this );

			if( callback != null )
				callback.onError( throwable );
		}

		@Override
		public void exit( Object result )
		{
			if( display != null )
			{
				display.setView( null );
				display = null;
			}
			
			activities.remove( this );

			if( callback != null )
				callback.onResult( result );
		}
		
		@Override
		public void start( IActivity activity, Object parameter, IActivityCallback callback )
		{
			ActivityController.this.start( activity, parameter, callback );
		}
	}
}
