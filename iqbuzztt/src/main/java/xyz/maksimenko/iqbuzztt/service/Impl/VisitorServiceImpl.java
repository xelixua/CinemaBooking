package xyz.maksimenko.iqbuzztt.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.maksimenko.iqbuzztt.Visitor;
import xyz.maksimenko.iqbuzztt.DAO.VisitorDAO;
import xyz.maksimenko.iqbuzztt.service.VisitorService;

@Service
@Transactional
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	private VisitorDAO visitorDAO;
	
	public Visitor addVisitor(String visitorName) {
		Visitor visitor = new Visitor();
		visitor.setFullName(visitorName);
		visitorDAO.addVisitor(visitor);
		return visitorDAO.getVisitorByUserName(visitor.getFullName());
	}

}
