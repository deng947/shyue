package org.cb.zframe.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreemarkerGenerator {
	private String rootPath = System.getProperty("user.dir");

	public static void main(String[] args) throws Exception{
		FreemarkerGenerator f=new FreemarkerGenerator();
		System.out.println("start generating...");
//		f.generateAllByClassName("org.openfans.gear.auth.Dept");
//		f.generateAllByClassName("org.openfans.gear.auth.Org");
		f.generateAllByClassName("org.openfans.gear.sample.Computer");
		f.generateAllByClassName("org.openfans.gear.sample.Person");
		System.out.println("generating successed ");
	}

	private void generateAllByClassName(String className) throws IOException, Exception {
		Map prop = setProp(className);
		Configuration cfg = config();

		generateJavaForm(prop, cfg);

		generateJavaController(prop, cfg);

//		generateDaoTest(prop, cfg);

		generateManagerTest(prop, cfg);

		generateJspList(prop, cfg);

		generateJspForm(prop, cfg);

		generateJspView(prop, cfg);
	}

	private Map setProp(String className) {
		Map prop = new HashMap();
		GearClass c = new GearClass(className);
		prop.put("class", c);
		prop.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()));
		return prop;
	}

	private Configuration config() throws IOException {
		Configuration cfg = new Configuration();
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(rootPath
				+ "/src/main/webapp/ftl/"));
		return cfg;
	}

	private void generateJavaForm(Map prop, Configuration cfg) throws Exception {
		generateFile(prop, cfg, "JavaForm.ftl", rootPath + "/src/main/java/"
				+ packageToPath(((GearClass) prop.get("class")).getPack())
				+ "/web/controller/"
				+ ((GearClass) prop.get("class")).getName() + "Form.java");
	}

	private void generateDaoTest(Map prop, Configuration cfg) throws Exception {
		generateFile(prop, cfg, "JavaDaoTest.ftl", rootPath + "/src/test/java/"
				+ packageToPath(((GearClass) prop.get("class")).getRootPack())
				+ "/dao/" + ((GearClass) prop.get("class")).getName()
				+ "DaoTest.java");

	}

	private void generateManagerTest(Map prop, Configuration cfg)
			throws Exception {
		generateFile(prop, cfg, "JavaManagerTest.ftl", rootPath
				+ "/src/test/java/"
				+ packageToPath(((GearClass) prop.get("class")).getRootPack())
				+ "/service/" + ((GearClass) prop.get("class")).getName()
				+ "ManagerTest.java");

	}

	private void generateFile(Map prop, Configuration cfg, String templateName,
			String filePath) throws Exception {
		String dir = filePath.substring(0, filePath.lastIndexOf("/"));
		createDirWithParent(dir);
		Template temp = cfg.getTemplate(templateName);
		temp.setEncoding("UTF-8");
		File file = new File(filePath);
		backupFile(file);
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "UTF-8"));
		temp.process(prop, out);
		out.flush();
		out.close();
	}

	private void generateJavaController(Map prop, Configuration cfg)
			throws Exception {
		generateFile(prop, cfg, "JavaController.ftl", rootPath
				+ "/src/main/java/"
				+ packageToPath(((GearClass) prop.get("class")).getPack())
				+ "/web/controller/"
				+ ((GearClass) prop.get("class")).getName() + "Controller.java");
	}

	private void generateJspList(Map prop, Configuration cfg) throws Exception {
		generateFile(prop, cfg, "JspList.ftl", rootPath
				+ "/src/main/webapp/WEB-INF/jsp/"
				+ ((GearClass) prop.get("class")).getModule() + "/"
				+ ((GearClass) prop.get("class")).getSpell() + "List.jsp");
	}

	private void generateJspForm(Map prop, Configuration cfg) throws Exception {
		generateFile(prop, cfg, "JspForm.ftl", rootPath
				+ "/src/main/webapp/WEB-INF/jsp/"
				+ ((GearClass) prop.get("class")).getModule() + "/"
				+ ((GearClass) prop.get("class")).getSpell() + "Form.jsp");
	}

	private void generateJspView(Map prop, Configuration cfg) throws Exception {
		generateFile(prop, cfg, "JspView.ftl", rootPath
				+ "/src/main/webapp/WEB-INF/jsp/"
				+ ((GearClass) prop.get("class")).getModule() + "/"
				+ ((GearClass) prop.get("class")).getSpell() + ".jsp");

	}

	private void backupFile(File file) throws Exception {
		if (file.exists()) {
			file.renameTo(new File(file.getAbsolutePath() + ".bak"));
		}
	}

	private String packageToPath(String packageName) {
		return packageName.replace('.', '/');
	}

	private void createDirWithParent(String dir) {
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
	}
}
