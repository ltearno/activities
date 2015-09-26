package fr.lteconsulting.activity.utils;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.activity.ActivityDisplayMode;
import fr.lteconsulting.activity.IActivityDisplay;
import fr.lteconsulting.activity.controller.IActivityControllerDisplay;

public class ActivityLayoutPanel implements IActivityControllerDisplay
{
	private final LayoutPanel panel = new LayoutPanel();

	@Override
	public Widget asWidget()
	{
		return panel;
	}

	@Override
	public IActivityDisplay create()
	{
		return new ActivityDisplay();
	}

	private class ActivityDisplay implements IActivityDisplay
	{
		IsWidget view;
		ActivityDisplayMode mode;
		
		public ActivityDisplay()
		{
			setDisplayMode( ActivityDisplayMode.FULL );
		}

		@Override
		public void setDisplayMode( ActivityDisplayMode mode )
		{
			this.mode = mode;
			
			updateViewMode();
		}

		@Override
		public void setView( IsWidget view )
		{
			if( this.view == view )
				return;
			
			if( this.view != null )
				panel.remove( this.view );
			
			this.view = view;

			if( view == null )
				return;

			panel.add( view );
			
			updateViewMode();
		}

		private void updateViewMode()
		{
			if( view == null )
				return;

			switch( mode )
			{
				case FULL:
					panel.setWidgetTopBottom( view, 0, Unit.PX, 0, Unit.PX );
					panel.setWidgetLeftRight( view, 0, Unit.PX, 0, Unit.PX );
					break;
				case DIALOG:
					panel.setWidgetTopBottom( view, 25, Unit.PCT, 25, Unit.PCT );
					panel.setWidgetLeftRight( view, 25, Unit.PCT, 25, Unit.PCT );
					break;
			}
		}
	}
}
