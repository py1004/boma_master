package com.qingke.boma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qingke.boma.exception.BomaException;
import com.qingke.boma.pojo.Company;
import com.qingke.boma.pojo.Manager;
import com.qingke.boma.pojo.Page;
import com.qingke.boma.service.CompanyService;
import com.qingke.boma.service.ManagerService;
import com.qingke.boma.util.Const;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private ManagerService managerServiceImpl;

	@Autowired
	private CompanyService companyServiceImpl;

	// ��ת�����Ӹ����˽���
	@RequestMapping("/addManager")
	public ModelAndView addManager() {
		// ��ȡ���й�˾(�������ʱ������ڼ��������ȡ)
		List<Company> companies = companyServiceImpl.getAllCompanies();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("companys", companies);
		modelAndView.setViewName("/manager/AddManager");
		return modelAndView;
	}

	// ��ȡҳ���ϴ��ĸ�������Ϣ�����������ݿ�
	@RequestMapping("/addManagerSubmit")
	public String addManagerSubmit(Manager manager) {
		System.out.println("manager: " + manager);
		System.out.println(manager.getCompany().getId());
		int result = managerServiceImpl.insert(manager);
		System.out.println("������" + result);
		return "redirect:/manager/addShowPraise";
	}

	// ��ѯ�ǿڱ�ӡ֤�Ĺ�˾����ҳ��
	@RequestMapping("/getTotalPageIsPraise")
	@ResponseBody
	public Map<String, Object> getTotalPageIsPraise() {
		Map<String, Object> mapResult = new HashMap<>();
		Page page = new Page();
		page.setPageAmount(Const.pageAmount);
		page.setTotalAmount(managerServiceImpl.countIsPraise());
		if (page.getTotalPage() != null) {
			System.out.println("�ǿڱ�ӡ֤����ҳ��:" + page.getTotalPage());
			mapResult.put("result", page.getTotalPage());
		} else {
			mapResult.put("result", "false");
		}
		return mapResult;
	}

	// ��ȡ�ǿڱ�ӡ֤�ĸ�����(��ҳ��һҳ)
	@RequestMapping("/getManagerIsPraise")
	public ModelAndView getManagerIsPraise() {
		System.out.println("�����ȡ�����ǿڱ�ӡ֤�Ĺ�˾");
		Map<String, Integer> map = new HashMap<>();
		map.put("index", 0);
		map.put("offset", Const.pageAmount);
		List<Manager> managers = managerServiceImpl.getAllManagersByPage(map);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("managerList", managers);
		modelAndView.setViewName("/Praise/praiseIndex");
		return modelAndView;
	}

	// ��ȡ�ǿڱ�ӡ֤�ĸ�����(��ҳAJax)
	@RequestMapping("/getManagerIsPraiseAjax")
	@ResponseBody
	public Map<String, Object> getManagerIsPraiseAjax(Page page) {
		Map<String, Object> resultMap = new HashMap<>();
		page.setPageAmount(Const.pageAmount);
		Map<String, Integer> mapPage = new HashMap<>();
		System.out.println("�ӵڼ�����ʼ:"+(page.getCurrentPage()-1)*page.getPageAmount());
		mapPage.put("index", (page.getCurrentPage() - 1) * page.getPageAmount());
		mapPage.put("offset", page.getPageAmount());
		List<Manager> managers = managerServiceImpl.getAllManagersByPage(mapPage);
		if(managers!=null) {
			resultMap.put("result", managers);
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
	
	//��ѯ���ǿڱ�ӡ֤�Ĺ�˾����ҳ��
	@RequestMapping("/getTotalPagesNotPraise")
	@ResponseBody
	public Map<String, Object> getTotalPagesNotPraise() {
		Map<String, Object> mapResult = new HashMap<>();
		Page page = new Page();
		page.setTotalAmount(managerServiceImpl.countNotPraise());
		page.setPageAmount(Const.pageAmount);
		if(page.getTotalPage()!=null) {
			mapResult.put("result", page.getTotalPage());
		} else {
			mapResult.put("result", "false");
		}
		return mapResult;
	}
	
	//��ȡ���ǿڱ�ӡ֤�ĸ�����(��ҳ��һҳ)
	@RequestMapping("/addShowPraise")
	public ModelAndView addShowPraise() {
		System.out.println("��������ҳ��");
		Map<String, Integer> mapPage = new HashMap<>();
		mapPage.put("index", 0);
		mapPage.put("offset", Const.pageAmount);
		List<Manager> managers = managerServiceImpl.getAllManagerNotByPage(mapPage);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("managers", managers);
		modelAndView.setViewName("/Praise/AddShowPraise");
		return modelAndView;
	}

	//��ȡ���ǿڱ�ӡ֤�ĸ�����(��ҳAjax)
	@RequestMapping("/getManagerIsNotPraise")
	@ResponseBody
	public Map<String, Object> getManagerIsNotPraise(Page page) {
		Map<String, Object> mapResult = new HashMap<>();
		page.setPageAmount(Const.pageAmount);
		Map<String, Integer> mapPage = new HashMap<>();
		System.out.println("�ӵڼ�����ʼ:"+(page.getCurrentPage()-1)*page.getPageAmount());
		mapPage.put("index", (page.getCurrentPage()-1)*page.getPageAmount());
		mapPage.put("offset", page.getPageAmount());
		List<Manager> managers = managerServiceImpl.getAllManagerNotByPage(mapPage);
		if(managers!=null) {
			mapResult.put("result", managers);
		} else {
			mapResult.put("result", "false");
		}
		return mapResult;
	}
	
	// �ύѡ����ʾ�Ŀڱ�ӡ֤
	@RequestMapping("/addShowPraiseSubmit")
	public String addShowPraiseSubmit(Integer[] listId) {
		System.out.println("��ѡ����" + listId.length);
		if (listId != null && listId.length > 0) {
			for (Integer id : listId) {
				System.out.println("������ID:" + id);
				Manager manager = new Manager();
				manager.setId(id);
				manager.setIsPraise("y");
				managerServiceImpl.update(manager);
			}
		}
		return "redirect:/manager/getManagerIsPraise";
	}

	// ȡ���������ǿڱ�ӡ֤
	@RequestMapping("/removeIsPraise")
	@ResponseBody
	public Map<String, Object> removeIsPraise(Manager manager) {
		Map<String, Object> map = new HashMap<String, Object>();
		manager.setIsPraise("n");
		System.out.println("ȡ����");
		int result = managerServiceImpl.update(manager);
		if (result != 0) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		return map;
	}

	// ��ȡManager��ID, ȡ����Manager����Ϣ
	@RequestMapping("/updateManagerById/{id}")
	public ModelAndView updateManagerById(@PathVariable("id") Integer id) throws Exception {
		Manager manager = managerServiceImpl.getManagerById(id);
		System.out.println("Manager:" + manager);
		ModelAndView modelAndView = new ModelAndView();
		List<Company> companies = companyServiceImpl.getAllCompanies();
		modelAndView.addObject("manager", manager);
		modelAndView.addObject("companys", companies);
		modelAndView.setViewName("/manager/UpdateManager");
		return modelAndView;
	}

	// ����Id���¸�����
	@RequestMapping("/updateManagerByIdSubmit/{id}")
	public String updateManagerByIdSubmit(@PathVariable("id") Integer id, Manager manager, HttpServletRequest request)
			throws BomaException {
		manager.setId(id);
		int result = managerServiceImpl.update(manager);
		// if(result==0){
		// throw new BomaException("δ֪����, ����ʧ��!!");
		// } else {
		// System.out.println("�Ѿ�����");
		// }
		System.out.println(result);
		if ("y".equals(manager.getIsPraise())) {
			return "redirect:/manager/getManagerIsPraise";
		} else {
			return "redirect:/manager/addShowPraise";
		}
	}
}
