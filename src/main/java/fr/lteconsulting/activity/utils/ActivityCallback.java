package fr.lteconsulting.activity.utils;

import fr.lteconsulting.activity.IActivityCallback;

/**
 * Base class for implementing an activity callback. This
 * remove the needs for boiler plate code as only needed
 * methods can be implemented (by overloading).
 * 
 * @param <R> Activity Result type
 */
public class ActivityCallback<R> implements IActivityCallback<R>
{
	@Override
	public void onCancel()
	{
	}

	@Override
	public void onResult( R result )
	{
	}

	@Override
	public void onError( Throwable throwable )
	{
	}
}
