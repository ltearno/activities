package fr.lteconsulting.activity.demo;

import java.util.ArrayList;
import java.util.List;

public class Item
{
	public static class ItemListBuilder
	{
		List<Item> list = new ArrayList<>();
		
		public static ItemListBuilder add( String title, String content )
		{
			ItemListBuilder b = new ItemListBuilder();
			b.list.add( new Item( title, content ) );
			return b;
		}
		
		public ItemListBuilder and( String title, String content )
		{
			list.add( new Item( title, content ) );
			return this;
		}
		
		public List<Item> build()
		{
			return list;
		}
	}
	
	private String title;
	private String content;

	public Item( String title, String content )
	{
		this.title = title;
		this.content = content;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent( String content )
	{
		this.content = content;
	}
}
