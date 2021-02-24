package qq.com.springbook.view.controller;

public class ViewResolver {

	public String prefix;
	public String suffix;

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String sufiix) {
		this.suffix = sufiix;
	}

	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}

}
