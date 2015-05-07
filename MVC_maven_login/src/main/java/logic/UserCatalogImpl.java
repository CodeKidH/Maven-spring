package logic;

import dao.UserDao;

public class UserCatalogImpl implements UserCatalog{
	
	private UserDao userDaO;
	
	public void setUserDao(UserDao userDao){
		this.userDaO = userDao;
	}
	
	public User getUserByUserIdAndPassword(String userId, String password){
		return this.userDaO.findByUserIdAndPassword(userId, password);
	}
}
