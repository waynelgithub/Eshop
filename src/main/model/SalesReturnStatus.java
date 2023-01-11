package main.model;

public enum SalesReturnStatus {
	//是否可退貨
	RETURNABLE("return.status.msg.returnable"),//可退貨
	NON_RETURNABLE("return.status.msg.nonreturnable"), //不可退貨

	//退貨流程
	RETURN_REQUEST_PLACED("return.status.msg.request.placed"),//已申請退貨
	RETURN_SHIPPING("return.status.msg.return.shipping"), //退貨運送中
	RETURNED("return.status.msg.returned"),//已退貨完成
	
	//退款流程
	REFUND_PROCESSING("return.status.msg.refund.processing"),//退款處理中
	RETURNLESS_REFUND_PROCESSING("return.status.msg.returnless.refund.processing"),//無需退貨的退款處理中
	REFUNDED("return.status.msg.refunded");//已退款


	
	
	private String messageCode;
	
	private SalesReturnStatus(String messageCode) {
		this.messageCode = messageCode;
	}
	
	//給前端 thymeleaf ，取得退貨代碼對應該顯示的訊息
	public String getMessageCode() {		
		return this.messageCode;
	}
}
