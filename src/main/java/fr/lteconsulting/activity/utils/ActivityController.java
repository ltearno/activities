package fr.lteconsulting.activity.utils;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.activity.IActivity;
import fr.lteconsulting.activity.IActivityCallback;
import fr.lteconsulting.activity.IActivityClosingProcess;
import fr.lteconsulting.activity.IActivityContext;
import fr.lteconsulting.activity.IActivityDisplay;
import fr.lteconsulting.activity.IActivityHandle;
import fr.lteconsulting.activity.controller.IActivityController;
import fr.lteconsulting.activity.controller.IActivityControllerDisplay;

public class ActivityController implements IActivityController
{
	private final List<ActivityContext<?, ?>> activities = new ArrayList<>();

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
	public <P, R> IActivityHandle<P, R> start( IActivity<P, R> activity, P parameter, IActivityCallback<R> callback )
	{
		ActivityContext<P, R> info = new ActivityContext<>( activity, parameter, callback );
		activities.add( info );

		info.startPanel();
		
		return info;
	}
	
	@Override
	public void closeAll()
	{
		if( closingProcess != null )
			closingProcess.abort();
		
		closingProcess = new ClosingProcess();
		
		closingProcess.go();
	}
	
	private ClosingProcess closingProcess;

	private class ClosingProcess implements IActivityClosingProcess
	{
		void go()
		{
			if( activities.isEmpty() )
			{
				abort();
				return;
			}
			
			activities.get( activities.size() - 1 ).activity.close( this );
		}
		
		@Override
		public void abort()
		{
			closingProcess = null;
		}
	}
	
	private class ActivityContext<P, R> implements IActivityContext<P, R>, IActivityHandle<P, R>
	{
		final IActivity<P, R> activity;
		final P parameter;
		final IActivityCallback<R> callback;

		IActivityDisplay display;
		
		ActivityContext( IActivity<P, R> activiy, P parameter, IActivityCallback<R> callback )
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
		public IActivityDisplay display()
		{
			ensureDisplay();
			return display;
		}
		
		@Override
		public P parameter()
		{
			return parameter;
		}

		@Override
		public void exit()
		{
			if( display != null )
			{
				display.show( null );
				display = null;
			}
			
			activities.remove( this );

			if( callback != null )
				callback.onCancel();
			
			if( closingProcess != null )
				closingProcess.go();
		}

		@Override
		public void exit( Throwable throwable )
		{
			if( display != null )
			{
				display.show( null );
				display = null;
			}
			
			activities.remove( this );

			if( callback != null )
				callback.onError( throwable );
			
			if( closingProcess != null )
				closingProcess.go();
		}

		@Override
		public void exit( R result )
		{
			if( display != null )
			{
				display.show( null );
				display = null;
			}
			
			activities.remove( this );

			if( callback != null )
				callback.onResult( result );
			
			if( closingProcess != null )
				closingProcess.go();
		}
		
		@Override
		public <PP, RR> IActivityHandle<PP,RR> start( IActivity<PP, RR> activity, PP parameter, IActivityCallback<RR> callback )
		{
			return ActivityController.this.start( activity, parameter, callback );
		}
	}
}
