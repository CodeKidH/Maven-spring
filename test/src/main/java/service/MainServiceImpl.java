package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MainDao;
import domain.UserDto;


@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	private MainDao dao;

	public List<UserDto> getUserInfo() {
		List<UserDto> lists = dao.getUserInfo();
		return lists;
	}
} 