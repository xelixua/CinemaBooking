package xyz.maksimenko.iqbuzztt.DAO;

import xyz.maksimenko.iqbuzztt.Visitor;

public interface VisitorDAO {
	public void addVisitor(Visitor visitor);
	public void updateVisitor(Visitor visitor);
	public Visitor getVisitor(Long visitorId);
	public Visitor getVisitorByUserName(String userName);
	public void removeVisitor(Long visitorId);
	
}
