package org.reqplay.util;

import java.io.File;

import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a resource list.
 * 
 * @author Douglas Siviotti
 * 
 */
public class ResourceList {

	private List<String> list;
	private String packageRoot;
	private String extension;
	private List<String> includePattern = new ArrayList<String>();
	private List<String> excludePattern = new ArrayList<String>();

	public ResourceList() {
		super();
	}

	/**
	 * Return all files terminated with .class (Classes, Interfaces, Annotations
	 * etc).
	 * 
	 * @return A list
	 */
	public List<Class<?>> listTypes(boolean addClass, boolean addInterface,
			boolean addEnum, boolean addAnnotation) {
		List<String> classList = listClassNames();
		List<Class<?>> classes = new ArrayList<Class<?>>();
		Class<?> c;
		for (String className : classList) {
			try {
				//System.out.println("className: " + className);
				c = Class.forName(className);
				if (c.isAnnotation()) {
					if (addAnnotation) {
						classes.add(c);
					}
				} else if (c.isEnum()) {
					if (addEnum) {
						classes.add(c);
					}
				} else if (c.isInterface()) {
					if (addInterface) {
						classes.add(c);
					}
				} else { // isClass
					if (addClass) {
						classes.add(c);
					}
				}
			} catch (Exception e) {
			}
		}
		return classes;
	}

	public List<Class<?>> listClasses() {
		return listTypes(true, false, false, false);
	}

	public List<Class<?>> listInterfaces() {
		return listTypes(false, true, false, false);
	}

	public List<Class<?>> listEnums() {
		return listTypes(false, false, true, false);
	}

	public List<Class<?>> listAnnotations() {
		return listTypes(false, false, false, true);
	}

	public List<String> listClassNames() {
		String tmp = extension;
		extension = ".class";
		list(); // filtra somente as classes
		// showList(list);
		List<String> classList = new ArrayList<String>();
		for (String s : list) {
			if (s.endsWith(".class")) {
				classList.add(s.substring(0, s.length() - 6));
			}
		}
		extension = tmp;
		return classList;
	}

	public List<String> list() {
		list = new ArrayList<String>();
		URLClassLoader loader = (URLClassLoader) Thread.currentThread()
				.getContextClassLoader();
		URL[] urls = loader.getURLs();
		File file = null;
		for (URL url : urls) {
			try {
				//System.out.println("URL "+url);
				file = new File(url.toURI());
				if (file.isDirectory()) {
					//System.out.println("dir "+file.getPath());
					loadFiles(null, file);
				}
			} catch (URISyntaxException e) {
				System.out.println("Error:" + file);
			}
		}
		return list;
	}

	private void loadFiles(String parent, File directory) {
		String location = null;
		for (File file : directory.listFiles()) {

			if (parent == null) {
				location = file.getName();
			} else {
				location = parent + "." + file.getName();
			}
			// System.out.println("location:"+location);
			if (file.isDirectory()) {
				loadFiles(location, file);
			} else {
				// System.out.println("file "+file);
				if (location.indexOf("$") > 0) {
					continue;
				}
				if (!excludePattern.isEmpty()) {
					boolean excludeMatch = false;
					for (String pattern : excludePattern) {
						//System.out.println("excludePattern:"+pattern);
						if (location.matches(pattern)) {
							//System.out.println(excludePattern + ":" +location);
							excludeMatch = true;
							continue;
						}
					}
					if (excludeMatch) {
						continue;
					}
				}
				if (!includePattern.isEmpty()) {
					boolean includeMatch = false;
					for (String pattern : includePattern) {
						if (location.matches(pattern)) {
							list.add(location);
							continue;
						}
					}
					if (includeMatch) {
						continue;
					}
				}
				if (extension != null && !location.endsWith(extension)) {
					continue;
				}
				if (packageRoot != null && !location.startsWith(packageRoot)) {
					continue;
				}
				//System.out.println("added:" + location);
				list.add(location);
			}
		}
	}

	public String getPackageRoot() {
		return packageRoot;
	}

	public void setPackageRoot(String packageRoot) {
		this.packageRoot = packageRoot;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void showList(List<Class<?>> list) {
		for (Object o : list) {
			System.out.println(o);
		}
	}

	public List<String> getIncludePattern() {
		return new ArrayList<String>(includePattern);
	}

	public void addIncludePattern(String pattern) {
		includePattern.add(pattern);
	}

	public List<String> getExcludePattern() {
		return new ArrayList<String>(excludePattern);
	}

	public void addExcludePattern(String pattern) {
		excludePattern.add(pattern);
	}

}
