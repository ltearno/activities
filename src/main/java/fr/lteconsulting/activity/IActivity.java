package fr.lteconsulting.activity;

/**
 * Activities are accessed by the controller through this interface
 * 
 * @param P
 *            Activity Parameter type
 * @param R
 *            Activity Result type
 */
public interface IActivity<P, R>
{
	/**
	 * The implementation should start its activity.
	 * 
	 * <p>
	 * This means usually those steps :
	 * 
	 * <ul>
	 * <li>creating a view and showing it with a call to
	 * `context.getDisplay().setView( widget )`.
	 * <li>taking few data loading actions
	 * <li>enable reacting to user's interactions
	 * </ul>
	 * 
	 * @param context
	 *            Activity's access to its external world
	 */
	void start( IActivityContext<P, R> context );

	/**
	 * The implementation is asked to close itself.
	 * 
	 * <p>
	 * The activity can either accept or not. In either case it is preferable to
	 * call one of those methods :
	 * 
	 * <ul>
	 * <li><code>closingProcess.abort()</code> to abort the closing process.
	 * <li><code>context.exit( ... )</code> (one of the three overloaded
	 * methods) to exit the activity and continue the
	 * {@link IActivityClosingProcess}.
	 * </ul>
	 * 
	 * <p>
	 * <i>Note that if the implementation does not call any of those methods,
	 * the closing process may be reactivated later when the activity really
	 * closes.</i>
	 * 
	 * @param closingProcess
	 *            The closing process that is happening, causing the call to
	 *            this method
	 */
	void close( IActivityClosingProcess closingProcess );
}
