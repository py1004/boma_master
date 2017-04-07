package com.qingke.boma.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qingke.boma.pojo.Admin;
import com.qingke.boma.service.AdminService;
import com.qingke.boma.util.CheckMethod;
import com.qingke.boma.util.Const;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminServiceImpl;

	/*
	 * //�����¼ҳ��
	 * 
	 * @RequestMapping("/") public ModelAndView loginPage() { ModelAndView
	 * modelAndView = new ModelAndView();
	 * modelAndView.setViewName("/admin/Login"); return modelAndView; }
	 */

	// ��¼��֤
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(Admin admin, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		Admin realAdmin = adminServiceImpl.getAdminByUsername(admin.getUsername());
		if (realAdmin != null) {
			if (admin.getPassword().equals(realAdmin.getPassword())) {
				resultMap.put("result", "true");
				session.setAttribute("user", realAdmin);
				session.setAttribute("superAdmin", Const.superAdmin);
			}
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}

	// ��¼�ɹ���ת����ҳ��
	@RequestMapping("loginSuccess")
	public String loginSuccess() {
		return "/indexFile";
	}

	// ��ת����������ҳ��
	@RequestMapping("/toUpdatePassword")
	public ModelAndView toUpdatePassword() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin_jsp/updatePassword");
		return modelAndView;
	}

	// ��ת����ӹ���Աҳ��
	@RequestMapping("/toAddAdmin")
	public ModelAndView toAddAdmin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin_jsp/addAdmin");
		return modelAndView;
	}

	// ��������
	@RequestMapping("/updatePassword")
	public ModelAndView updatePassword(HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin_jsp/updatePassword");
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String msg = CheckMethod.validatePassword(newPassword);
		if (msg != "ok") {
			modelAndView.addObject("oldPassword", oldPassword);
			modelAndView.addObject("newPassword", newPassword);
			modelAndView.addObject("msg", msg);
			return modelAndView;
		} else {
			HttpSession session = req.getSession();
			Admin admin = (Admin) session.getAttribute("user");
			System.out.println(admin.getPassword());
			if (!admin.getPassword().equals(oldPassword)) {
				modelAndView.addObject("oldPassword", oldPassword);
				modelAndView.addObject("newPassword", newPassword);
				modelAndView.addObject("msg", "�������������");
				return modelAndView;
			} else {
				admin.setPassword(newPassword);
				int count = adminServiceImpl.update(admin);
				if (count != 1) {
					modelAndView.addObject("msg", "δ֪���󣬸�������ʧ��");
				} else {
					modelAndView.addObject("msg", "������ĳɹ�");
				}
				session.setAttribute("user", admin);
				return modelAndView;
			}
		}
	}

	// ���ӹ���Ա
	@RequestMapping("/addAdmin")
	public ModelAndView addAdmin(HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin_jsp/addAdmin");
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String msg = CheckMethod.validatePassword(password);
		if (msg != "ok") {
			modelAndView.addObject("name", name);
			modelAndView.addObject("username", username);
			modelAndView.addObject("password", password);
			modelAndView.addObject("msg", msg);
			return modelAndView;
		} else {
			Admin admin = new Admin();
			admin.setName(name);
			admin.setUsername(username);
			admin.setPassword(password);
			int count = adminServiceImpl.insert(admin);
			if (count != 1) {
				modelAndView.addObject("msg", "δ֪������������Աʧ��");
			} else {
				modelAndView.addObject("msg", "��������Ա�ɹ�");
			}
			return modelAndView;
		}
	}

	// �ǳ�
	@RequestMapping("/signOut")
	public String signOut(HttpSession session) {
		/*Admin user = (Admin) session.getAttribute("user");
		if(user != null) {
			
		}*/
		session.removeAttribute("user");
		System.out.println("�û��ǳ���");
		return "redirect:/Login.jsp";
	}

}
