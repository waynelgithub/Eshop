package main.model;

public enum SalesReturnStatus {
	RETURNABLE("return.status.msg.returnable"),
	NON_RETURNABLE("return.status.msg.nonreturnable"),
	RETURNED("return.status.msg.returned");
	
	private String messageCode;
	
	private SalesReturnStatus(String messageCode) {
		this.messageCode = messageCode;
	}
	
	//給前端 thymeleaf ，取得退貨代碼對應該顯示的訊息
	public String getMessageCode() {		
		return this.messageCode;
	}
}
