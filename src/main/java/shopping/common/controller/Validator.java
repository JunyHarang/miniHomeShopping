package shopping.common.controller;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
	public boolean validate(HttpServletRequest request);
}
