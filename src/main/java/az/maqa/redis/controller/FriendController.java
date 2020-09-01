package az.maqa.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import az.maqa.redis.dao.FriendDao;
import az.maqa.redis.model.Friend;

@RestController
public class FriendController {

	@Autowired
	private FriendDao friendDao;
	
	@GetMapping(value = "addFriend")
	public void addFriend() {
		Friend friend = new Friend(1, "Samir", 18);
		friendDao.addFriend(friend);

		Friend friend2 = new Friend(2, "Qasim", 23);
		friendDao.addFriend(friend2);

		System.out.println("Number of Friends:" + friendDao.getNumberOfFriends());

		Friend friendByIndex = friendDao.getFriendByIndex(1);
		System.out.println(friendByIndex.getName());

		friendDao.deleteFriends(friend2);

		Friend friendByIndex2 = friendDao.getFriendByIndex(2);
		System.out.println(friendByIndex2.getName());
	}
	
}
