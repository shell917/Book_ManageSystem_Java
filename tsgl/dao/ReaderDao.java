package dao;

import java.util.List;

import entity.Reader;

public interface ReaderDao {
	public Reader queryCurrentUserInfo();
	public List<Reader> queryAllReader();
	public List<Reader> queryReaderByName(String name);
	public List<Reader> queryReaderByUserName(String userName);
	public void rePassword(String password);
	
	
	public void addReader(Reader reader);
	public void delReader(String userName);
	public void updateReader(Reader reader, String userName);
}
