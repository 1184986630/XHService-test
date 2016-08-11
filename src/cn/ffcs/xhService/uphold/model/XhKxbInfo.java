package cn.ffcs.xhService.uphold.model;

import java.util.List;

public class XhKxbInfo {
		private String description;//产品描述
        private String id;//产品id
        private String name;//产品名称
        private List services;
        private String type;//产品烈性
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public List getServices() {
			return services;
		}
		public void setServices(List services) {
			this.services = services;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
        
}
