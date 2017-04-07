package com.qingke.boma.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qingke.boma.pojo.Company;
import com.qingke.boma.pojo.Page;
import com.qingke.boma.pojo.Recruit;
import com.qingke.boma.service.CompanyService;
import com.qingke.boma.service.RecruitService;
import com.qingke.boma.util.Const;

@Controller
@RequestMapping("/recruit")
public class RecruitController {

	@Autowired
	private RecruitService recruitServiceImpl;
	@Autowired
	private CompanyService companyServiceImpl;
	
	//��һ�λ�ȡ��Ƹ��Ϣ����
	@RequestMapping("/showIsShowRecruit")
	public ModelAndView showIsShowRecruit(HttpServletRequest req) throws IOException {
		Page page = new Page();
		page.setCurrentPage(1);
		page.setPageAmount(Const.addRecruitAmount);
		page.setCurrentPosition(page.getCurrentPosition());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/recruit/showIsShowRecruit");
		List<Recruit> recruits = recruitServiceImpl.getIsShowRecruits(page);
		modelAndView.addObject("recruits", recruits);
		modelAndView.addObject("page", page);
		String msg = (String) req.getAttribute("msg");
		if (msg != "" && msg != null) {
			modelAndView.addObject("msg", msg);
		}
		return modelAndView;
	}
	
	// ������Ƹ��Ϣ
	@RequestMapping("/addRecruit")
	public void addRecruit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Recruit recruit = new Recruit();
		String companyName = req.getParameter("company");
		Company company = companyServiceImpl.getCompanyByName(companyName);
		recruit.setTitle(req.getParameter("title"));
		recruit.setAddress(req.getParameter("address"));
		recruit.setDescription(req.getParameter("description"));
		recruit.setRequirement(req.getParameter("requirement"));
		recruit.setCompany(company);
		recruit.setSalary(req.getParameter("salary"));
		if (req.getParameter("isShow") != null) {
			recruit.setIsShow(req.getParameter("isShow"));
		}
		int count = recruitServiceImpl.insert(recruit);
		if (count == 1) {
			req.setAttribute("msg", "������Ƹ��Ϣ�ɹ�");
		} else {
			req.setAttribute("msg", "������Ƹ��Ϣʧ��");
		}
		req.getRequestDispatcher("/recruit/showIsShowRecruit").forward(req, resp);
	}
	
	// ��ȡ��������Ƹ��Ϣ�����벢��ת��������Ƹ��Ϣҳ��
	@RequestMapping("/toUpdateRecruit")
	public ModelAndView toUpdateRecruit(HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/recruit/updateRecruit");
		Recruit recruit = recruitServiceImpl.getRecruitById(Integer.parseInt(req.getParameter("recruit")));
		modelAndView.addObject("recruit", recruit);
		return modelAndView;
	}
	
	// ������Ƹ��Ϣ
	@RequestMapping("/updateRecruit")
	public void updateRecruit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Recruit recruit = new Recruit();
		recruit.setId(Integer.parseInt(req.getParameter("id")));
		recruit.setAddress(req.getParameter("address"));
		recruit.setDescription(req.getParameter("description"));
		recruit.setRequirement(req.getParameter("requirement"));
		recruit.setSalary(req.getParameter("salary"));
		recruit.setTitle(req.getParameter("title"));
		int count = recruitServiceImpl.update(recruit);
		if (count == 1) {
			req.setAttribute("msg", "������Ƹ��Ϣ�ɹ�");
		} else {
			req.setAttribute("msg", "������Ƹ��Ϣʧ��");
		}
		req.getRequestDispatcher("/recruit/showIsShowRecruit").forward(req, resp);
	}
	
	//ɾ����Ƹ��Ϣ
	@RequestMapping("/deleteRecruit")
	@ResponseBody
	public Map<String, String> deleteRecruit(Recruit recruit) {
		Map<String, String> map = new HashMap<>();
		int count = recruitServiceImpl.deleteRecruit(recruit);
		if (count == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		return map;
	}

	// ��ȡ������ʾ����Ƹ��Ϣ����ռ��ҳ��
	@RequestMapping("/getTotalPage")
	@ResponseBody
	public Map<String, Object> getTotalPage() {
		Map<String, Object> resultMap = new HashMap<>();
		Page pg = new Page();
		// ����ÿҳ��ʾ������
		pg.setPageAmount(Const.addRecruitAmount);
		// �����ܹ�����
		pg.setTotalAmount(recruitServiceImpl.getAllRecruits().size());
		System.out.println("��Ƹ��Ϣ��ҳ��:" + pg.getTotalPage());
		if (pg.getTotalPage() != null) {
			resultMap.put("result", pg.getTotalPage());
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}

	// ��õ�ǰҳ��˾(��ҳAJAX)
	@RequestMapping("/showAllRecruitsAjax")
	@ResponseBody
	public Map<String, Object> showAllRecruitsAjax(Page page) {
		Map<String, Object> resultMap = new HashMap<>();
		// ����ÿҳ��ʾ������
		System.out.println(page.getCurrentPage());
		// ����map
		page.setPageAmount(Const.addRecruitAmount);
		page.setCurrentPosition(page.getCurrentPosition());
		List<Recruit> recruits = recruitServiceImpl.getIsShowRecruits(page);
		if (recruits != null) {
			resultMap.put("result", recruits);
			resultMap.put("currentPosition", page.getCurrentPosition());
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
}
