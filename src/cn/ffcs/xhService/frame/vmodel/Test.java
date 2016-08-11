package cn.ffcs.xhService.frame.vmodel;
import cn.ffcs.xhService.core.model.ParamsValid;
import cn.ffcs.xhService.core.model.RegexType;
import cn.ffcs.xhService.frame.service.ValidateService;
import cn.ffcs.xhService.model.Message;

/**
 * Created by user on 2016/5/20.
 */
public class Test {
	@ParamsValid(regexType = {RegexType.NOT_ENMPTY}, retCode = "222", description = "{0}不能为空")
	private String test1;
	@ParamsValid(regexExpression = "1\\d{10}", retCode = "111", description = "{1}不是正常的手机号")
	private String phone;
	@ParamsValid(regexType = {RegexType.NOT_ENMPTY, RegexType.PHONENO}, retCode = "33", description = "{0}不是非空的正常手机号")
	private String phone2;
	private String test2;

	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}

	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public static void main(String[] args) {
		Test t = new Test();
		t.setTest1("11111");
		t.setPhone("8901234567");
		t.setPhone2("18901234567");
		Message m = ValidateService.validate(t);
		System.out.println(m.getResult() + " , " + m.getDetail());
	}
}
