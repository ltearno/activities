package fr.lteconsulting.activity.demo;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.lteconsulting.activity.IActivityClosingProcess;
import fr.lteconsulting.activity.utils.ActivityCallback;
import fr.lteconsulting.activity.utils.Activity;
import fr.lteconsulting.hexa.client.ui.UiBuilder;

public class ListActivity extends Activity<List<Item>, List<Item>, ListActivity.View>
{
	protected class View extends Composite
	{
		HTML list = new HTML();
		Button plus = new Button( "+" );

		View()
		{
			initWidget( UiBuilder.vert( new Label( "Item list" ), new ScrollPanel( list ), plus ) );
			getElement().getStyle().setBackgroundColor( "white" );
		}
	}

	public ListActivity()
	{
		setView( new View() );

		view().plus.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				Item item = new Item( "title", "content" );
				Application.ITEMS.add( item );

				context().start( new EditActivity(), item, new ActivityCallback<Item>()
				{
					@Override
					public void onResult( Item result )
					{
						reload();
					}
				} );
			}
		} );
	}

	@Override
	public void onStart()
	{
		reload();
	}

	@Override
	public void onClose( IActivityClosingProcess closingProcess )
	{
		context().exit();
	}

	void reload()
	{
		StringBuilder sb = new StringBuilder();

		for( Item item : context().parameter() )
			sb.append( T.item( item.getTitle(), item.getContent() ).asString() );

		view().list.setHTML( sb.toString() );
	}

	private static final Templates T = GWT.create( Templates.class );

	interface Templates extends SafeHtmlTemplates
	{
		@Template( "<div><h1>{0}</h1><span>{1}</span></div>" )
		SafeHtml item( String title, String content );
	}
}
