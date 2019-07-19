package service;

import javax.inject.Inject;

import persistence.repo.ListTodoRepo;

public class ListTodoService {

	@Inject
	private ListTodoRepo repo;

	public String CreateList(Long userId,String listsTodo) {

		return this.repo.CreateList(userId,listsTodo);
	}

	public String getAllLists() {
		return this.repo.getAllLists();
	}

	public String updateList(long listId, String listsTodo) {

		return this.repo.updateList(listId, listsTodo);

	}

	public String deleteList(long listId) {
		return this.repo.deleteList(listId);
	}

}
