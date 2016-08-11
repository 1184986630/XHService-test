package cn.ffcs.xhService.uphold.model;
/**
 * OCR识别身份证信息（天翼小白）响应参数实体
 */
public class IdentificationResponse {
	private String img_type;// 解析的图片类型:1.正面；2.反面；0.无法解析
	private String id_code;// 识别出的身份证号
	private String real_name;// 识别出的姓名
	private String gender;// 识别出的性别
	private String ethnic;// 识别出的民族
	private String addr;// 识别出的身份证地
	private String issue_authority;// 识别出的签发机关
	private String valid_period;// 识别出的有效期

	public String getImg_type() {
		return img_type;
	}

	public void setImg_type(String img_type) {
		this.img_type = img_type;
	}

	public String getId_code() {
		return id_code;
	}

	public void setId_code(String id_code) {
		this.id_code = id_code;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIssue_authority() {
		return issue_authority;
	}

	public void setIssue_authority(String issue_authority) {
		this.issue_authority = issue_authority;
	}

	public String getValid_period() {
		return valid_period;
	}

	public void setValid_period(String valid_period) {
		this.valid_period = valid_period;
	}

}
