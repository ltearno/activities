package fr.lteconsulting.activity.utils;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * A basic activity controller with a default display.
 */
public class SimpleActivityController extends ActivityController implements IsWidget
{
	public SimpleActivityController()
	{
		super( new ActivityLayoutPanel() );
	}

	@Override
	public Widget asWidget()
	{
		return getDisplay().asWidget();
	}
}
