package com.qingke.boma.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qingke.boma.exception.BomaException;
import com.qingke.boma.pojo.Company;
import com.qingke.boma.pojo.Logo;
import com.qingke.boma.pojo.Page;
import com.qingke.boma.pojo.Trade;
import com.qingke.boma.service.CompanyService;
import com.qingke.boma.service.TradeService;
import com.qingke.boma.util.Const;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyServiceImpl;
	@Autowired
	private TradeService tradeServiceImpl;
	// ��ȡ���й�˾
	@RequestMapping("/getAllCompanies")
	public ModelAndView getAllCompanies() {
		ModelAndView modelAndView = new ModelAndView();
		List<Company> companies = companyServiceImpl.getAllCompanies();
		modelAndView.setViewName("/recruit/addRecruit");
		modelAndView.addObject("companies", companies);
		return modelAndView;
	}

	// ͨ����˾���ƻ�ȡ��˾��Ϣ
	@RequestMapping("/getCompanyByName")
	@ResponseBody
	public Map<String, String> getCompanyByName(Company company) {
		Map<String, String> map = new HashMap<>();
		Company c = companyServiceImpl.getCompanyByName(company.getName());
		if (c != null) {
			map.put("result", c.getDescription());
		} else {
			map.put("result", "error");
		}
		return map;
	}
	
	//��ת������˾ҳ��
		@RequestMapping("/addCompany")
		public ModelAndView addCompany() {
			List<Trade> trades = tradeServiceImpl.getAllTrades();
			List<Company> companies = companyServiceImpl.getAllCompanies();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("trades", trades);
			modelAndView.addObject("companys", companies);
			modelAndView.setViewName("/company/AddCompany");
			return modelAndView;
		}
		
		//ִ��������˾
		@RequestMapping("/addCompanySubmit")
		public String addCompanySubmit(Company company, @RequestParam("file0") MultipartFile multipartFile)
				throws IllegalStateException, IOException {

			System.out.println("��˾��:" + company.getName());
			System.out.println("��˾���:" + company.getDescription());
			System.out.println("������ҵID:" + company.getTrade());
			int result = 0;
			Logo logo = new Logo();
			// logo.setCompany(company);
			// ��ȡ�ϴ���ͼƬ��ԭʼ����
			String logoName = multipartFile.getOriginalFilename();
			System.out.println("[DEBUG]ͼƬԭʼ����:" + logoName);
			if (multipartFile != null && logoName != null && logoName.length() > 0) {
				// ����ͼƬ����λ��
				String loadPath = Const.PATH_PIC;

				// �����µ�ͼƬ����
				String newLogoName = UUID.randomUUID() + logoName.substring(logoName.lastIndexOf("."));
				System.out.println("[DEBUG]�µ�ͼƬ����:" + newLogoName);

				// ����ͼƬ
				File file = new File(loadPath + newLogoName);
				// ��ҳ���ϴ���ͼƬ���ݴ���ͼƬ
				multipartFile.transferTo(file);
				// ��ͼƬ·������Logo��
				logo.setImg(newLogoName);
			}

			// ��ͼƬ���õ�company��
			company.setLogo(logo);

			result = companyServiceImpl.insert(company);
			if (result == 0) {
				System.out.println("����Ӧ���׳��쳣���ߴ�ӡ��ӹ�˾ʧ����Ϣ");
			}
			//String path = multipartFile.getOriginalFilename();
			// ��������ӳɹ�ҳ��
			return "redirect:/company/showAllCompanies";
		}
		//��ȡ���й�˾����ռ��ҳ��
		@RequestMapping("/getTotalPage")
		@ResponseBody
		public Map<String, Object> getTotalPage() {
			Map<String, Object> resultMap = new HashMap<>();
			Page pg = new Page();
			// ����ÿҳ��ʾ������
			pg.setPageAmount(Const.pageAmount);
			//�����ܹ�����
			pg.setTotalAmount(companyServiceImpl.countAllCompanies());
			System.out.println("��ҳ��:"+ pg.getTotalPage());
			if(pg.getTotalPage() != null) {
				resultMap.put("result", pg.getTotalPage());
			} else {
				resultMap.put("result", "false");
			}
			return resultMap;
		}
		//������й�˾(��ҳAJAX)
		@RequestMapping("/showAllCompaniesAjax")
		@ResponseBody
		public Map<String, Object> showAllCompaniesAjax(Page page) {
			Map<String, Object> resultMap = new HashMap<>();
			// ����ÿҳ��ʾ������
			System.out.println(page.getCurrentPage());
			page.setPageAmount(Const.pageAmount);
			// ����map
			Map<String, Integer> map = new HashMap<>();
			map.put("index", (page.getCurrentPage() - 1) * page.getPageAmount());
			map.put("offSet", page.getPageAmount());
			List<Company> companies = companyServiceImpl.getCompaniesByPage(map);
			if(companies!=null) {
				resultMap.put("result", companies);
			} else {
				resultMap.put("result", "false");
			}
			return resultMap;
		}
		
		// ������й�˾(��ҳ��һҳ)
		@RequestMapping("/showAllCompanies")
		public ModelAndView showAllCompanies(HttpServletRequest req) {
			Page page = new Page();
			// ����ÿҳ��ʾ������
			page.setPageAmount(Const.pageAmount);
			// ����map
			Map<String, Integer> map = new HashMap<>();
			map.put("index", 0);
			map.put("offSet", page.getPageAmount());

			ModelAndView modelAndView = new ModelAndView();
			List<Company> companies = companyServiceImpl.getCompaniesByPage(map);
			if (companies != null) {
				System.out.println("��ǰȡ���Ĺ�˾����:" + companies.size());
				modelAndView.addObject("companies", companies);
				modelAndView.addObject("page", page);
			} else {
				modelAndView.addObject("msg", "���ݿ���δ�����κι�˾��Ϣ");
			}
			String msg = (String)req.getAttribute("msg");
			if(msg != null) {
				modelAndView.addObject("msg", msg);
			}
			modelAndView.setViewName("/company/ShowAllCompanies");
			return modelAndView;
		}

		// ���ݹ�˾ID��ȡ��˾��Ϣ
		@RequestMapping("/updateCompany/{id}")
		public ModelAndView updateCompany(@PathVariable("id") Integer id) {
			List<Trade> trades = tradeServiceImpl.getAllTrades();
			Company company = companyServiceImpl.getCompanyById(id);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("company", company);
			System.out.println(company.getParentCompany());
			modelAndView.addObject("trades", trades);
			List<Company> geCompanies = companyServiceImpl.getAllCompanies();
			List<Company> companies = new ArrayList<>();
			for (Company c : geCompanies) {
				if (c.getId() != id) {
					companies.add(c);
				}
			}
			modelAndView.addObject("companies", companies);
			modelAndView.setViewName("/company/UpdateCompany");
			return modelAndView;
		}

		// ִ�и��º��ϴ��Ĺ�˾
		@RequestMapping("/updateCompanySubmit")
		public String updateCompanySubmit(Company company, @RequestParam("file0") MultipartFile multipartFile)
				throws Exception {
			Logo logo = null;
			String updateLogoName = multipartFile.getOriginalFilename();
			if (multipartFile != null && updateLogoName != null && updateLogoName.length() > 0) {
				// ����ͼƬ����λ��
				String loadPath = Const.PATH_PIC;
				// ɾ��֮ǰ��ͼƬ
				String oldLogoName = company.getLogo().getImg();
				System.out.println("��ͼƬ:" + oldLogoName);

				File file = new File(loadPath, oldLogoName);
				System.out.println(file.exists() + ":" + file.getAbsolutePath());
				if (file.exists()) {

					file.delete();
					System.out.println(file.exists());
				}

				// ------------------------------------------------ //
				// �����µ�ͼƬ����
				String newLogoName = UUID.randomUUID() + updateLogoName.substring(updateLogoName.lastIndexOf("."));
				System.out.println("[DEBUG]�µ�ͼƬ����:" + newLogoName);
				// ����ͼƬ
				file = new File(loadPath + newLogoName);

				System.out.println(file.exists());
				// ��ҳ���ϴ���ͼƬ���ݴ���ͼƬ
				multipartFile.transferTo(file);
				// ��ͼƬ·������Logo��
				logo = new Logo();
				logo.setImg(newLogoName);
			}
			company.setLogo(logo);
			int result = companyServiceImpl.update(company);
			if (result == 0) {
				throw new BomaException("����ʧ��, ������ִ�и��²�������");
			} else {
				return "redirect:/company/showAllCompanies";
			}
		}
		
		// ���ع�˾��
		@RequestMapping("/checkCompanyName")
		@ResponseBody
		public Map<String, Object> checkCompanyName(Company company) {
			Map<String, Object> mapResult = new HashMap<>();
			company = companyServiceImpl.getCompanyByName(company.getName());
			if(company!=null) {
				mapResult.put("result", "true");
			} else {
				mapResult.put("result", "false");
			}
			return mapResult;
		}

	@RequestMapping("/deleteCompany")
	@ResponseBody
	public Map<String, Object> deleteCompany(Integer id) {
		Map<String, Object> map = new HashMap<>();
		Company company = companyServiceImpl.getCompanyById(id);
		company.setIsCase("n");
		companyServiceImpl.update(company);
		map.put("result", "true");
		return map;
	}

	@RequestMapping("/listcompany/{id}")
	public void listCompany(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
		Trade trade = new Trade();
		trade.setId(id);
		List<Company> companies = companyServiceImpl.getUnCaseCompanyByTrade(trade);
		response.setContentType("text/html;charset=utf-8");
		String html = "[";
		for (int i = 0; i < companies.size(); i++) {
			if (i > 0) {
				html += ",";
			}
			Company company = companies.get(i);
			html += "{id:'" + company.getId() + "',name:'" + company.getName() + "',logo:'" + company.getLogo() + "'}";
		}
		html += "]";
		response.getWriter().println(html);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> addCompany(Integer id) {
		Map<String, Object> map = new HashMap<>();
		Company company = companyServiceImpl.getCompanyById(id);
		company.setIsCase("y");
		companyServiceImpl.update(company);
		map.put("result", "true");
		return map;
	}

}
