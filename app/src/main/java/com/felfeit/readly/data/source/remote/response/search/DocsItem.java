package com.felfeit.readly.data.source.remote.response.search;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DocsItem{

	@SerializedName("author_name")
	private List<String> authorName;

	@SerializedName("edition_count")
	private int editionCount;

	@SerializedName("first_publish_year")
	private int firstPublishYear;

	@SerializedName("cover_edition_key")
	private String coverEditionKey;

	@SerializedName("title")
	private String title;

	@SerializedName("key")
	private String key;

	public List<String> getAuthorName(){
		return authorName;
	}

	public int getEditionCount(){
		return editionCount;
	}

	public int getFirstPublishYear(){
		return firstPublishYear;
	}

	public String getCoverEditionKey(){
		return "https://covers.openlibrary.org/b/olid/" + coverEditionKey + "-M.jpg";
	}

	public String getTitle(){
		return title;
	}

	public String getKey() {
		return key;
	}

	public boolean hasCover() {
		return coverEditionKey != null && !coverEditionKey.isEmpty();
	}
}