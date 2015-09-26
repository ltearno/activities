package fr.lteconsulting.activity.controller;

import com.google.gwt.user.client.ui.IsWidget;

import fr.lteconsulting.activity.IActivityDisplay;

/**
 * Implementations of this interface are used by the {@link IActivityController}
 * to manage the displaying of the activities.
 */
public interface IActivityControllerDisplay extends IsWidget
{
	/**
	 * Create a slot for displaying an activity.
	 * 
	 * <p>The order of creation creation fixes of order of
	 * activities layouts.
	 * 
	 * @return The activity's display container
	 */
	IActivityDisplay create();
}
