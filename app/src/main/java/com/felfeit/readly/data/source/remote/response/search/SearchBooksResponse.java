package com.felfeit.readly.data.source.remote.response.search;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchBooksResponse{

	@SerializedName("q")
	private String q;

	@SerializedName("offset")
	private Object offset;

	@SerializedName("docs")
	private List<DocsItem> docs;

	@SerializedName("numFound")
	private int numFound;

	@SerializedName("start")
	private int start;

	@SerializedName("documentation_url")
	private String documentationUrl;

	@SerializedName("numFoundExact")
	private boolean numFoundExact;

	@SerializedName("num_found")
	private int num_Found;

	public String getQ(){
		return q;
	}

	public Object getOffset(){
		return offset;
	}

	public List<DocsItem> getDocs(){
		return docs;
	}

	public int getNumFound(){
		return numFound;
	}

	public int getStart(){
		return start;
	}

	public String getDocumentationUrl(){
		return documentationUrl;
	}

	public boolean isNumFoundExact(){
		return numFoundExact;
	}

	public int num_Found(){
		return num_Found;
	}
}