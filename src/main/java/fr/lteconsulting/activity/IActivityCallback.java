package fr.lteconsulting.activity;

/**
 * Execution callback from an Activity
 */
public interface IActivityCallback<R>
{
	/**
	 * Activity has been cancelled
	 */
	void onCancel();

	/**
	 * Activity was successful and gives a value back
	 * 
	 * @param result The value resulting of the activity
	 */
	void onResult( R result );

	/**
	 * Activity stopped because of an uncontroller error
	 * 
	 * @param throwable The {@link Throwable} which was the cause of the error
	 */
	void onError( Throwable throwable );
}
