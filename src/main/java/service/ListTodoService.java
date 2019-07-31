package service;

import javax.inject.Inject;

import persistence.domain.ListsTodo;

import persistence.repo.ListTodoRepo;
import util.JSONUtil;

public class ListTodoService {

	@Inject
	private ListTodoRepo repo;
	
	@Inject
	private JSONUtil gson;

	public String CreateList(Long userId,String listsTodo) {
		ListsTodo aList = this.gson.getObjectForJSON(listsTodo, ListsTodo.class);
		if(aList.getListName().isEmpty()) {
			return "{\"Success\":\"False\"}";
		}

		return this.repo.CreateList(userId,listsTodo);
	}

	public String getAllLists(Long userId) {
		return this.repo.getAllLists(userId);
	}

	public String updateList(long listId, String listsTodo) {
		ListsTodo aList = this.gson.getObjectForJSON(listsTodo, ListsTodo.class);
		if(aList.getListName().isEmpty()) {
			return "{\"Success\":\"False\"}";
		}

		return this.repo.updateList(listId, listsTodo);

	}

	public String deleteList(long listId) {
		return this.repo.deleteList(listId);
	}

}
