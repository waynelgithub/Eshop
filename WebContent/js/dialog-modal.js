/**
How to use the dialog modal?
1. 需要 Bootstrap 5 
	<link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
	<script th:src="@{/js/bootstrap.min.js}"></script>
	<script th:src="@{/js/bootstrap.bundle.min.js}"></script>
	
2. 加入此modal專屬 js
	<script type=module th:src="@{/js/dialog-modal.js}"></script>
	
3. 在HTML body 尾端 加入自訂標籤<my-dialog-modal></my-dialog-modal>
	
4. 在要呼叫此 modal 的按鈕元素內，增加以下4組 attribute
	4.1 前兩組為固定值，代表打開modal，modal id 是 staticBackdrop
		data-bs-toggle="modal" 
		data-bs-target="#staticBackdrop"
	
	4.2 data-bs-modal-message-id="訊息的id"
	4.3 data-bs-action-href="確定按鈕的超連結"

5. 增加 switch case  '此訊息的id' 跟要顯示的標題及內容

6. 範例：
	<a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop"
	th:data-bs-action-href="@{/delete-product/} + ${product.id}"
	data-bs-modal-message-id="msg-delete-product">確定</a>

	說明：
	6.1. 打開modal
	6.2. 呼叫 dialog-modal.js, 使用msg-delete-product 取得並填入 modal的標題及內容
	6.3. 把th 產生的超連結填入確定按鈕
	
	結果：
	標題：刪除
	內容：你確定你要刪除嗎？
	確定按鈕：刪除的連結

@author Wayne Lee
 * 
 */


class MyDialogModal extends HTMLElement {
    connectedCallback() {
		//modal frame code
        this.innerHTML = `		    
          <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
              data-bs-keyboard="false" tabindex="-1"
              aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <h1 class="modal-title fs-5" id="modal-title">Modal Title</h1>
                          <button type="button" class="btn-close" data-bs-dismiss="modal"
                              aria-label="Close"></button>
                      </div>
                      <div class="modal-body">...</div>
                      <div class="modal-footer">
                          <button type="button" class="btn btn-secondary"
                              data-bs-dismiss="modal">取消</button>
                          <a class="btn btn-danger" id="action-href" href="#">確定</a>
                      </div>
                  </div>
              </div>
          </div>		    
      `;
    }
}

// define <my-modal></my-modal> will be replaced by myModal.innerHTML
customElements.define('my-dialog-modal', MyDialogModal);


const dialogModal = document.getElementById('staticBackdrop')
dialogModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button = event.relatedTarget;

    // Extract info from data-bs-* attributes
    const href = button.getAttribute('data-bs-action-href');
    const messageId = button.getAttribute('data-bs-modal-message-id');

    // If necessary, you could initiate an AJAX request here
    // and then do the updating in a callback.
    
	//Prepare the message by the messageId
    let message = getMessage(messageId);
    let messageTitle = message.messageTitle;
    let messageBody = message.messageBody;

    // Update the modal's title	
    const modalTitle = dialogModal.querySelector('#modal-title');
    modalTitle.textContent = messageTitle;

    // Update the modal's body	
    const modalBody = dialogModal.querySelector('.modal-body');
    modalBody.textContent = messageBody;

    // Update the modal's action button	
    const modalAction = dialogModal.querySelector('#action-href');
    modalAction.setAttribute('href', href);

})

// Prepare the message content by the messageId 
function getMessage(messageId){
	let messageTitle;
	let messageBody;
		
		switch (messageId){
			case 'msg-delete-product':
				messageTitle = '刪除';
				messageBody = '你確定你要刪除嗎？';
				break;
				
			case 'msg-return-request-placed':
				messageTitle = '申請退貨';
				messageBody = '你確定你要申請退貨嗎？';
				break;
				
			default:
				messageTitle = '無法取得標題，請重新操作';
				messageBody = '無法取得內容，請重新操作';
		
		}
		
	return {messageTitle,messageBody};
}

