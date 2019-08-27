package com.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface ModelService {
	
	public abstract List<String> getCategory(HashMap<String, Object> map);
	
	public abstract Set<String> getKeyset(HashMap<String, Object> map);
	
}
