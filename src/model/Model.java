package model;

import com.db4o.ObjectContainer;

public class Model {

	protected ObjectContainer db;
	
	public Model(ObjectContainer db) {
        this.db = db;
    }

	public ObjectContainer getDb() {
		return db;
	}

	public void setDb(ObjectContainer db) {
		this.db = db;
	}
	
}
