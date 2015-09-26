package fr.lteconsulting.activity;

/**
 * Interface of a component knowing how to start new Activities.
 * 
 * <p>This is one of the roles of activity controllers...
 */
public interface IActivityStarter
{
	/**
	 * Starts an activity
	 * 
	 * @param activity The activity to be started.
	 * @param callback A callback to receive the activity's exit state. Can be <code>null</code>
	 */
	<P, R> void start( IActivity<P, R> activity, P parameter, IActivityCallback<R> callback );
}
