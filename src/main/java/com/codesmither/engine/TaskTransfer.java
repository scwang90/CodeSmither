package com.codesmither.engine;

import com.codesmither.factory.ConfigFactory;
import com.codesmither.factory.FreemarkerFactory;
import com.codesmither.model.Model;
import com.codesmither.model.Table;
import com.codesmither.task.*;
import com.codesmither.task.FileFilter;
import com.codesmither.util.FileUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskTransfer {

	private int itask = 0;
	private String fsrc;
	private String ftarget;
	private List<Task> tasks = new ArrayList<Task>();
	private List<Table> tables;
	private Model model;
	private String packagepath;

	public TaskTransfer(Model model, File fsrc, File ftarget, FileFilter fileFilter) {
		this.model = model;
		this.tables = model.tables;
		this.fsrc = fsrc.getAbsolutePath();
		this.ftarget = ftarget.getAbsolutePath();
		this.packagepath = model.packagename.replace(".", File.separatorChar + "");
		this.tasks = new TaskLoader(fsrc,ftarget,fileFilter).loadTask();
		this.putTask(tasks);
	}

	private void putTask(List<Task> tasks) {
		for (Task task : tasks) {
			String path = task.getFile().getParent();
			path = path.replace(fsrc, ftarget);
			path = path.replace("${packagename}", packagepath);
			new File(path).mkdirs();
		}
	}

	public boolean hasTask() {
		return itask < tasks.size();
	}

	public String doTask() throws IOException, TemplateException {
		Task task = tasks.get(itask++);
		String path = task.getFile().getAbsolutePath();
		path = path.replace(fsrc, ftarget);
		path = path.replace("${packagename}", packagepath);
		File outfile = new File(path);
		
		if (task.getFile().getName().endsWith(".ftl")) {
			Template template = getTemplate(task.getFile());
			for (Table table : tables) {
				String outpath = outfile.getAbsolutePath().replace(".ftl", "");
				Writer out = getFileWriter(new File(outpath.replace("${className}", table.className)));
				model.bindTable(table);
				template.process(model, out);
				out.close();
			}
		} else if(FileUtil.isTextFile(task.getFile())){
			Template template = getTemplate(task.getFile());
			Writer out = getFileWriter(outfile);
			template.process(model, out);
			out.close();
		} else {
			FileInputStream inputStream = new FileInputStream(task.getFile());
			FileOutputStream outputStream = new FileOutputStream(outfile);
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			outputStream.write(bytes);
			outputStream.close();
			inputStream.close();
			System.gc();
		}
		return path;
	}
	
	private Template getTemplate(File file) throws IOException {
		String charset = ConfigFactory.getTemplateCharset();
		if (charset != null && charset.trim().length() > 0) {
			return FreemarkerFactory.getTemplate(file, charset);
		}
		return FreemarkerFactory.getTemplate(file);
	}

	private Writer getFileWriter(File file) throws IOException{
		String charset = ConfigFactory.getTargetCharset();
		if (charset != null && charset.trim().length() > 0) {
			return new OutputStreamWriter(new FileOutputStream(file),charset);
		}
		return new FileWriter(file);
	}
}