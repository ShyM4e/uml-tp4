package org.mql.java.format;

public class RelationshipFormat {
	private String fromClass;
    private String toClass;
    private String relationshipType;

    public RelationshipFormat(String fromClass, String toClass, String relationshipType) {
        this.fromClass = fromClass;
        this.toClass = toClass;
        this.relationshipType = relationshipType;
    }

    
    public String getFromClass() {
		return fromClass;
	}


	public void setFromClass(String fromClass) {
		this.fromClass = fromClass;
	}


	public String getToClass() {
		return toClass;
	}


	public void setToClass(String toClass) {
		this.toClass = toClass;
	}


	public String getRelationshipType() {
		return relationshipType;
	}


	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}


	@Override
    public String toString() {
        return relationshipType + " : " +fromClass + "->" + toClass;
    }
	

}
