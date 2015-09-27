package fr.lteconsulting.activity;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Activity's display container.
 * 
 * <p>
 * This is useful for an activity to change how it is displayed and laid-out
 * inside its controller's display
 */
public interface IActivityDisplay
{
	/**
	 * Display's a new view in the activity's controller display.
	 * 
	 * <p>To remove the view from the controller's display,
	 * simply call <code>setView( null )</code>
	 * 
	 * <p>There can only be one view by activity, so if any
	 * view was already displayed, it is removed.
	 * @param view
	 */
	void show( IsWidget view );

	/**
	 * Sets the activity's view display mode.
	 * 
	 * @param mode The desired display mode
	 */
	void setDisplayMode( ActivityDisplayMode mode );
}
