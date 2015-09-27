package fr.lteconsulting.activity.demo.activity;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * An activity that allows to choose an enum value
 */
public class MenuActivity<T extends Enum<?>> extends PreviousNextActivity<T, T>
{
	final Class<T> cls;

	final VerticalPanel panel;

	final Map<Enum<?>, RadioButton> radios = new HashMap<>();

	public MenuActivity( Class<T> cls )
	{
		this.cls = cls;

		panel = new VerticalPanel();
		for( T i : cls.getEnumConstants() )
		{
			RadioButton radio = new RadioButton( "activity_" + System.identityHashCode( this ), i.name() );
			panel.add( radio );
			radios.put( i, radio );
		}
	}

	@Override
	protected void onStart()
	{
		if( getContext().parameter() != null )
			radios.get( getContext().parameter() ).setValue( true );

		setView( panel );
	}

	@Override
	protected void onNext()
	{
		for( T i : cls.getEnumConstants() )
		{
			if( radios.get( i ).getValue() )
			{
				getContext().exit( i );
				return;
			}
		}
	}

	@Override
	protected void onPrevious()
	{
		getContext().exit();
	}
}
