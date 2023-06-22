package fr.junker.myApi.dao;

import java.util.List;

public interface Dao<Key, Value> {
    public void update(Value value) throws Exception;
	public Value getById(Key key) throws Exception;
	public void delete(Value value) throws Exception;
	public Value add(Value value) throws Exception;
	public List<Value> getAll() throws Exception;
}
