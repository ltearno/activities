package fr.lteconsulting.activity;

/**
 * Activity's view of its external world.
 * 
 * <p>
 * This interface of which an instance is given at the starting time of an
 * activity, is used to manipulate the activity's display container and to exit.
 * 
 * @param P
 *            Activity Parameter type
 * @param R
 *            Activity Result type
 */
public interface IActivityContext<P, R> extends IActivityStarter
{
	/**
	 * Obtains the activity's display container.
	 */
	IActivityDisplay display();

	/**
	 * Obtains the parameter that was given to this activity
	 */
	P parameter();

	/**
	 * Exits the activity with a return value.
	 * 
	 * <p>
	 * The activity is removed from its controller, and the activity's execution
	 * callback which was set by the activity caller is called.
	 */
	void exit( R result );

	/**
	 * Exits the activity by cancelling it.
	 * 
	 * <p>
	 * The activity is removed from its controller, and the activity's execution
	 * callback which was set by the activity caller is called.
	 */
	void exit();

	/**
	 * Exits the activity because of an error.
	 * 
	 * <p>
	 * The activity is removed from its controller, and the activity's execution
	 * callback which was set by the activity caller is called.
	 */
	void exit( Throwable throwable );
}
