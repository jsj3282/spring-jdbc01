package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.jdbc_dao.JdbcDAO;

public class JdbcSaveServiceImpl implements JdbcService {

	@Override
	public void execute(Model model) {
		//request로 저장되어 있으므로 map 형태로 가져와야 한다.
		//model은 넘겨줄 필요 없지만 이미 execute(Model model)이므로 그냥 넘겨줌
		//model에 있는 값을 쓸때는 object로 받은후 map으로 변환한다.
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		JdbcDAO dao = new JdbcDAO();
		dao.save(id, name);
		
	}

}
