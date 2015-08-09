package org.cb.zframe.auth.service.impl;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.cb.zframe.auth.Dept;
import org.cb.zframe.auth.Org;
import org.cb.zframe.auth.Resource;
import org.cb.zframe.auth.Role;
import org.cb.zframe.auth.User;
import org.cb.zframe.auth.service.InitManager;
import org.cb.zframe.auth.service.UserManager;
import org.cb.zframe.service.GenericManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InitManagerImpl implements InitManager {
	private UserManager umgr;
	private GenericManager<Role, Long> rmgr;
	private GenericManager<Dept, Long> dmgr;
	private GenericManager<Org, Long> omgr;
	private GenericManager<Resource, Long> fmgr;
	private final Logger log = LoggerFactory.getLogger(getClass());

	public void initAuth(String fileName) throws Exception {
		Map<String, Role> roleMap = new HashMap<String, Role>();
		Map<String, Resource> resourceMap = new HashMap<String, Resource>();
		Map<String, Org> orgMap = new HashMap<String, Org>();
		Map<String, Dept> deptMap = new HashMap<String, Dept>();
		Map<String, User> userMap = new HashMap<String, User>();
		InputStream input = this.getClass().getResourceAsStream(fileName);
		POIFSFileSystem fs = new POIFSFileSystem(input);

		HSSFWorkbook wb = new HSSFWorkbook(fs);
		initRole(wb, roleMap);
		initResource(wb, resourceMap);
		initRoleResource(wb, roleMap, resourceMap);
		initOrg(wb, orgMap);
		initDept(wb, deptMap, orgMap);
		initUser(wb, userMap, deptMap);
		initUserRole(wb, userMap, roleMap);
		input.close();

	}

	public void setUserManager(UserManager userManager) {
		this.umgr = userManager;
	}

	public void setRoleManager(GenericManager<Role, Long> rmgr) {
		this.rmgr = rmgr;
	}

	public void setDeptManager(GenericManager<Dept, Long> dmgr) {
		this.dmgr = dmgr;
	}

	public void setOrgManager(GenericManager<Org, Long> omgr) {
		this.omgr = omgr;
	}

	public void setResourceManager(GenericManager<Resource, Long> fmgr) {
		this.fmgr = fmgr;
	}

	private void initRoleResource(HSSFWorkbook wb, Map<String, Role> roleMap,
			Map<String, Resource> resourceMap) {
		HSSFSheet sheet = wb.getSheetAt(2);
		Iterator rows = sheet.rowIterator();
		rows.next();// 第一行标题不读
		for (; rows.hasNext();) {
			HSSFRow row = (HSSFRow) rows.next();
			Role role = null;
			for (Iterator cells = row.cellIterator(); cells.hasNext();) {
				HSSFCell cell = (HSSFCell) cells.next();
				switch (cell.getCellNum()) {
				case 0:
					role = roleMap
							.get(cell.getRichStringCellValue().toString());
					break;
				case 1:
					role.addResource(resourceMap.get(cell
							.getRichStringCellValue().toString()));
					role = rmgr.save(role);
					break;
				}
			}
		}
	}

	private void initResource(HSSFWorkbook wb, Map<String, Resource> resourceMap) {
		HSSFSheet sheet = wb.getSheetAt(1);
		Iterator rows = sheet.rowIterator();
		rows.next();// 第一行标题不读
		for (; rows.hasNext();) {
			HSSFRow row = (HSSFRow) rows.next();
			Resource resource = new Resource();
			for (Iterator cells = row.cellIterator(); cells.hasNext();) {
				HSSFCell cell = (HSSFCell) cells.next();
				switch (cell.getCellNum()) {
				case 0:
					resource.setCode(cell.getRichStringCellValue().toString());
					break;
				case 1:
					resource.setDescription(cell.getRichStringCellValue()
							.toString());
					break;
				case 2:
					Resource parentResource = resourceMap.get(cell
							.getRichStringCellValue().toString());
					resource.setParent(parentResource);
					break;
				}
			}
			resource = fmgr.save(resource);
			resourceMap.put(resource.getCode(), resource);
		}
	}

	private void initOrg(HSSFWorkbook wb, Map<String, Org> orgMap) {
		HSSFSheet sheet = wb.getSheetAt(3);
		Iterator rows = sheet.rowIterator();
		rows.next();// 第一行标题不读
		for (; rows.hasNext();) {
			HSSFRow row = (HSSFRow) rows.next();
			Org org = new Org();
			for (Iterator cells = row.cellIterator(); cells.hasNext();) {
				HSSFCell cell = (HSSFCell) cells.next();
				switch (cell.getCellNum()) {
				case 0:
					if (cell.getRichStringCellValue().toString().length() == 0)
						return;
					log.error(cell.getRichStringCellValue().toString());
					org.setName(cell.getRichStringCellValue().toString());
					break;
				case 1:
					org.setOrgId((long) cell.getNumericCellValue());
					break;
				case 2:
					org.setSpell(cell.getRichStringCellValue().toString());
					break;
				}
			}
			org = omgr.save(org);
			orgMap.put(org.getName(), org);
		}
	}

	private void initUserRole(HSSFWorkbook wb, Map<String, User> userMap,
			Map<String, Role> roleMap) {
		HSSFSheet sheet = wb.getSheetAt(6);
		Iterator rows = sheet.rowIterator();
		rows.next();// 第一行标题不读
		for (; rows.hasNext();) {
			HSSFRow row = (HSSFRow) rows.next();
			User user = null;
			for (Iterator cells = row.cellIterator(); cells.hasNext();) {
				HSSFCell cell = (HSSFCell) cells.next();
				switch (cell.getCellNum()) {
				case 0:
					user = userMap
							.get(cell.getRichStringCellValue().toString());
					break;
				case 1:
					user.addRole(roleMap.get(cell.getRichStringCellValue()
							.toString()));
					user = umgr.save(user);
					break;
				}
			}
		}

	}

	private void initRole(HSSFWorkbook wb, Map<String, Role> roleMap) {
		HSSFSheet sheet = wb.getSheetAt(0);
		Iterator rows = sheet.rowIterator();
		rows.next();// 第一行标题不读
		for (; rows.hasNext();) {
			HSSFRow row = (HSSFRow) rows.next();
			Role role = new Role();
			for (Iterator cells = row.cellIterator(); cells.hasNext();) {
				HSSFCell cell = (HSSFCell) cells.next();
				switch (cell.getCellNum()) {
				case 0:
					if (cell.getRichStringCellValue().toString().length() == 0)
						return;
					role.setName(cell.getRichStringCellValue().toString());
					break;
				case 1:
					role.setDescription(cell.getRichStringCellValue()
							.toString());
					break;
				}
			}
			role = rmgr.save(role);
			roleMap.put(role.getName(), role);
		}
	}

	private void initDept(HSSFWorkbook wb, Map<String, Dept> deptMap,
			Map<String, Org> orgMap) {
		HSSFSheet sheet = wb.getSheetAt(4);
		Iterator rows = sheet.rowIterator();
		rows.next();// 第一行标题不读
		for (; rows.hasNext();) {
			HSSFRow row = (HSSFRow) rows.next();
			Dept dept = new Dept();
			for (Iterator cells = row.cellIterator(); cells.hasNext();) {
				HSSFCell cell = (HSSFCell) cells.next();
				switch (cell.getCellNum()) {
				case 0:
					dept.setName(cell.getRichStringCellValue().toString());
					break;
				case 1:
					dept.setSpell(cell.getRichStringCellValue().toString());
					break;
				case 2:
					Org org = orgMap.get(cell.getRichStringCellValue()
							.toString());
					dept.setOrg(org);
					break;
				}
			}
			dept = dmgr.save(dept);
			deptMap.put(dept.getOrg().getName() + dept.getName(), dept);
		}
	}

	private void initUser(HSSFWorkbook wb, Map<String, User> userMap,
			Map<String, Dept> deptMap) {
		HSSFSheet sheet = wb.getSheetAt(5);
		Iterator rows = sheet.rowIterator();
		rows.next();// 第一行标题不读
		for (; rows.hasNext();) {
			HSSFRow row = (HSSFRow) rows.next();
			User user = new User();
			String org = ""; // 记录公司列的值
			for (Iterator cells = row.cellIterator(); cells.hasNext();) {
				HSSFCell cell = (HSSFCell) cells.next();
				switch (cell.getCellNum()) {
				case 0:
					user.setName(cell.getRichStringCellValue().toString());
					break;
				case 1:
					user.setRealName(cell.getRichStringCellValue().toString());
					break;
				case 2:
					user.setEmail(cell.getRichStringCellValue().toString());
					break;
				case 3:
					user.setPhone(getStringValue(cell));
					break;
				case 4:
					user.setMobile(getStringValue(cell));
					break;
				case 5:
					user.setPosition(cell.getRichStringCellValue().toString());
					break;
				case 6:
					org = cell.getRichStringCellValue().toString();
					break;
				case 7:
					user.setDept(deptMap.get(org
							+ cell.getRichStringCellValue().toString()));
					break;
				case 8:
					user.setPassword(getStringValue(cell));
					break;
				}
			}
			user = umgr.saveUser(user, false);
			userMap.put(user.getName(), user);
		}
	}

	/**
	 * 将不为String类型的cell变成String，如电话号码
	 * 
	 * @param cell
	 * @return
	 */
	private static String getStringValue(HSSFCell cell) {
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:

			String s = NumberFormat.getNumberInstance().format(
					cell.getNumericCellValue());
			s = s.replaceAll(",", "");
			return s;
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getRichStringCellValue().toString();
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		default:
			return "unsuported cell type";
		}
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate() + " " + date.getHours() + ":"
						+ date.getMinutes() + ":" + date.getSeconds();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}
}
