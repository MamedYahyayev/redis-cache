package az.maqa.redis.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import az.maqa.redis.model.Friend;

@Transactional
@Repository
public class FriendDao {

	private static final String KEY = "friends";

	@Autowired
	private RedisTemplate<String, Friend> redisTemplate;

	public void addFriend(Friend friend) {
		redisTemplate.opsForList().leftPush(KEY, friend);
	}

	public long getNumberOfFriends() {
		return redisTemplate.opsForList().size(KEY);
	}

	public Friend getFriendByIndex(Integer index) {
		return redisTemplate.opsForList().index(KEY, index);
	}

	public void deleteFriends(Friend friend) {
		redisTemplate.opsForList().remove(KEY, 1, friend);
	}

}
