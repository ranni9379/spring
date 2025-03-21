package com.springStudy1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springStudy1.DAO.UserDao;
import com.springStudy1.DTO.User;


@Service
public class UserService {

private final UserDao userDao;

@Autowired
public UserService(UserDao userDao) {
	this.userDao=userDao;
}

public void save(User user) {
		//User-Dao 객체의 메서들은 호출하여 테이블에 저장하기
		//아이디 중복되지 않게 저장
		boolean hasId= userDao.findByUserId(user.getUserId());
		if(!hasId) {
			userDao.insert(user);
			//hasId가 거짓이라면 중복되지 않은 아이디
			
		}
	}

public boolean loginChk(String id, String pw) {
	
	return userDao.findByUserIdAndUserPw(id,pw);
}

}
