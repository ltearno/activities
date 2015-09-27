package fr.lteconsulting.activity.utils;

import com.google.gwt.user.client.ui.IsWidget;

import fr.lteconsulting.activity.IActivity;
import fr.lteconsulting.activity.IActivityClosingProcess;
import fr.lteconsulting.activity.IActivityContext;

/**
 * Base class for impementing an {@link IActivity}
 * 
 * <p>
 * It does the basic things like :
 * <ul>
 * <li>saving the context when the activity starts,
 * <li>displays the view in the activityy's display container when the activity
 * starts,
 * <li>calls the <code>onStart</code> and <code>onClose</code> abstract methods.
 * </ul>
 * 
 * @param <P>
 *            Activity Parameter type
 * @param <R>
 *            Activity Result type
 * @param <V>
 *            Activity's view type. It should implements or extends
 *            {@link IsWidget}
 */
public abstract class Activity<P, R, V extends IsWidget> implements IActivity<P, R>
{
	/**
	 * Called when the activity is started.
	 */
	protected abstract void onStart();

	/**
	 * Called when the activity is required to close.
	 * 
	 * <p>
	 * See {@link IActivity} for details.
	 * 
	 * @param closingProcess
	 *            The associated closing process.
	 */
	protected abstract void onClose( IActivityClosingProcess closingProcess );

	private IActivityContext<P, R> context;

	private V view;

	/**
	 * Sets the view instance. This is the instance that will be put in the
	 * display when the activity will be started.
	 * 
	 * @param view
	 */
	protected void setView( V view )
	{
		this.view = view;
	}

	/**
	 * Obtains the activity's context.
	 * 
	 * <p>
	 * If the activity is not started, the context should be <code>null</code>.
	 */
	protected IActivityContext<P, R> context()
	{
		return context;
	}

	/**
	 * Obtains the activity's view, as set by the method 'setView( view )'
	 */
	protected V view()
	{
		return view;
	}

	@Override
	public final void start( IActivityContext<P, R> context )
	{
		this.context = context;

		onStart();

		if( view == null )
			throw new IllegalStateException( "View should be created when start() method is called !" );

		context.display().show( view );
	}

	@Override
	public final void close( IActivityClosingProcess closingProcess )
	{
		onClose( closingProcess );
	}
}
