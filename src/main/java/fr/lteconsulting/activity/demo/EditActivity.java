package fr.lteconsulting.activity.demo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.activity.IActivityClosingProcess;
import fr.lteconsulting.activity.utils.Activity;
import fr.lteconsulting.hexa.client.ui.UiBuilder;

public class EditActivity extends Activity<Item, Item, EditActivity.View>
{
	protected class View extends Composite
	{
		TextBox title = new TextBox();
		TextArea content = new TextArea();
		Button cancel = new Button( "Cancel" );
		Button validate = new Button( "Validate" );

		View()
		{
			initWidget( UiBuilder.vert( new Label( "Edit item" ), title, content, cancel, validate ) );
			getElement().getStyle().setBackgroundColor( "white" );
		}
	}
	
	public EditActivity()
	{
		setView( new View());
		
		view().cancel.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				context().exit();
			}
		} );

		view().validate.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				Item item = context().parameter();

				item.setTitle( view().title.getValue() );
				item.setContent( view().content.getValue() );

				context().exit( item );
			}
		} );
	}

	@Override
	public void onStart()
	{
		Item item = context().parameter();
		view().title.setValue( item.getTitle() );
		view().content.setValue( item.getContent() );
	}

	@Override
	public void onClose( IActivityClosingProcess closingProcess )
	{
		Utils.standardConfirm( context(), closingProcess );
	}
}